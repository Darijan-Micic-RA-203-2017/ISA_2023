package ftn.project.ISAMedicalEquipmentBackend.controller.equipment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.project.ISAMedicalEquipmentBackend.converter.equipment.TypeOfMedicalEquipmentConverter;
import ftn.project.ISAMedicalEquipmentBackend.domain.equipment.TypeOfMedicalEquipment;
import ftn.project.ISAMedicalEquipmentBackend.dto.equipment.TypeOfMedicalEquipmentDTO;
import ftn.project.ISAMedicalEquipmentBackend.service.equipment.TypeOfMedicalEquipmentService;

@RestController
@RequestMapping(path = "/types-of-medical-equipment", produces = MediaType.APPLICATION_JSON_VALUE)
public class TypeOfMedicalEquipmentController {
	private final TypeOfMedicalEquipmentService typeOfMedicalEquipmentService;
	
	@Autowired
	public TypeOfMedicalEquipmentController(
			TypeOfMedicalEquipmentService typeOfMedicalEquipmentService) {
		this.typeOfMedicalEquipmentService = typeOfMedicalEquipmentService;
	}
	
	@GetMapping(path = "")
	public ResponseEntity<List<TypeOfMedicalEquipmentDTO>> findAll() {
		List<TypeOfMedicalEquipment> allTypesOfMedicalEquipment = 
				typeOfMedicalEquipmentService.findAll();
		
		return new ResponseEntity<List<TypeOfMedicalEquipmentDTO>>(
				TypeOfMedicalEquipmentConverter.convertToDTOsList(allTypesOfMedicalEquipment), 
				HttpStatus.OK);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<TypeOfMedicalEquipmentDTO> findById(@PathVariable(name = "id") String id) {
		TypeOfMedicalEquipmentDTO typeOfMedicalEquipment = null;
		
		long idAsLong = 0;
		try {
			idAsLong = Long.parseLong(id);
		} catch (NumberFormatException nFE) {
			return new ResponseEntity<TypeOfMedicalEquipmentDTO>(typeOfMedicalEquipment, 
					HttpStatus.BAD_REQUEST);
		}
		
		typeOfMedicalEquipment = TypeOfMedicalEquipmentConverter.convertToDTO(
				typeOfMedicalEquipmentService.findById(idAsLong));
		
		return new ResponseEntity<TypeOfMedicalEquipmentDTO>(typeOfMedicalEquipment, HttpStatus.OK);
	}
}
