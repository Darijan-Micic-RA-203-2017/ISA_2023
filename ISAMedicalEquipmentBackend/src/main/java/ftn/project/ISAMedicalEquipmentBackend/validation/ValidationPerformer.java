package ftn.project.ISAMedicalEquipmentBackend.validation;

import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;

import ftn.project.ISAMedicalEquipmentBackend.dto.ProcurementManagerRegistrationReqDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.SearchCriterionDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.UserCodeWrapperDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.order.OrderCreationDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.term.ExchangeTermDTO;
import ftn.project.ISAMedicalEquipmentBackend.validation.validator.ValidatorForExchangeTermDTO;
import ftn.project.ISAMedicalEquipmentBackend.validation.validator.ValidatorForOrderCreationDTO;
import ftn.project.ISAMedicalEquipmentBackend.validation.validator.ValidatorForProcurementManagerRegistrationReqDTO;
import ftn.project.ISAMedicalEquipmentBackend.validation.validator.ValidatorForSearchCriterionDTO;
import ftn.project.ISAMedicalEquipmentBackend.validation.validator.ValidatorForUserCodeWrapperDTO;

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
		if (target.getClass().isAssignableFrom(ProcurementManagerRegistrationReqDTO.class)) {
			ValidationUtils.invokeValidator(new ValidatorForProcurementManagerRegistrationReqDTO(), 
					target, bindingResult);
		} else if (target.getClass().isAssignableFrom(UserCodeWrapperDTO.class)) {
			ValidationUtils.invokeValidator(new ValidatorForUserCodeWrapperDTO(), 
					target, bindingResult);
		} else if (target.getClass().isAssignableFrom(SearchCriterionDTO.class)) {
			ValidationUtils.invokeValidator(new ValidatorForSearchCriterionDTO(), 
					target, bindingResult);
		} else if (target.getClass().isAssignableFrom(ExchangeTermDTO.class)) {
			ValidationUtils.invokeValidator(new ValidatorForExchangeTermDTO(), 
					target, bindingResult);
		} else if (target.getClass().isAssignableFrom(OrderCreationDTO.class)) {
			ValidationUtils.invokeValidator(new ValidatorForOrderCreationDTO(), 
					target, bindingResult);
		}
	}
}
