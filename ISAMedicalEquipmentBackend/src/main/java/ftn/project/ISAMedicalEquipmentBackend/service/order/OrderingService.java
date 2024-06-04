package ftn.project.ISAMedicalEquipmentBackend.service.order;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.springframework.mail.MailException;

import com.google.zxing.WriterException;

import ftn.project.ISAMedicalEquipmentBackend.domain.equipment.MedicalEquipment;
import ftn.project.ISAMedicalEquipmentBackend.domain.order.EquipmentOrder;
import ftn.project.ISAMedicalEquipmentBackend.dto.order.DetailsOfEquipmentOrderDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.order.EquipmentOrderDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.order.OrderCreationDTO;
import ftn.project.ISAMedicalEquipmentBackend.exception.IncorrectSubtotalPriceOfOrderDetailsException;
import ftn.project.ISAMedicalEquipmentBackend.exception.NotEnoughEquipmentForOrderException;
import ftn.project.ISAMedicalEquipmentBackend.exception.PastTermDeletionException;

public interface OrderingService {
	static final long ONE_DAY_IN_MILLISECONDS = 86400000;
	
	EquipmentOrder createOrder(OrderCreationDTO orderCreationDTO) throws 
			NotEnoughEquipmentForOrderException, IncorrectSubtotalPriceOfOrderDetailsException;
	boolean isThereEnoughEquipmentForOrder(MedicalEquipment equipment, int requestedAmountInOrder);
	boolean doesSpecifiedSubtotalPriceMatchTheExactOne(
			DetailsOfEquipmentOrderDTO detailsOfOrder, double priceOfEquipment);
	byte[] generateQRCodeOfNewEquipmentOrder(EquipmentOrderDTO newEquipmentOrder) 
			throws WriterException, IOException;
	BufferedImage generateImageOfQRCode(String barcodeText) throws WriterException;
	void sendEmailWithQRCodeOfNewOrder(EquipmentOrderDTO newEquipmentOrder, 
			byte[] imageOfQRCodeAsByteArray) throws MailException;
	boolean deleteOrderByExchangeTermId(long exchangeTermId) throws PastTermDeletionException;
	void restoreAmountsOfMedicalEquipment(EquipmentOrder deletedEquipmentOrder);
	void penalizeProcurementManager(EquipmentOrder deletedEquipmentOrder);
}
