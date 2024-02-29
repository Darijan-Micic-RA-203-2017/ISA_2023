package ftn.project.ISAMedicalEquipmentBackend.dto.order;

import ftn.project.ISAMedicalEquipmentBackend.dto.term.ExchangeTermDTO;

public class OrderCreationDTO {
	private ExchangeTermDTO term;
	private EquipmentOrderDTO order;
	
	public OrderCreationDTO() {}
	
	public OrderCreationDTO(ExchangeTermDTO term, EquipmentOrderDTO order) {
		this.term = term;
		this.order = order;
	}
	
	public ExchangeTermDTO getTerm() {
		return term;
	}
	
	public void setTerm(ExchangeTermDTO term) {
		this.term = term;
	}
	
	public EquipmentOrderDTO getOrder() {
		return order;
	}
	
	public void setOrder(EquipmentOrderDTO order) {
		this.order = order;
	}
}
