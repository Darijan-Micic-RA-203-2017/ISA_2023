package ftn.project.ISAMedicalEquipmentBackend.domain.order;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ftn.project.ISAMedicalEquipmentBackend.domain.term.ExchangeTerm;
import ftn.project.ISAMedicalEquipmentBackend.domain.user.ProcurementManagerOfHospital;

@Entity
@Table(name = "equipment_orders")
public class EquipmentOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false, columnDefinition = "bigserial")
	private long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "exchange_term_id")
	private ExchangeTerm exchangeTerm;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "procurement_manager_id")
	private ProcurementManagerOfHospital procurementManager;
	
	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<DetailsOfEquipmentOrder> details;
	
	@Column(name = "total_price", nullable = false)
	private double totalPrice;
	
	public EquipmentOrder() {}
	
	public EquipmentOrder(long id, ExchangeTerm exchangeTerm, 
			ProcurementManagerOfHospital procurementManager, Set<DetailsOfEquipmentOrder> details, 
			double totalPrice) {
		this.id = id;
		this.exchangeTerm = exchangeTerm;
		this.procurementManager = procurementManager;
		this.details = details;
		this.totalPrice = totalPrice;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public ExchangeTerm getExchangeTerm() {
		return exchangeTerm;
	}
	
	public void setExchangeTerm(ExchangeTerm exchangeTerm) {
		this.exchangeTerm = exchangeTerm;
	}
	
	public ProcurementManagerOfHospital getProcurementManager() {
		return procurementManager;
	}
	
	public void setProcurementManager(ProcurementManagerOfHospital procurementManager) {
		this.procurementManager = procurementManager;
	}
	
	public Set<DetailsOfEquipmentOrder> getDetails() {
		return details;
	}
	
	public void setDetails(Set<DetailsOfEquipmentOrder> details) {
		this.details = details;
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	@Override
	public int hashCode() {
		final int prime = 59;
		int result = 1;
		
		long temp = Double.doubleToLongBits(getTotalPrice());
		result = prime * result + (int) (temp ^ (temp >>> 32));
		
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof EquipmentOrder)) {
			return false;
		}
		
		EquipmentOrder other = (EquipmentOrder) obj;
		
		if (getId() != other.getId()) {
			return false;
		}
		
		if (Double.doubleToLongBits(getTotalPrice()) != 
				Double.doubleToLongBits(other.getTotalPrice())) {
			return false;
		}
		
		return true;
	}
}
