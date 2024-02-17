package ftn.project.ISAMedicalEquipmentBackend.controller.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.project.ISAMedicalEquipmentBackend.converter.order.DetailsOfEquipmentOrderConverter;
import ftn.project.ISAMedicalEquipmentBackend.domain.order.DetailsOfEquipmentOrder;
import ftn.project.ISAMedicalEquipmentBackend.dto.order.DetailsOfEquipmentOrderDTO;
import ftn.project.ISAMedicalEquipmentBackend.service.order.DetailsOfEquipmentOrderService;

@RestController
@RequestMapping(path = "/details-of-equipment-orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class DetailsOfEquipmentOrderController {
	private final DetailsOfEquipmentOrderService detailsOfEquipmentOrderService;
	
	@Autowired
	public DetailsOfEquipmentOrderController(
			DetailsOfEquipmentOrderService detailsOfEquipmentOrderService) {
		this.detailsOfEquipmentOrderService = detailsOfEquipmentOrderService;
	}
	
	@GetMapping(path = "")
	public ResponseEntity<List<DetailsOfEquipmentOrderDTO>> findAll() {
		List<DetailsOfEquipmentOrder> allDetailsOfEquipmentOrders = 
				detailsOfEquipmentOrderService.findAll();
		
		return new ResponseEntity<List<DetailsOfEquipmentOrderDTO>>(
				DetailsOfEquipmentOrderConverter.convertToDTOsList(allDetailsOfEquipmentOrders), 
				HttpStatus.OK);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<DetailsOfEquipmentOrderDTO> findById(@PathVariable(name = "id") String id) {
		DetailsOfEquipmentOrderDTO detailsOfEquipmentOrder = null;
		
		long idAsLong = 0;
		try {
			idAsLong = Long.parseLong(id);
		} catch (NumberFormatException nFE) {
			return new ResponseEntity<DetailsOfEquipmentOrderDTO>(detailsOfEquipmentOrder, 
					HttpStatus.BAD_REQUEST);
		}
		
		detailsOfEquipmentOrder = DetailsOfEquipmentOrderConverter.convertToDTO(
				detailsOfEquipmentOrderService.findById(idAsLong));
		
		return new ResponseEntity<DetailsOfEquipmentOrderDTO>(detailsOfEquipmentOrder, HttpStatus.OK);
	}
}
