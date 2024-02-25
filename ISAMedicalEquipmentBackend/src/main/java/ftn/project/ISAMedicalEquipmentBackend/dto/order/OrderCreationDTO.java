package ftn.project.ISAMedicalEquipmentBackend.dto.order;

import ftn.project.ISAMedicalEquipmentBackend.dto.term.ExchangeTermDTO;

public class OrderCreationDTO {
	private ExchangeTermDTO exchangeTerm;
	private EquipmentOrderDTO order;
	
	public OrderCreationDTO() {}
	
	public OrderCreationDTO(ExchangeTermDTO exchangeTerm, EquipmentOrderDTO order) {
		this.exchangeTerm = exchangeTerm;
		this.order = order;
	}
	
	public ExchangeTermDTO getExchangeTerm() {
		return exchangeTerm;
	}
	
	public void setExchangeTerm(ExchangeTermDTO exchangeTerm) {
		this.exchangeTerm = exchangeTerm;
	}
	
	public EquipmentOrderDTO getOrder() {
		return order;
	}
	
	public void setOrder(EquipmentOrderDTO order) {
		this.order = order;
	}
}
