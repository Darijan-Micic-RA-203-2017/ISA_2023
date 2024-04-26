package ftn.project.ISAMedicalEquipmentBackend.controller.equipment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.project.ISAMedicalEquipmentBackend.converter.equipment.MedicalEquipmentConverter;
import ftn.project.ISAMedicalEquipmentBackend.domain.equipment.MedicalEquipment;
import ftn.project.ISAMedicalEquipmentBackend.dto.SearchCriterionDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.equipment.MedicalEquipmentDTO;
import ftn.project.ISAMedicalEquipmentBackend.service.equipment.MedicalEquipmentService;
import ftn.project.ISAMedicalEquipmentBackend.validation.ValidationPerformer;

@RestController
@RequestMapping(path = "/medical-equipment", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicalEquipmentController {
	private final MedicalEquipmentService medicalEquipmentService;
	
	@Autowired
	public MedicalEquipmentController(
			MedicalEquipmentService medicalEquipmentService) {
		this.medicalEquipmentService = medicalEquipmentService;
	}
	
	@GetMapping(path = "")
	public ResponseEntity<List<MedicalEquipmentDTO>> findAll() {
		List<MedicalEquipment> allMedicalEquipment = medicalEquipmentService.findAll();
		
		return new ResponseEntity<List<MedicalEquipmentDTO>>(
				MedicalEquipmentConverter.convertToDTOsList(allMedicalEquipment), 
				HttpStatus.OK);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<MedicalEquipmentDTO> findById(@PathVariable(name = "id") String id) {
		MedicalEquipmentDTO equipment = null;
		
		long idAsLong = 0;
		try {
			idAsLong = Long.parseLong(id);
		} catch (NumberFormatException nFE) {
			return new ResponseEntity<MedicalEquipmentDTO>(equipment, HttpStatus.BAD_REQUEST);
		}
		
		equipment = MedicalEquipmentConverter.convertToDTO(
				medicalEquipmentService.findById(idAsLong));
		
		return new ResponseEntity<MedicalEquipmentDTO>(equipment, HttpStatus.OK);
	}
	
	@GetMapping(path = "/of-company/{companyName}")
	public ResponseEntity<List<MedicalEquipmentDTO>> findByCompanyName(
			@PathVariable(name = "companyName") String companyName) {
		List<MedicalEquipmentDTO> allEquipmentOfSpecifiedCompany = null;
		if (companyName.isEmpty()) {
			return new ResponseEntity<List<MedicalEquipmentDTO>>(allEquipmentOfSpecifiedCompany, 
					HttpStatus.BAD_REQUEST);
		}
		
		allEquipmentOfSpecifiedCompany = MedicalEquipmentConverter.convertToDTOsList(
				medicalEquipmentService.findByCompanyName(companyName));
		
		return new ResponseEntity<List<MedicalEquipmentDTO>>(allEquipmentOfSpecifiedCompany, 
				HttpStatus.OK);
	}
	
	@PostMapping(path = "/search-by-name", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MedicalEquipmentDTO>> searchEquipmentByName(
			@RequestBody SearchCriterionDTO searchCriterionDTO) {
		List<MedicalEquipmentDTO> equipment = null;
		
		String validationMessages = ValidationPerformer.getValidationMessages(searchCriterionDTO);
		if (validationMessages != null) {
			return new ResponseEntity<List<MedicalEquipmentDTO>>(equipment, HttpStatus.BAD_REQUEST);
		}
		
		equipment = MedicalEquipmentConverter.convertToDTOsList(
				medicalEquipmentService.searchEquipmentByName(searchCriterionDTO));
		
		return new ResponseEntity<List<MedicalEquipmentDTO>>(equipment, HttpStatus.OK);
	}
	
	@PostMapping(path = "/search-by-name/of-company/{companyName}", 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MedicalEquipmentDTO>> searchEquipmentOfCompanyByName(
			@PathVariable(name = "companyName") String companyName, 
			@RequestBody SearchCriterionDTO searchCriterionDTO) {
		List<MedicalEquipmentDTO> equipment = null;
		if (companyName.isEmpty()) {
			return new ResponseEntity<List<MedicalEquipmentDTO>>(equipment, HttpStatus.BAD_REQUEST);
		}
		
		String validationMessages = ValidationPerformer.getValidationMessages(searchCriterionDTO);
		if (validationMessages != null) {
			return new ResponseEntity<List<MedicalEquipmentDTO>>(equipment, HttpStatus.BAD_REQUEST);
		}
		
		equipment = MedicalEquipmentConverter.convertToDTOsList(
				medicalEquipmentService.searchEquipmentOfCompanyByName(searchCriterionDTO, 
						companyName));
		
		return new ResponseEntity<List<MedicalEquipmentDTO>>(equipment, HttpStatus.OK);
	}
}
