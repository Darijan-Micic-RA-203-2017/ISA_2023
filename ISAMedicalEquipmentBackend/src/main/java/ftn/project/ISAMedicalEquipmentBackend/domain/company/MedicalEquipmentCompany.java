package ftn.project.ISAMedicalEquipmentBackend.domain.company;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
	
	@Column(name = "street_and_number", nullable = false)
	private String streetAndNumber;
	
	@Column(name = "populated_place", nullable = false)
	private String populatedPlace;
	
	@Column(name = "country", nullable = false)
	private String country;
	
	@Column(name = "description", nullable = false, columnDefinition = "text")
	private String description;
	
	@Column(name = "average_grade", nullable = false)
	private double averageGrade;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "work_time_id")
	private WorkTime workTime;
	
	@OneToMany(mappedBy="company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<MedicalEquipment> equipment;
	
	@OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<ExchangeTerm> unoccupiedExchangeTerms;
	
	@OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<CompanyAdministrator> administrators;
	
	public MedicalEquipmentCompany() {}
	
	public MedicalEquipmentCompany(long id, String name, String streetAndNumber, 
			String populatedPlace, String country, String description, double averageGrade, 
			WorkTime workTime, Set<MedicalEquipment> equipment, 
			Set<ExchangeTerm> unoccupiedExchangeTerms, Set<CompanyAdministrator> administrators) {
		this.id = id;
		this.name = name;
		this.streetAndNumber = streetAndNumber;
		this.populatedPlace = populatedPlace;
		this.country = country;
		this.description = description;
		this.averageGrade = averageGrade;
		this.workTime = workTime;
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
	
	public String getStreetAndNumber() {
		return streetAndNumber;
	}
	
	public void setStreetAndNumber(String streetAndNumber) {
		this.streetAndNumber = streetAndNumber;
	}
	
	public String getPopulatedPlace() {
		return populatedPlace;
	}
	
	public void setPopulatedPlace(String populatedPlace) {
		this.populatedPlace = populatedPlace;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
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
	
	public WorkTime getWorkTime() {
		return workTime;
	}
	
	public void setWorkTime(WorkTime workTime) {
		this.workTime = workTime;
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
		result = prime * result + 
				((getStreetAndNumber() == null) ? 0 : getStreetAndNumber().hashCode());
		result = prime * result + 
				((getPopulatedPlace() == null) ? 0 : getPopulatedPlace().hashCode());
		result = prime * result + ((getCountry() == null) ? 0 : getCountry().hashCode());
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
		
		if (getStreetAndNumber() == null) {
			if (other.getStreetAndNumber() != null) {
				return false;
			}
		} else if (!getStreetAndNumber().equals(other.getStreetAndNumber())) {
			return false;
		}
		
		if (getPopulatedPlace() == null) {
			if (other.getPopulatedPlace() != null) {
				return false;
			}
		} else if (!getPopulatedPlace().equals(other.getPopulatedPlace())) {
			return false;
		}
		
		if (getCountry() == null) {
			if (other.getCountry() != null) {
				return false;
			}
		} else if (!getCountry().equals(other.getCountry())) {
			return false;
		}
		
		if (Double.doubleToLongBits(getAverageGrade()) != 
				Double.doubleToLongBits(other.getAverageGrade())) {
			return false;
		}
		
		return true;
	}
}
