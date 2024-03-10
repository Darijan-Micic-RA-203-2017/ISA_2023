package ftn.project.ISAMedicalEquipmentBackend.controller.order;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.WriterException;

import ftn.project.ISAMedicalEquipmentBackend.converter.order.EquipmentOrderConverter;
import ftn.project.ISAMedicalEquipmentBackend.dto.ObjectAndTextResponseDTO;
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
		
		return new ResponseEntity<ObjectAndTextResponseDTO>(
				new ObjectAndTextResponseDTO(createdEquipmentOrder, 
						"New equipment order has been successfully created!"), 
				HttpStatus.CREATED);
	}
	
	// REFERENCE: https://stackoverflow.com/questions/59786720/spring-convert-buffered-image-into-response-entity/59787932#59787932
	@PostMapping(path = "/generate-qr-code", consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<byte[]> generateQRCode(@RequestBody EquipmentOrderDTO newEquipmentOrder) {
		byte[] imageOfQRCodeAsByteArray = null;
		try {
			imageOfQRCodeAsByteArray = 
					orderingService.generateQRCodeOfNewEquipmentOrder(newEquipmentOrder);
		} catch (WriterException | IOException e) {
			e.printStackTrace();
			
			return new ResponseEntity<byte[]>(imageOfQRCodeAsByteArray, 
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<byte[]>(imageOfQRCodeAsByteArray, HttpStatus.OK);
	}
}
