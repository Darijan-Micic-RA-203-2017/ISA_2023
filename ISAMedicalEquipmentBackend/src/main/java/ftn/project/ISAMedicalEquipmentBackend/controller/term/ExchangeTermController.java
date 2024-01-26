package ftn.project.ISAMedicalEquipmentBackend.controller.term;

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

import ftn.project.ISAMedicalEquipmentBackend.converter.term.ExchangeTermConverter;
import ftn.project.ISAMedicalEquipmentBackend.domain.term.ExchangeTerm;
import ftn.project.ISAMedicalEquipmentBackend.dto.DateTimeWrapperDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.term.ExchangeTermDTO;
import ftn.project.ISAMedicalEquipmentBackend.service.term.ExchangeTermService;

@RestController
@RequestMapping(path = "/exchange-terms", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExchangeTermController {
	private final ExchangeTermService exchangeTermService;
	
	@Autowired
	public ExchangeTermController(ExchangeTermService exchangeTermService) {
		this.exchangeTermService = exchangeTermService;
	}
	
	@GetMapping(path = "")
	public ResponseEntity<List<ExchangeTermDTO>> findAll() {
		List<ExchangeTerm> allExchangeTerms = exchangeTermService.findAll();
		
		return new ResponseEntity<List<ExchangeTermDTO>>(
				ExchangeTermConverter.convertToDTOsList(allExchangeTerms), 
				HttpStatus.OK);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<ExchangeTermDTO> findById(@PathVariable(name = "id") String id) {
		ExchangeTermDTO exchangeTerm = null;
		
		long idAsLong = 0;
		try {
			idAsLong = Long.parseLong(id);
		} catch (NumberFormatException nFE) {
			return new ResponseEntity<ExchangeTermDTO>(exchangeTerm, HttpStatus.BAD_REQUEST);
		}
		
		exchangeTerm = ExchangeTermConverter.convertToDTO(exchangeTermService.findById(idAsLong));
		
		return new ResponseEntity<ExchangeTermDTO>(exchangeTerm, HttpStatus.OK);
	}
	
	@PostMapping(path = "/on-specific-date", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ExchangeTermDTO>> findAllOnSpecificDate(
			@RequestBody DateTimeWrapperDTO dateWrapper) {
		List<ExchangeTermDTO> termsOnSpecificDate = ExchangeTermConverter.convertToDTOsList(
				exchangeTermService.findAllOnSpecificDate(dateWrapper.getDateTime()));
		
		return new ResponseEntity<List<ExchangeTermDTO>>(termsOnSpecificDate, 
				HttpStatus.OK);
	}
	
	@PostMapping(path = "/on-specific-date-of-company/{companyId}", 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ExchangeTermDTO>> findAllOnSpecificDateOfCompany(
			@PathVariable(name = "companyId") String companyId, 
			@RequestBody DateTimeWrapperDTO dateWrapper) {
		List<ExchangeTermDTO> termsOnSpecificDateOfCompany = null;
		
		long companyIdAsLong = 0;
		try {
			companyIdAsLong = Long.parseLong(companyId);
		} catch (NumberFormatException nFE) {
			return new ResponseEntity<List<ExchangeTermDTO>>(termsOnSpecificDateOfCompany, 
					HttpStatus.BAD_REQUEST);
		}
		
		List<ExchangeTerm> terms = exchangeTermService.findAllOnSpecificDateOfCompany(
				dateWrapper.getDateTime(), companyIdAsLong);
		termsOnSpecificDateOfCompany = ExchangeTermConverter.convertToDTOsList(terms);
		
		return new ResponseEntity<List<ExchangeTermDTO>>(termsOnSpecificDateOfCompany, 
				HttpStatus.OK);
	}
	
	@GetMapping(path = "/of-company/{companyId}")
	public ResponseEntity<List<ExchangeTermDTO>> findAllOfCompany(
			@PathVariable(name = "companyId") String companyId) {
		List<ExchangeTermDTO> termsOfCompany = null;
		
		long companyIdAsLong = 0;
		try {
			companyIdAsLong = Long.parseLong(companyId);
		} catch (NumberFormatException nFE) {
			return new ResponseEntity<List<ExchangeTermDTO>>(termsOfCompany, HttpStatus.BAD_REQUEST);
		}
		
		termsOfCompany = ExchangeTermConverter.convertToDTOsList(
				exchangeTermService.findAllOfCompany(companyIdAsLong));
		
		return new ResponseEntity<List<ExchangeTermDTO>>(termsOfCompany, 
				HttpStatus.OK);
	}
}
