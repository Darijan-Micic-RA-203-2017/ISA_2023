package ftn.project.ISAMedicalEquipmentBackend.domain.company;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ftn.project.ISAMedicalEquipmentBackend.domain.equipment.MedicalEquipment;
import ftn.project.ISAMedicalEquipmentBackend.domain.term.ExchangeTerm;
import ftn.project.ISAMedicalEquipmentBackend.domain.user.CompanyAdministrator;

@Entity
@Table(name = "medical_equipment_companies")
public class MedicalEquipmentCompany {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false, columnDefinition = "bigserial")
	private long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "address", nullable = false)
	private String address;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "average_grade", nullable = false)
	private double averageGrade;
	
	@OneToMany(mappedBy="company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<MedicalEquipment> equipment;
	
	@OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<ExchangeTerm> unoccupiedExchangeTerms;
	
	@OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<CompanyAdministrator> administrators;
	
	public MedicalEquipmentCompany() {}
	
	public MedicalEquipmentCompany(long id, String name, String address, String description, 
			double averageGrade, Set<MedicalEquipment> equipment, 
			Set<ExchangeTerm> unoccupiedExchangeTerms, Set<CompanyAdministrator> administrators) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.description = description;
		this.averageGrade = averageGrade;
		this.equipment = equipment;
		this.unoccupiedExchangeTerms = unoccupiedExchangeTerms;
		this.administrators = administrators;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public double getAverageGrade() {
		return averageGrade;
	}
	
	public void setAverageGrade(double averageGrade) {
		this.averageGrade = averageGrade;
	}
	
	public Set<MedicalEquipment> getEquipment() {
		return equipment;
	}
	
	public void setEquipment(Set<MedicalEquipment> equipment) {
		this.equipment = equipment;
	}
	
	public Set<ExchangeTerm> getUnoccupiedExchangeTerms() {
		return unoccupiedExchangeTerms;
	}
	
	public void setUnoccupiedExchangeTerms(Set<ExchangeTerm> unoccupiedExchangeTerms) {
		this.unoccupiedExchangeTerms = unoccupiedExchangeTerms;
	}
	
	public Set<CompanyAdministrator> getAdministrators() {
		return administrators;
	}
	
	public void setAdministrators(Set<CompanyAdministrator> administrators) {
		this.administrators = administrators;
	}
	
	@Override
	public int hashCode() {
		final int prime = 41;
		int result = 1;
		
		result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
		long temp = Double.doubleToLongBits(getAverageGrade());
		result = prime * result + (int) (temp ^ (temp >>> 32));
		
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof MedicalEquipmentCompany)) {
			return false;
		}
		
		MedicalEquipmentCompany other = (MedicalEquipmentCompany) obj;
		
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
		
		if (Double.doubleToLongBits(getAverageGrade()) != 
				Double.doubleToLongBits(other.getAverageGrade())) {
			return false;
		}
		
		return true;
	}
}
