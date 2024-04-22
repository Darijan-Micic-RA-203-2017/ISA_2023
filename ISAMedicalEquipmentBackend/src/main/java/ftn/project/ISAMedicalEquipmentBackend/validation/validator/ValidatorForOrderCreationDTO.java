package ftn.project.ISAMedicalEquipmentBackend.validation.validator;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ftn.project.ISAMedicalEquipmentBackend.dto.order.DetailsOfEquipmentOrderDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.order.EquipmentOrderDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.order.OrderCreationDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.term.ExchangeTermDTO;

public class ValidatorForOrderCreationDTO implements Validator {
	public ValidatorForOrderCreationDTO() {}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return OrderCreationDTO.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		OrderCreationDTO orderCreationDTO = (OrderCreationDTO) target;
		
		ValidationUtils.rejectIfEmpty(errors, "term", 
				"field.required", "Term is null!");
		ExchangeTermDTO term = orderCreationDTO.getTerm();
		if (term != null) {
			validateTerm(term, errors);
		}
		
		ValidationUtils.rejectIfEmpty(errors, "order", 
				"field.required", "Order is null!");
		EquipmentOrderDTO order = orderCreationDTO.getOrder();
		if (order != null) {
			validateOrder(order, errors);
		}
	}
	
	private void validateTerm(ExchangeTermDTO term, Errors errors) {
		long idOfTerm = term.getId();
		if (idOfTerm != 0) {
			errors.rejectValue("term.id", "field.value", null, 
					"Id of term is not 0!");
		}
		
		ValidationUtils.rejectIfEmpty(errors, "term.startingTime", 
				"field.required", "Starting time of term is empty!");
		Timestamp startingTimeOfTerm = term.getStartingTime();
		ValidationUtils.rejectIfEmpty(errors, "term.endingTime", 
				"field.required", "Ending time of term is empty!");
		Timestamp endingTimeOfTerm = term.getEndingTime();
		if (startingTimeOfTerm != null && endingTimeOfTerm != null) {
			if (startingTimeOfTerm.compareTo(endingTimeOfTerm) >= 0) {
				errors.rejectValue("term.startingTime", "field.max", null, 
						"Starting time of term is set after ending time!");
				errors.rejectValue("term.endingTime", "field.min", null, 
						"Ending time of term is set before starting time!");
			}
			
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			calendar.add(Calendar.DATE, 1);
			if (endingTimeOfTerm.compareTo(calendar.getTime()) < 0) {
				errors.rejectValue("term.endingTime", "field.min", null, 
						"Ending time of term is set before tomorrow!");
			}
		}
		
		long procurementManagerIdOfTerm = term.getProcurementManagerId();
		if (procurementManagerIdOfTerm <= 0) {
			errors.rejectValue("term.procurementManagerId", "field.min", null, 
					"Procurement manager id of term is not specified!");
		}
		
		long companyIdOfTerm = term.getCompanyId();
		if (companyIdOfTerm <= 0) {
			errors.rejectValue("term.companyId", "field.min", null, 
					"Company id of term is not specified!");
		}
		
		long companyAdministratorIdOfTerm = term.getCompanyAdministratorId();
		if (companyAdministratorIdOfTerm != 0) {
			errors.rejectValue("term.companyAdministratorId", "field.value", null, 
					"Company administrator id of exchange term is not 0!");
		}
	}
	
	private void validateOrder(EquipmentOrderDTO order, Errors errors) {
		long idOfOrder = order.getId();
		if (idOfOrder != 0) {
			errors.rejectValue("order.id", "field.value", null, 
					"Id of order is not 0!");
		}
		
		long termIdOfOrder = order.getExchangeTermId();
		if (termIdOfOrder != 0) {
			errors.rejectValue("order.exchangeTermId", "field.value", null, 
					"Exchange term id of order is not 0!");
		}
		
		long procurementManagerIdOfOrder = order.getProcurementManagerId();
		if (procurementManagerIdOfOrder <= 0) {
			errors.rejectValue("order.procurementManagerId", "field.min", null, 
					"Procurement manager id of order is not specified!");
		}
		
		List<DetailsOfEquipmentOrderDTO> detailsOfOrder = order.getDetails();
		double totalPriceOfAllOrderDetails = validateDetailsOfOrder(detailsOfOrder, errors);
		
		double totalPriceOfOrder = order.getTotalPrice();
		if (Double.doubleToLongBits(totalPriceOfOrder) <= 0) {
			errors.rejectValue("order.totalPrice", "field.min", null, 
					"Total price of order is not positive!");
		}
		if (Double.doubleToLongBits(totalPriceOfOrder) != 
				Double.doubleToLongBits(totalPriceOfAllOrderDetails)) {
			errors.rejectValue("order.totalPrice", "field.value", null, 
					"Total price of order is not equal to sum of subtotal prices of details!");
		}
	}
	
	private double validateDetailsOfOrder(List<DetailsOfEquipmentOrderDTO> detailsOfOrder, 
			Errors errors) {
		double totalPriceOfAllOrderDetails = 0.0;
		
		ValidationUtils.rejectIfEmpty(errors, "order.details", 
				"field.required", "Details of order are null!");
		if (detailsOfOrder.isEmpty()) {
			errors.rejectValue("order.details", "field.empty", null, 
					"Order does not contain any equipment!");
		}
		
		for (int i = 0; i < detailsOfOrder.size(); i++) {
			long idOfOrderDetails = detailsOfOrder.get(i).getId();
			if (idOfOrderDetails != 0) {
				errors.rejectValue("order.details[" + i + "].id", "field.value", null, 
						"Id of order's details is not 0!");
			}
			
			long orderIdOfOrderDetails = detailsOfOrder.get(i).getOrderId();
			if (orderIdOfOrderDetails != 0) {
				errors.rejectValue("order.details[" + i + "].orderId", "field.value", null, 
						"Order id of order's details is not 0!");
			}
			
			long equipmentIdOfOrderDetails = detailsOfOrder.get(i).getEquipmentId();
			if (equipmentIdOfOrderDetails <= 0) {
				errors.rejectValue("order.details[" + i + "].equipmentId", "field.min", null, 
						"Equipment id of order's details is not specified!");
			}
			
			int amountOfOrderDetails = detailsOfOrder.get(i).getAmount();
			if (amountOfOrderDetails <= 0) {
				errors.rejectValue("order.details[" + i + "].amount", "field.min", null, 
						"Amount of order's details is not specified!");
			}
			
			double subtotalPriceOfOrderDetails = detailsOfOrder.get(i).getSubtotalPrice();
			if (Double.doubleToLongBits(subtotalPriceOfOrderDetails) <= 0) {
				errors.rejectValue("order.details[" + i + "].subtotalPrice", "field.min", null, 
						"Subtotal price of order's details is not positive!");
			}
			
			totalPriceOfAllOrderDetails += subtotalPriceOfOrderDetails;
		}
		
		return totalPriceOfAllOrderDetails;
	}
}
