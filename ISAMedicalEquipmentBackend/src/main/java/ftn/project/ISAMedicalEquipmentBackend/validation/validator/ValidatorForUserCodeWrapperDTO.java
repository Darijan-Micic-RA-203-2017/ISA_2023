package ftn.project.ISAMedicalEquipmentBackend.validation.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ftn.project.ISAMedicalEquipmentBackend.dto.UserCodeWrapperDTO;

public class ValidatorForUserCodeWrapperDTO implements Validator {
	public ValidatorForUserCodeWrapperDTO() {}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return UserCodeWrapperDTO.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userCode", 
				"field.required", "User code is empty!");
	}
}
