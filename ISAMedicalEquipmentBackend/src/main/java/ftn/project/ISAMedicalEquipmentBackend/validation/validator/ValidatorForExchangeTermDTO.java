package ftn.project.ISAMedicalEquipmentBackend.validation.validator;

import java.sql.Timestamp;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ftn.project.ISAMedicalEquipmentBackend.dto.term.ExchangeTermDTO;

/** REFERENCE: https://docs.spring.io/spring-framework/reference/core/validation/validator.html */
public class ValidatorForExchangeTermDTO implements Validator {
	public ValidatorForExchangeTermDTO() {}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return ExchangeTermDTO.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		ExchangeTermDTO exchangeTermDTO = (ExchangeTermDTO) target;
		
		long id = exchangeTermDTO.getId();
		if (id < 0) {
			errors.rejectValue("id", "field.min", null, 
					"Id is not nonnegative!");
		}
		
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
		
		long companyAdministratorId = exchangeTermDTO.getCompanyAdministratorId();
		if (companyAdministratorId < 0) {
			errors.rejectValue("companyAdministratorId", "field.min", null, 
					"Company administrator id is negative!");
		}
	}
}
