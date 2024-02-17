package ftn.project.ISAMedicalEquipmentBackend.converter.order;

import java.util.ArrayList;
import java.util.List;

import ftn.project.ISAMedicalEquipmentBackend.domain.order.DetailsOfEquipmentOrder;
import ftn.project.ISAMedicalEquipmentBackend.dto.order.DetailsOfEquipmentOrderDTO;

public class DetailsOfEquipmentOrderConverter {
	public DetailsOfEquipmentOrderConverter() {}
	
	public static List<DetailsOfEquipmentOrderDTO> convertToDTOsList(
			List<DetailsOfEquipmentOrder> detailsOfEquipmentOrders) {
		if (detailsOfEquipmentOrders == null) {
			return null;
		}
		
		List<DetailsOfEquipmentOrderDTO> dtosList = new ArrayList<DetailsOfEquipmentOrderDTO>();
		for (DetailsOfEquipmentOrder d: detailsOfEquipmentOrders) {
			dtosList.add(convertToDTO(d));
		}
		
		return dtosList;
	}
	
	public static DetailsOfEquipmentOrderDTO convertToDTO(
			DetailsOfEquipmentOrder detailsOfequipmentOrder) {
		if (detailsOfequipmentOrder == null) {
			return null;
		}
		
		long id = detailsOfequipmentOrder.getId();
		long orderId = detailsOfequipmentOrder.getOrder().getId();
		long equipmentId = detailsOfequipmentOrder.getEquipment().getId();
		int amount = detailsOfequipmentOrder.getAmount();
		double subtotalPrice = detailsOfequipmentOrder.getSubtotalPrice();
		
		DetailsOfEquipmentOrderDTO dto = new DetailsOfEquipmentOrderDTO(id, orderId, equipmentId, 
				amount, subtotalPrice);
		
		return dto;
	}
}
