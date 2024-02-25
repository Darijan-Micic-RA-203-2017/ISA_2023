package ftn.project.ISAMedicalEquipmentBackend.domain.equipment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "medical_equipment")
public class MedicalEquipment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false, columnDefinition = "bigserial")
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_id")
	private TypeOfMedicalEquipment type;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "price", nullable = false)
	private double price;
	
	@Column(name = "amount", nullable = false)
	private int amount;
	
	@Column(name = "company_name", nullable = false)
	private String companyName;
	
	public MedicalEquipment() {}
	
	public MedicalEquipment(long id, TypeOfMedicalEquipment type, String name, double price, 
			int amount, String companyName) {
		this.id = id;
		this.type = type;
		this.name = name;
		this.price = price;
		this.amount = amount;
		this.companyName = companyName;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public TypeOfMedicalEquipment getType() {
		return type;
	}
	
	public void setType(TypeOfMedicalEquipment type) {
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	@Override
	public int hashCode() {
		final int prime = 51;
		int result = 1;
		
		result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
		long temp = Double.doubleToLongBits(getPrice());
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + getAmount();
		result = prime * result + ((getCompanyName() == null) ? 0 : getCompanyName().hashCode());
		
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof MedicalEquipment)) {
			return false;
		}
		
		MedicalEquipment other = (MedicalEquipment) obj;
		
		if (getId() != other.getId()) {
			return false;
		}
		
		if (getName() == null) {
			if (other.getName() != null) {
				return false;
			}
		} else if (!getName().equals(other.getName())) {
			return false;
		}
		
		if (Double.doubleToLongBits(getPrice()) != Double.doubleToLongBits(other.getPrice())) {
			return false;
		}
		
		if (getAmount() != other.getAmount()) {
			return false;
		}
		
		if (getCompanyName() == null) {
			if (other.getCompanyName() != null) {
				return false;
			}
		} else if (!getCompanyName().equals(other.getCompanyName())) {
			return false;
		}
		
		return true;
	}
}
