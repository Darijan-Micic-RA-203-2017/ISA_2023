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

import ftn.project.ISAMedicalEquipmentBackend.converter.order.EquipmentOrderConverter;
import ftn.project.ISAMedicalEquipmentBackend.domain.order.EquipmentOrder;
import ftn.project.ISAMedicalEquipmentBackend.dto.order.EquipmentOrderDTO;
import ftn.project.ISAMedicalEquipmentBackend.service.order.EquipmentOrderService;

@RestController
@RequestMapping(path = "/equipment-orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class EquipmentOrderController {
	private final EquipmentOrderService equipmentOrderService;
	
	@Autowired
	public EquipmentOrderController(EquipmentOrderService equipmentOrderService) {
		this.equipmentOrderService = equipmentOrderService;
	}
	
	@GetMapping(path = "")
	public ResponseEntity<List<EquipmentOrderDTO>> findAll() {
		List<EquipmentOrder> allEquipmentOrders = equipmentOrderService.findAll();
		
		return new ResponseEntity<List<EquipmentOrderDTO>>(
				EquipmentOrderConverter.convertToDTOsList(allEquipmentOrders), 
				HttpStatus.OK);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<EquipmentOrderDTO> findById(@PathVariable(name = "id") String id) {
		EquipmentOrderDTO equipmentOrder = null;
		
		long idAsLong = 0;
		try {
			idAsLong = Long.parseLong(id);
		} catch (NumberFormatException nFE) {
			return new ResponseEntity<EquipmentOrderDTO>(equipmentOrder, HttpStatus.BAD_REQUEST);
		}
		
		equipmentOrder = EquipmentOrderConverter.convertToDTO(
				equipmentOrderService.findById(idAsLong));
		
		return new ResponseEntity<EquipmentOrderDTO>(equipmentOrder, HttpStatus.OK);
	}
}
