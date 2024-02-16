package ftn.project.ISAMedicalEquipmentBackend.validation.validator;

import java.sql.Timestamp;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ftn.project.ISAMedicalEquipmentBackend.dto.term.ExchangeTermDTO;

public class ValidatorForExchangeTermDTO implements Validator {
	public ValidatorForExchangeTermDTO() {}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return ExchangeTermDTO.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		ExchangeTermDTO exchangeTermDTO = (ExchangeTermDTO) target;
		
		ValidationUtils.rejectIfEmpty(errors, "startingTime", 
				"field.required", "Starting time is empty!");
		Timestamp startingTime = exchangeTermDTO.getStartingTime();
		ValidationUtils.rejectIfEmpty(errors, "endingTime", 
				"field.required", "Ending time is empty!");
		Timestamp endingTime = exchangeTermDTO.getEndingTime();
		if (startingTime != null && endingTime != null) {
			if (startingTime.compareTo(endingTime) >= 0) {
				errors.rejectValue("startingTime", "field.max", null, 
						"Starting time is set after ending time!");
			}
			
			if (endingTime.compareTo(startingTime) <= 0) {
				errors.rejectValue("endingTime", "field.min", null, 
						"Ending time is set before starting time!");
			}
		}
		
		long procurementManagerId = exchangeTermDTO.getProcurementManagerId();
		if (procurementManagerId <= 0) {
			errors.rejectValue("procurementManagerId", "field.min", null, 
					"Procurement manager id is not specified!");
		}
		
		long companyId = exchangeTermDTO.getCompanyId();
		if (companyId <= 0) {
			errors.rejectValue("companyId", "field.min", null, 
					"Company id is not specified!");
		}
		
		long administratorId = exchangeTermDTO.getAdministratorId();
		if (administratorId < 0) {
			errors.rejectValue("administratorId", "field.min", null, 
					"Administrator id is negative!");
		}
	}
}
