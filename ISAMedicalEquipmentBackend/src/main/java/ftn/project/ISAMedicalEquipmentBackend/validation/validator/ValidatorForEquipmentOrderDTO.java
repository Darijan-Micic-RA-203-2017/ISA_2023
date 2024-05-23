package ftn.project.ISAMedicalEquipmentBackend.validation.validator;

import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ftn.project.ISAMedicalEquipmentBackend.dto.order.DetailsOfEquipmentOrderDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.order.EquipmentOrderDTO;

/** REFERENCE: https://docs.spring.io/spring-framework/reference/core/validation/validator.html */
public class ValidatorForEquipmentOrderDTO implements Validator {
	public ValidatorForEquipmentOrderDTO() {}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return EquipmentOrderDTO.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		EquipmentOrderDTO equipmentOrder = (EquipmentOrderDTO) target;
		
		long id = equipmentOrder.getId();
		if (id <= 0) {
			errors.rejectValue("id", "field.min", null, 
					"Id is not specified!");
		}
		
		long exchangeTermId = equipmentOrder.getExchangeTermId();
		if (exchangeTermId <= 0) {
			errors.rejectValue("exchangeTermId", "field.min", null, 
					"Exchange term id is not specified!");
		}
		
		long procurementManagerId = equipmentOrder.getProcurementManagerId();
		if (procurementManagerId <= 0) {
			errors.rejectValue("procurementManagerId", "field.min", null, 
					"Procurement manager id is not specified!");
		}
		
		List<DetailsOfEquipmentOrderDTO> details = equipmentOrder.getDetails();
		double totalPriceOfAllOrderDetails = validateDetailsOfOrder(details, errors);
		
		double totalPrice = equipmentOrder.getTotalPrice();
		if (Double.doubleToLongBits(totalPrice) <= 0) {
			errors.rejectValue("totalPrice", "field.min", null, 
					"Total price is not positive!");
		}
		if (Double.doubleToLongBits(totalPrice) != 
				Double.doubleToLongBits(totalPriceOfAllOrderDetails)) {
			errors.rejectValue("totalPrice", "field.value", null, 
					"Total price is not equal to sum of subtotal prices of details!");
		}
	}
	
	private double validateDetailsOfOrder(List<DetailsOfEquipmentOrderDTO> detailsOfOrder, 
			Errors errors) {
		double totalPriceOfAllOrderDetails = 0.0;
		
		ValidationUtils.rejectIfEmpty(errors, "details", 
				"field.required", "Details of order are null!");
		if (detailsOfOrder.isEmpty()) {
			errors.rejectValue("details", "field.empty", null, 
					"Order does not contain any equipment!");
		}
		
		for (int i = 0; i < detailsOfOrder.size(); i++) {
			long idOfOrderDetails = detailsOfOrder.get(i).getId();
			if (idOfOrderDetails <= 0) {
				errors.rejectValue("details[" + i + "].id", "field.value", null, 
						"Id of order's details is not specified!");
			}
			
			long orderIdOfOrderDetails = detailsOfOrder.get(i).getOrderId();
			if (orderIdOfOrderDetails <= 0) {
				errors.rejectValue("details[" + i + "].orderId", "field.value", null, 
						"Order id of order's details is not specified!");
			}
			
			long equipmentIdOfOrderDetails = detailsOfOrder.get(i).getEquipmentId();
			if (equipmentIdOfOrderDetails <= 0) {
				errors.rejectValue("details[" + i + "].equipmentId", "field.min", null, 
						"Equipment id of order's details is not specified!");
			}
			
			int amountOfOrderDetails = detailsOfOrder.get(i).getAmount();
			if (amountOfOrderDetails <= 0) {
				errors.rejectValue("details[" + i + "].amount", "field.min", null, 
						"Amount of order's details is not specified!");
			}
			
			double subtotalPriceOfOrderDetails = detailsOfOrder.get(i).getSubtotalPrice();
			if (Double.doubleToLongBits(subtotalPriceOfOrderDetails) <= 0) {
				errors.rejectValue("details[" + i + "].subtotalPrice", "field.min", null, 
						"Subtotal price of order's details is not positive!");
			}
			
			totalPriceOfAllOrderDetails += subtotalPriceOfOrderDetails;
		}
		
		return totalPriceOfAllOrderDetails;
	}
}
