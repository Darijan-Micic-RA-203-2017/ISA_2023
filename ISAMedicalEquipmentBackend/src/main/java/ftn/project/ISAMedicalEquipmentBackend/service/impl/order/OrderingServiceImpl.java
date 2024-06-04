package ftn.project.ISAMedicalEquipmentBackend.service.impl.order;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import ftn.project.ISAMedicalEquipmentBackend.converter.equipment.MedicalEquipmentConverter;
import ftn.project.ISAMedicalEquipmentBackend.domain.equipment.MedicalEquipment;
import ftn.project.ISAMedicalEquipmentBackend.domain.order.DetailsOfEquipmentOrder;
import ftn.project.ISAMedicalEquipmentBackend.domain.order.EquipmentOrder;
import ftn.project.ISAMedicalEquipmentBackend.domain.term.ExchangeTerm;
import ftn.project.ISAMedicalEquipmentBackend.domain.user.CompanyAdministrator;
import ftn.project.ISAMedicalEquipmentBackend.domain.user.ProcurementManager;
import ftn.project.ISAMedicalEquipmentBackend.dto.order.DetailsOfEquipmentOrderDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.order.EquipmentOrderDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.order.OrderCreationDTO;
import ftn.project.ISAMedicalEquipmentBackend.exception.IncorrectSubtotalPriceOfOrderDetailsException;
import ftn.project.ISAMedicalEquipmentBackend.exception.NotEnoughEquipmentForOrderException;
import ftn.project.ISAMedicalEquipmentBackend.exception.PastTermDeletionException;
import ftn.project.ISAMedicalEquipmentBackend.service.company.MedicalEquipmentCompanyService;
import ftn.project.ISAMedicalEquipmentBackend.service.equipment.MedicalEquipmentService;
import ftn.project.ISAMedicalEquipmentBackend.service.order.EquipmentOrderService;
import ftn.project.ISAMedicalEquipmentBackend.service.order.OrderingService;
import ftn.project.ISAMedicalEquipmentBackend.service.term.ExchangeTermService;
import ftn.project.ISAMedicalEquipmentBackend.service.user.ProcurementManagerService;

@Service
public class OrderingServiceImpl implements OrderingService {
	private final MedicalEquipmentService medicalEquipmentService;
	private final EquipmentOrderService equipmentOrderService;
	private final ExchangeTermService exchangeTermService;
	private final MedicalEquipmentCompanyService medicalEquipmentCompanyService;
	private final ProcurementManagerService procurementManagerService;
	
	private final JavaMailSender javaMailSender;
	private final Environment environment;
	
	@Autowired
	public OrderingServiceImpl(MedicalEquipmentService medicalEquipmentService, 
			EquipmentOrderService equipmentOrderService, ExchangeTermService exchangeTermService, 
			MedicalEquipmentCompanyService medicalEquipmentCompanyService, 
			ProcurementManagerService procurementManagerService, JavaMailSender javaMailSender, 
			Environment environment) {
		this.medicalEquipmentService = medicalEquipmentService;
		this.equipmentOrderService = equipmentOrderService;
		this.exchangeTermService = exchangeTermService;
		this.medicalEquipmentCompanyService = medicalEquipmentCompanyService;
		this.procurementManagerService = procurementManagerService;
		this.javaMailSender = javaMailSender;
		this.environment = environment;
	}

	@Override
	public EquipmentOrder createOrder(OrderCreationDTO orderCreationDTO) throws 
			NotEnoughEquipmentForOrderException, IncorrectSubtotalPriceOfOrderDetailsException {
		List<MedicalEquipment> equipmentInOrder = new ArrayList<MedicalEquipment>();
		List<Integer> amountsOfEquipmentInOrder = new ArrayList<Integer>();
		for (DetailsOfEquipmentOrderDTO dDTO: orderCreationDTO.getOrder().getDetails()) {
			MedicalEquipment equipment = medicalEquipmentService.findById(dDTO.getEquipmentId());
			int amount = dDTO.getAmount();
			
			if (!isThereEnoughEquipmentForOrder(equipment, amount)) {
				throw new NotEnoughEquipmentForOrderException();
			}
			if (!doesSpecifiedSubtotalPriceMatchTheExactOne(dDTO, equipment.getPrice())) {
				throw new IncorrectSubtotalPriceOfOrderDetailsException();
			}
			
			equipmentInOrder.add(equipment);
			amountsOfEquipmentInOrder.add(amount);
		}
		
		for (int i = 0; i < equipmentInOrder.size(); i++) {
			MedicalEquipment medEqu = equipmentInOrder.get(i);
			int amount = amountsOfEquipmentInOrder.get(i);
			
			medEqu.setAmount(medEqu.getAmount() - amount);
			medicalEquipmentService.save(MedicalEquipmentConverter.convertToDTO(medEqu));
		}
		
		CompanyAdministrator availableCompanyAdministrator = 
				medicalEquipmentCompanyService.findAvailableCompanyAdministrator(
						orderCreationDTO.getTerm().getCompanyId());
		orderCreationDTO.getTerm().setCompanyAdministratorId(availableCompanyAdministrator.getId());
		
		ExchangeTerm exchangeTerm = 
				exchangeTermService.reserveTerm(orderCreationDTO.getTerm());
		orderCreationDTO.getOrder().setExchangeTermId(exchangeTerm.getId());
		
		EquipmentOrder order = 
				equipmentOrderService.create(orderCreationDTO.getOrder(), equipmentInOrder);
		
		return order;
	}
	
	@Override
	public boolean isThereEnoughEquipmentForOrder(MedicalEquipment equipment, 
			int requestedAmountInOrder) {
		boolean isThereEnough = true;
		if (equipment.getAmount() < requestedAmountInOrder) {
			isThereEnough = false;
		}
		
		return isThereEnough;
	}
	
	@Override
	public boolean doesSpecifiedSubtotalPriceMatchTheExactOne(
			DetailsOfEquipmentOrderDTO detailsOfOrder, double priceOfEquipment) {
		boolean doesItMatch = true;
		
		double exactSubtotalPrice = detailsOfOrder.getAmount() * priceOfEquipment;
		if (Double.doubleToLongBits(detailsOfOrder.getSubtotalPrice()) != 
				Double.doubleToLongBits(exactSubtotalPrice)) {
			doesItMatch = false;
		}
		
		return doesItMatch;
	}
	
	@Override
	public byte[] generateQRCodeOfNewEquipmentOrder(EquipmentOrderDTO newEquipmentOrder) 
			throws WriterException, IOException {
		BufferedImage imageOfQRCode = generateImageOfQRCode(newEquipmentOrder.toString());
		
		// REFERENCE: https://stackoverflow.com/questions/59786720/spring-convert-buffered-image-into-response-entity/59787932#59787932
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ImageIO.write(imageOfQRCode, "png", byteArrayOutputStream);
		byte[] imageOfQRCodeAsByteArray = byteArrayOutputStream.toByteArray();
		byteArrayOutputStream.close();
		
		return imageOfQRCodeAsByteArray;
	}
	
	@Override
	public BufferedImage generateImageOfQRCode(String barcodeText) throws WriterException {
		// REFERENCE: https://www.baeldung.com/java-generating-barcodes-qr-codes
		QRCodeWriter barcodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 176, 176);
		
		return MatrixToImageWriter.toBufferedImage(bitMatrix);
	}
	
	@Async
	@Override
	public void sendEmailWithQRCodeOfNewOrder(EquipmentOrderDTO newEquipmentOrder, 
			byte[] imageOfQRCodeAsByteArray) throws MailException {
		// REFERENCE: https://docs.spring.io/spring-framework/docs/1.0.1/javadoc-api/org/springframework/mail/javamail/MimeMessageHelper.html
		ByteArrayResource byteArrayResource = new ByteArrayResource(imageOfQRCodeAsByteArray);
		
		javaMailSender.send(new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws MessagingException {
				MimeMessageHelper emailMessageWithQRCodeOfNewOrder = 
						new MimeMessageHelper(mimeMessage, true, "UTF-8");
				
				ProcurementManager procurementManager = procurementManagerService
						.findById(newEquipmentOrder.getProcurementManagerId());
				emailMessageWithQRCodeOfNewOrder.setTo(procurementManager.getEmailAddress());
				emailMessageWithQRCodeOfNewOrder.setFrom(
						environment.getProperty("spring.mail.username"));
				emailMessageWithQRCodeOfNewOrder.setSubject(
						"ISAMedicalEquipment - potvrda zakazivanja termina za preuzimanje opreme");
				
				StringBuilder emailMessageTextBuilder = new StringBuilder("<p>Poštovani/a ");
				emailMessageTextBuilder.append(procurementManager.getFirstName()).append(",</p>");
				emailMessageTextBuilder.append("<p>Uspešno je zakazan termin za preuzimanje opreme.");
				emailMessageTextBuilder.append(" QR kod narudžbine:</p>");
				emailMessageTextBuilder.append("<p><img src='cid:imageOfQRCode'></p>");
				emailMessageTextBuilder.append("<p>Srdačan pozdrav!</p>");
				
				emailMessageWithQRCodeOfNewOrder.setText(emailMessageTextBuilder.toString(), true);
				emailMessageWithQRCodeOfNewOrder.addInline("imageOfQRCode", byteArrayResource, 
						"image/png");
			}
		});
	}
	
	@Override
	public boolean deleteOrderByExchangeTermId(long exchangeTermId) throws PastTermDeletionException {
		EquipmentOrder deletedEquipmentOrder = 
				equipmentOrderService.deleteByExchangeTermId(exchangeTermId);
		if (deletedEquipmentOrder == null) {
			return false;
		}
		
		restoreAmountsOfMedicalEquipment(deletedEquipmentOrder);
		
		penalizeProcurementManager(deletedEquipmentOrder);
		
		return true;
	}
	
	@Override
	public void restoreAmountsOfMedicalEquipment(EquipmentOrder deletedEquipmentOrder) {
		for (DetailsOfEquipmentOrder d: deletedEquipmentOrder.getDetails()) {
			MedicalEquipment medEqu = medicalEquipmentService.findById(d.getEquipment().getId());
			medEqu.setAmount(medEqu.getAmount() + d.getAmount());
			medicalEquipmentService.save(MedicalEquipmentConverter.convertToDTO(medEqu));
		}
	}
	
	@Override
	public void penalizeProcurementManager(EquipmentOrder deletedEquipmentOrder) {
		Timestamp startingTimeOfTerm = deletedEquipmentOrder.getExchangeTerm().getStartingTime();
		Date currentTime = Calendar.getInstance().getTime();
		long differenceBetweenStartingTimeOfTermAndCurrentTime = 
				exchangeTermService.calculateDifferenceBetweenTimestamps(
						startingTimeOfTerm, currentTime);
		
		int numberOfPenaltyPoints = 0;
		if (differenceBetweenStartingTimeOfTermAndCurrentTime > ONE_DAY_IN_MILLISECONDS) {
			numberOfPenaltyPoints = 1;
		} else {
			numberOfPenaltyPoints = 2;
		}
		
		procurementManagerService.penalizeWith(
				deletedEquipmentOrder.getProcurementManager().getId(), numberOfPenaltyPoints);
	}
}
