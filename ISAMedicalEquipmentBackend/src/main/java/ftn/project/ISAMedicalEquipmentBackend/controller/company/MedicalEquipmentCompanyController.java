package ftn.project.ISAMedicalEquipmentBackend.controller.company;

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

import ftn.project.ISAMedicalEquipmentBackend.converter.company.MedicalEquipmentCompanyConverter;
import ftn.project.ISAMedicalEquipmentBackend.domain.company.MedicalEquipmentCompany;
import ftn.project.ISAMedicalEquipmentBackend.dto.SearchCriterionDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.company.MedicalEquipmentCompanyDTO;
import ftn.project.ISAMedicalEquipmentBackend.service.company.MedicalEquipmentCompanyService;
import ftn.project.ISAMedicalEquipmentBackend.validation.ValidationPerformer;

@RestController
@RequestMapping(path = "/medical-equipment-companies", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicalEquipmentCompanyController {
	private final MedicalEquipmentCompanyService medicalEquipmentCompanyService;
	
	@Autowired
	public MedicalEquipmentCompanyController(
			MedicalEquipmentCompanyService medicalEquipmentCompanyService) {
		this.medicalEquipmentCompanyService = medicalEquipmentCompanyService;
	}
	
	@GetMapping(path = "")
	public ResponseEntity<List<MedicalEquipmentCompanyDTO>> findAll() {
		List<MedicalEquipmentCompany> allMedicalEquipmentCompanies = 
				medicalEquipmentCompanyService.findAll();
		
		return new ResponseEntity<List<MedicalEquipmentCompanyDTO>>(
				MedicalEquipmentCompanyConverter.convertToDTOsList(allMedicalEquipmentCompanies), 
				HttpStatus.OK);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<MedicalEquipmentCompanyDTO> findById(@PathVariable(name = "id") String id) {
		MedicalEquipmentCompanyDTO company = null;
		
		long idAsLong = 0;
		try {
			idAsLong = Long.parseLong(id);
		} catch (NumberFormatException nFE) {
			return new ResponseEntity<MedicalEquipmentCompanyDTO>(company, HttpStatus.BAD_REQUEST);
		}
		
		company = MedicalEquipmentCompanyConverter.convertToDTO(
				medicalEquipmentCompanyService.findById(idAsLong));
		
		return new ResponseEntity<MedicalEquipmentCompanyDTO>(company, HttpStatus.OK);
	}
	
	@PostMapping(path = "/search-by-name-or-populated-place", 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MedicalEquipmentCompanyDTO>> searchByNameOrPopulatedPlace(
			@RequestBody SearchCriterionDTO searchCriterionDTO) {
		List<MedicalEquipmentCompanyDTO> companies = null;
		
		String validationMessages = ValidationPerformer.getValidationMessages(searchCriterionDTO);
		if (validationMessages != null) {
			return new ResponseEntity<List<MedicalEquipmentCompanyDTO>>(companies, 
					HttpStatus.BAD_REQUEST);
		}
		
		companies = MedicalEquipmentCompanyConverter.convertToDTOsList(
				medicalEquipmentCompanyService.searchByNameOrPopulatedPlace(searchCriterionDTO));
		
		return new ResponseEntity<List<MedicalEquipmentCompanyDTO>>(companies, HttpStatus.OK);
	}
}
