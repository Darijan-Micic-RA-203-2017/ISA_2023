package ftn.project.ISAMedicalEquipmentBackend.domain.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ftn.project.ISAMedicalEquipmentBackend.domain.equipment.MedicalEquipment;

@Entity
@Table(name = "details_of_equipment_orders")
public class DetailsOfEquipmentOrder implements Comparable<DetailsOfEquipmentOrder> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false, columnDefinition = "bigserial")
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private EquipmentOrder order;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "equipment_id")
	private MedicalEquipment equipment;
	
	@Column(name = "amount", nullable = false)
	private int amount;
	
	@Column(name = "subtotal_price", nullable = false)
	private double subtotalPrice;
	
	public DetailsOfEquipmentOrder() {}
	
	public DetailsOfEquipmentOrder(long id, EquipmentOrder order, MedicalEquipment equipment, 
			int amount, double subtotalPrice) {
		this.id = id;
		this.order = order;
		this.equipment = equipment;
		this.amount = amount;
		this.subtotalPrice = subtotalPrice;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public EquipmentOrder getOrder() {
		return order;
	}
	
	public void setOrder(EquipmentOrder order) {
		this.order = order;
	}
	
	public MedicalEquipment getEquipment() {
		return equipment;
	}
	
	public void setEquipment(MedicalEquipment equipment) {
		this.equipment = equipment;
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
	public int hashCode() {
		final int prime = 61;
		int result = 1;
		
		result = prime * result + getAmount();
		long temp = Double.doubleToLongBits(getSubtotalPrice());
		result = prime * result + (int) (temp ^ (temp >>> 32));
		
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof DetailsOfEquipmentOrder)) {
			return false;
		}
		
		DetailsOfEquipmentOrder other = (DetailsOfEquipmentOrder) obj;
		
		if (getId() != other.getId()) {
			return false;
		}
		
		if (getAmount() != other.getAmount()) {
			return false;
		}
		
		if (Double.doubleToLongBits(getSubtotalPrice()) != 
				Double.doubleToLongBits(other.getSubtotalPrice())) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public int compareTo(DetailsOfEquipmentOrder o) {
		if (o == null) {
			throw new NullPointerException();
		}
		
		if (this == o) {
			return 0;
		}
		
		if (!(o instanceof DetailsOfEquipmentOrder)) {
			throw new ClassCastException();
		}
		
		DetailsOfEquipmentOrder other = (DetailsOfEquipmentOrder) o;
		
		if (getId() < o.getId()) {
			return -1;
		} else if (getId() > o.getId()) {
			return 1;
		}
		
		if (getAmount() < other.getAmount()) {
			return -1;
		} else if (getAmount() > o.getAmount()) {
			return 1;
		}
		
		if (Double.doubleToLongBits(getSubtotalPrice()) < 
				Double.doubleToLongBits(other.getSubtotalPrice())) {
			return -1;
		} else if (Double.doubleToLongBits(getSubtotalPrice()) > 
				Double.doubleToLongBits(o.getSubtotalPrice())) {
			return 1;
		}
		
		return 0;
	}
}
