package ftn.project.ISAMedicalEquipmentBackend.validation.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ftn.project.ISAMedicalEquipmentBackend.dto.SearchCriterionDTO;

public class ValidatorForSearchCriterionDTO implements Validator {
	public ValidatorForSearchCriterionDTO() {}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return SearchCriterionDTO.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "criterion", 
				"field.required", "Criterion is empty!");
	}
}
