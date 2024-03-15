package ftn.project.ISAMedicalEquipmentBackend.controller.order;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.WriterException;

import ftn.project.ISAMedicalEquipmentBackend.converter.order.EquipmentOrderConverter;
import ftn.project.ISAMedicalEquipmentBackend.dto.ObjectAndTextResponseDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.SimpleTextResponseDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.order.EquipmentOrderDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.order.OrderCreationDTO;
import ftn.project.ISAMedicalEquipmentBackend.exception.IncorrectSubtotalPriceOfOrderDetailsException;
import ftn.project.ISAMedicalEquipmentBackend.exception.NotEnoughEquipmentForOrderException;
import ftn.project.ISAMedicalEquipmentBackend.service.order.OrderingService;
import ftn.project.ISAMedicalEquipmentBackend.validation.ValidationPerformer;

@RestController
@RequestMapping(path = "/ordering", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderingController {
	private final OrderingService orderingService;
	
	@Autowired
	public OrderingController(OrderingService orderingService) {
		this.orderingService = orderingService;
	}
	
	@PostMapping(path = "/create-order", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ObjectAndTextResponseDTO> createOrder(
			@RequestBody OrderCreationDTO orderCreationDTO) {
		EquipmentOrderDTO createdEquipmentOrder = null;
		String validationMessages = ValidationPerformer.getValidationMessages(orderCreationDTO);
		if (validationMessages != null) {
			return new ResponseEntity<ObjectAndTextResponseDTO>(
					new ObjectAndTextResponseDTO(createdEquipmentOrder, validationMessages), 
					HttpStatus.BAD_REQUEST);
		}
		
		try {
			createdEquipmentOrder = EquipmentOrderConverter.convertToDTO(
					orderingService.createOrder(orderCreationDTO));
		} catch (NotEnoughEquipmentForOrderException | IncorrectSubtotalPriceOfOrderDetailsException e) {
			System.out.println("\n" + e.getMessage());
			
			return new ResponseEntity<ObjectAndTextResponseDTO>(
					new ObjectAndTextResponseDTO(createdEquipmentOrder, e.getMessage()), 
					HttpStatus.BAD_REQUEST);
		}
		
		System.out.println("\nNew equipment order and its associations (exchange term and details)" 
				+ " were successfully created.\n");
		
		return new ResponseEntity<ObjectAndTextResponseDTO>(
				new ObjectAndTextResponseDTO(createdEquipmentOrder, "New equipment order was successfully created."), 
				HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/generate-qr-code", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SimpleTextResponseDTO> generateQRCode(
			@RequestBody EquipmentOrderDTO newEquipmentOrder) {
		String validationMessages = ValidationPerformer.getValidationMessages(newEquipmentOrder);
		if (validationMessages != null) {
			return new ResponseEntity<SimpleTextResponseDTO>(
					new SimpleTextResponseDTO(validationMessages), 
					HttpStatus.BAD_REQUEST);
		}
		
		byte[] imageOfQRCodeAsByteArray = null;
		try {
			imageOfQRCodeAsByteArray = 
					orderingService.generateQRCodeOfNewEquipmentOrder(newEquipmentOrder);
		} catch (WriterException | IOException e) {
			e.printStackTrace();
			
			return new ResponseEntity<SimpleTextResponseDTO>(
					new SimpleTextResponseDTO("QR code of new equipment order was not generated due to an error!"), 
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		try {
			orderingService.sendEmailWithQRCodeOfNewOrder(newEquipmentOrder, imageOfQRCodeAsByteArray);
		} catch (MailException mE) {
			System.out.println("\nEmail message was not sent!\n");
			mE.printStackTrace();
			
			return new ResponseEntity<SimpleTextResponseDTO>(
					new SimpleTextResponseDTO("Email message was not sent due to an error!"), 
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		System.out.println("\nEmail message with QR code of new order was successfully sent.\n");
		
		return new ResponseEntity<SimpleTextResponseDTO>(
				new SimpleTextResponseDTO("QR code of new equipment order was successfully generated and sent to your email address."), 
				HttpStatus.OK);
	}
}
