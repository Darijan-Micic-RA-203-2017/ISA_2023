package ftn.project.ISAMedicalEquipmentBackend.converter.order;

import java.util.ArrayList;
import java.util.List;

import ftn.project.ISAMedicalEquipmentBackend.domain.order.DetailsOfEquipmentOrder;
import ftn.project.ISAMedicalEquipmentBackend.domain.order.EquipmentOrder;
import ftn.project.ISAMedicalEquipmentBackend.dto.order.DetailsOfEquipmentOrderDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.order.EquipmentOrderDTO;

public class EquipmentOrderConverter {
	public EquipmentOrderConverter() {}
	
	public static List<EquipmentOrderDTO> convertToDTOsList(List<EquipmentOrder> equipmentOrders) {
		if (equipmentOrders == null) {
			return null;
		}
		
		List<EquipmentOrderDTO> dtosList = new ArrayList<EquipmentOrderDTO>();
		for (EquipmentOrder o: equipmentOrders) {
			dtosList.add(convertToDTO(o));
		}
		
		return dtosList;
	}
	
	public static EquipmentOrderDTO convertToDTO(EquipmentOrder equipmentOrder) {
		if (equipmentOrder == null) {
			return null;
		}
		
		long id = equipmentOrder.getId();
		long exchangeTermId = equipmentOrder.getExchangeTerm().getId();
		long procurementManagerId = equipmentOrder.getProcurementManager().getId();
		List<DetailsOfEquipmentOrderDTO> details = new ArrayList<DetailsOfEquipmentOrderDTO>();
		for (DetailsOfEquipmentOrder d: equipmentOrder.getDetails()) {
			details.add(DetailsOfEquipmentOrderConverter.convertToDTO(d));
		}
		double totalPrice = equipmentOrder.getTotalPrice();
		
		EquipmentOrderDTO dto = new EquipmentOrderDTO(id, exchangeTermId, procurementManagerId, 
				details, totalPrice);
		
		return dto;
	}
}
