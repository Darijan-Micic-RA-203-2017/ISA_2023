package ftn.project.ISAMedicalEquipmentBackend.dto.order;

import java.util.List;

public class EquipmentOrderDTO {
	private long id;
	private long exchangeTermId;
	private long procurementManagerId;
	private List<DetailsOfEquipmentOrderDTO> details;
	private double totalPrice;
	
	public EquipmentOrderDTO() {}
	
	public EquipmentOrderDTO(long id, long exchangeTermId, long procurementManagerId, 
			List<DetailsOfEquipmentOrderDTO> details, double totalPrice) {
		this.id = id;
		this.exchangeTermId = exchangeTermId;
		this.procurementManagerId = procurementManagerId;
		this.details = details;
		this.totalPrice = totalPrice;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getExchangeTermId() {
		return exchangeTermId;
	}
	
	public void setExchangeTermId(long exchangeTermId) {
		this.exchangeTermId = exchangeTermId;
	}
	
	public long getProcurementManagerId() {
		return procurementManagerId;
	}
	
	public void setProcurementManagerId(long procurementManagerId) {
		this.procurementManagerId = procurementManagerId;
	}
	
	public List<DetailsOfEquipmentOrderDTO> getDetails() {
		return details;
	}
	
	public void setDetails(List<DetailsOfEquipmentOrderDTO> details) {
		this.details = details;
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("EquipmentOrderDTO [id=").append(id).append(", exchangeTermId=")
				.append(exchangeTermId).append(", procurementManagerId=")
				.append(procurementManagerId).append(", details=").append(details)
				.append(", totalPrice=").append(totalPrice).append("]");
		
		return builder.toString();
	}
}
