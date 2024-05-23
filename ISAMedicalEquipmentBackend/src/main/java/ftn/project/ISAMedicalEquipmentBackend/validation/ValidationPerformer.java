package ftn.project.ISAMedicalEquipmentBackend.validation;

import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;

import ftn.project.ISAMedicalEquipmentBackend.dto.ProcurementManagerRegistrationReqDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.SearchCriterionDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.UserCodeWrapperDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.order.EquipmentOrderDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.order.OrderCreationDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.term.ExchangeTermDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.user.UserDTO;
import ftn.project.ISAMedicalEquipmentBackend.validation.validator.ValidatorForEquipmentOrderDTO;
import ftn.project.ISAMedicalEquipmentBackend.validation.validator.ValidatorForExchangeTermDTO;
import ftn.project.ISAMedicalEquipmentBackend.validation.validator.ValidatorForOrderCreationDTO;
import ftn.project.ISAMedicalEquipmentBackend.validation.validator.ValidatorForProcurementManagerRegistrationReqDTO;
import ftn.project.ISAMedicalEquipmentBackend.validation.validator.ValidatorForSearchCriterionDTO;
import ftn.project.ISAMedicalEquipmentBackend.validation.validator.ValidatorForUserCodeWrapperDTO;
import ftn.project.ISAMedicalEquipmentBackend.validation.validator.ValidatorForUserDTO;

/** REFERENCE: https://docs.spring.io/spring-framework/reference/core/validation/validator.html */
public class ValidationPerformer {
	public ValidationPerformer() {}
	
	public static String getValidationMessages(Object target) {
		BeanPropertyBindingResult bindingResult = performValidation(target);
		if (bindingResult.hasErrors()) {
			StringBuilder responseTextBuilder = new StringBuilder();
			for (ObjectError e: bindingResult.getAllErrors()) {
				responseTextBuilder.append(e.getDefaultMessage()).append("\n");
			}
			
			return responseTextBuilder.toString();
		}
		
		return null;
	}
	
	public static BeanPropertyBindingResult performValidation(Object target) {
		BeanPropertyBindingResult bindingResult = 
				new BeanPropertyBindingResult(target, target.getClass().getName());
		
		DefaultMessageCodesResolver messageCodesResolver = 
				(DefaultMessageCodesResolver) bindingResult.getMessageCodesResolver();
		messageCodesResolver.setMessageCodeFormatter(
				DefaultMessageCodesResolver.Format.POSTFIX_ERROR_CODE);
		
		invokeCorrespondingValidator(target, bindingResult);
		
		return bindingResult;
	}
	
	private static void invokeCorrespondingValidator(Object target, 
			BeanPropertyBindingResult bindingResult) {
		if (ProcurementManagerRegistrationReqDTO.class.isAssignableFrom(target.getClass())) {
			ValidationUtils.invokeValidator(new ValidatorForProcurementManagerRegistrationReqDTO(), 
					target, bindingResult);
		} else if (UserCodeWrapperDTO.class.isAssignableFrom(target.getClass())) {
			ValidationUtils.invokeValidator(new ValidatorForUserCodeWrapperDTO(), 
					target, bindingResult);
		} else if (SearchCriterionDTO.class.isAssignableFrom(target.getClass())) {
			ValidationUtils.invokeValidator(new ValidatorForSearchCriterionDTO(), 
					target, bindingResult);
		} else if (OrderCreationDTO.class.isAssignableFrom(target.getClass())) {
			ValidationUtils.invokeValidator(new ValidatorForOrderCreationDTO(), 
					target, bindingResult);
		} else if (ExchangeTermDTO.class.isAssignableFrom(target.getClass())) {
			ValidationUtils.invokeValidator(new ValidatorForExchangeTermDTO(), 
					target, bindingResult);
		} else if (EquipmentOrderDTO.class.isAssignableFrom(target.getClass())) {
			ValidationUtils.invokeValidator(new ValidatorForEquipmentOrderDTO(), 
					target, bindingResult);
		} else if (UserDTO.class.isAssignableFrom(target.getClass())) {
			ValidationUtils.invokeValidator(new ValidatorForUserDTO(), 
					target, bindingResult);
		}
	}
}
