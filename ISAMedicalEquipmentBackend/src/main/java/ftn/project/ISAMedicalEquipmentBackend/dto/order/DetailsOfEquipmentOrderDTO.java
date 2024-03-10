package ftn.project.ISAMedicalEquipmentBackend.dto.order;

public class DetailsOfEquipmentOrderDTO {
	private long id;
	private long orderId;
	private long equipmentId;
	private int amount;
	private double subtotalPrice;
	
	public DetailsOfEquipmentOrderDTO() {}
	
	public DetailsOfEquipmentOrderDTO(long id, long orderId, long equipmentId, int amount, 
			double subtotalPrice) {
		this.id = id;
		this.orderId = orderId;
		this.equipmentId = equipmentId;
		this.amount = amount;
		this.subtotalPrice = subtotalPrice;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getOrderId() {
		return orderId;
	}
	
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	
	public long getEquipmentId() {
		return equipmentId;
	}
	
	public void setEquipmentId(long equipmentId) {
		this.equipmentId = equipmentId;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public double getSubtotalPrice() {
		return subtotalPrice;
	}
	
	public void setSubtotalPrice(double subtotalPrice) {
		this.subtotalPrice = subtotalPrice;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("DetailsOfEquipmentOrderDTO [id=").append(id).append(", orderId=")
				.append(orderId).append(", equipmentId=").append(equipmentId).append(", amount=")
				.append(amount).append(", subtotalPrice=").append(subtotalPrice).append("]");
		
		return builder.toString();
	}
}
