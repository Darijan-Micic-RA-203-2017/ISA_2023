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

import ftn.project.ISAMedicalEquipmentBackend.domain.company.MedicalEquipmentCompany;
import ftn.project.ISAMedicalEquipmentBackend.dto.SearchCriterionDTO;
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
	public ResponseEntity<List<MedicalEquipmentCompany>> findAll() {
		List<MedicalEquipmentCompany> allMedicalEquipmentCompanies = 
				medicalEquipmentCompanyService.findAll();
		
		return new ResponseEntity<List<MedicalEquipmentCompany>>(allMedicalEquipmentCompanies, 
				HttpStatus.OK);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<MedicalEquipmentCompany> findById(@PathVariable(name = "id") String id) {
		MedicalEquipmentCompany existingMedicalEquipmentCompany = null;
		
		long idAsLong = 0;
		try {
			idAsLong = Long.parseLong(id);
		} catch (NumberFormatException nFE) {
			return new ResponseEntity<MedicalEquipmentCompany>(existingMedicalEquipmentCompany, 
					HttpStatus.BAD_REQUEST);
		}
		
		existingMedicalEquipmentCompany = medicalEquipmentCompanyService.findById(idAsLong);
		if (existingMedicalEquipmentCompany == null) {
			return new ResponseEntity<MedicalEquipmentCompany>(existingMedicalEquipmentCompany, 
					HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<MedicalEquipmentCompany>(existingMedicalEquipmentCompany, 
				HttpStatus.OK);
	}
	
	@PostMapping(path = "/search-by-name-or-populated-place", 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MedicalEquipmentCompany>> searchByNameOrPopulatedPlace(
			@RequestBody SearchCriterionDTO searchCriterionDTO) {
		List<MedicalEquipmentCompany> companies = null;
		
		String validationMessages = ValidationPerformer.getValidationMessages(searchCriterionDTO);
		if (validationMessages != null) {
			return new ResponseEntity<List<MedicalEquipmentCompany>>(companies, 
					HttpStatus.BAD_REQUEST);
		}
		
		companies = medicalEquipmentCompanyService.searchByNameOrPopulatedPlace(searchCriterionDTO);
		
		return new ResponseEntity<List<MedicalEquipmentCompany>>(companies, 
				HttpStatus.OK);
	}
}
