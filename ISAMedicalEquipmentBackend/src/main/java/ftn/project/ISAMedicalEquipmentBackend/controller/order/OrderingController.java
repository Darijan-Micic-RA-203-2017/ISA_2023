package ftn.project.ISAMedicalEquipmentBackend.controller.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.project.ISAMedicalEquipmentBackend.converter.order.EquipmentOrderConverter;
import ftn.project.ISAMedicalEquipmentBackend.dto.ObjectAndTextResponseDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.order.EquipmentOrderDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.order.OrderCreationDTO;
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
		} catch (NotEnoughEquipmentForOrderException nEEFOE) {
			return new ResponseEntity<ObjectAndTextResponseDTO>(
					new ObjectAndTextResponseDTO(createdEquipmentOrder, 
							"There is not enough equipment for order!"), 
					HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<ObjectAndTextResponseDTO>(
				new ObjectAndTextResponseDTO(createdEquipmentOrder, 
						"New equipment order has been successfully created!"), 
				HttpStatus.CREATED);
	}
}
