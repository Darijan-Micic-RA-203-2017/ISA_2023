package ftn.project.ISAMedicalEquipmentBackend.domain.term;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ftn.project.ISAMedicalEquipmentBackend.domain.company.MedicalEquipmentCompany;
import ftn.project.ISAMedicalEquipmentBackend.domain.user.CompanyAdministrator;
import ftn.project.ISAMedicalEquipmentBackend.domain.user.ProcurementManagerOfHospital;

@Entity
@Table(name = "exchange_terms")
public class ExchangeTerm {
	@Id
	@SequenceGenerator(name = "exchange_term_id_generator", 
		sequenceName = "exchange_term_ids_sequence", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exchange_term_id_generator")
	@Column(name = "id", nullable = false, updatable = false, columnDefinition = "bigserial")
	private long id;
	
	@Column(name = "starting_time", nullable = false)
	private Timestamp startingTime;
	
	@Column(name = "ending_time", nullable = false)
	private Timestamp endingTime;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "procurement_manager_id")
	private ProcurementManagerOfHospital procurementManager;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	private MedicalEquipmentCompany company;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_administrator_id")
	private CompanyAdministrator companyAdministrator;
	
	public ExchangeTerm() {}
	
	public ExchangeTerm(long id, Timestamp startingTime, Timestamp endingTime, 
			ProcurementManagerOfHospital procurementManager, MedicalEquipmentCompany company, 
			CompanyAdministrator companyAdministrator) {
		this.id = id;
		this.startingTime = startingTime;
		this.endingTime = endingTime;
		this.procurementManager = procurementManager;
		this.company = company;
		this.companyAdministrator = companyAdministrator;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public Timestamp getStartingTime() {
		return startingTime;
	}
	
	public void setStartingTime(Timestamp startingTime) {
		this.startingTime = startingTime;
	}
	
	public Timestamp getEndingTime() {
		return endingTime;
	}
	
	public void setEndingTime(Timestamp endingTime) {
		this.endingTime = endingTime;
	}
	
	public ProcurementManagerOfHospital getProcurementManager() {
		return procurementManager;
	}
	
	public void setProcurementManager(ProcurementManagerOfHospital procurementManager) {
		this.procurementManager = procurementManager;
	}
	
	public MedicalEquipmentCompany getCompany() {
		return company;
	}
	
	public void setCompany(MedicalEquipmentCompany company) {
		this.company = company;
	}
	
	public CompanyAdministrator getCompanyAdministrator() {
		return companyAdministrator;
	}
	
	public void setCompanyAdministrator(CompanyAdministrator companyAdministrator) {
		this.companyAdministrator = companyAdministrator;
	}
	
	@Override
	public int hashCode() {
		final int prime = 43;
		int result = 1;
		
		result = prime * result + ((getStartingTime() == null) ? 0 : getStartingTime().hashCode());
		result = prime * result + ((getEndingTime() == null) ? 0 : getEndingTime().hashCode());
		
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof ExchangeTerm)) {
			return false;
		}
		
		ExchangeTerm other = (ExchangeTerm) obj;
		
		if (getId() != other.getId()) {
			return false;
		}
		
		if (getStartingTime() == null) {
			if (other.getStartingTime() != null) {
				return false;
			}
		} else if (!getStartingTime().equals(other.getStartingTime())) {
			return false;
		}
		
		if (getEndingTime() == null) {
			if (other.getEndingTime() != null) {
				return false;
			}
		} else if (!getEndingTime().equals(other.getEndingTime())) {
			return false;
		}
		
		return true;
	}
}
