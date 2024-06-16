package ftn.project.ISAMedicalEquipmentBackend.domain.user;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ftn.project.ISAMedicalEquipmentBackend.domain.complaint.Complaint;
import ftn.project.ISAMedicalEquipmentBackend.domain.term.ExchangeTerm;

@Entity
@Table(name = "procurement_managers")
public class ProcurementManager extends User {
	@Column(name = "penalty_points", nullable = false)
	private int penaltyPoints;
	
	@Column(name = "loyalty_points", nullable = false)
	private int loyaltyPoints;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "loyalty_program_id")
	private LoyaltyProgram loyaltyProgram;
	
	@OneToMany(mappedBy = "procurementManager", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<ExchangeTerm> exchangeTerms;
	
	@OneToMany(mappedBy = "procurementManager", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Complaint> complaints;
	
	public ProcurementManager() {}
	
	public ProcurementManager(long id, Set<UserRole> roles, boolean isEnabled, 
			String userCode, String emailAddress, String username, String password, 
			Timestamp lastPasswordResetDate, String firstName, String lastName, String residence, 
			String populatedPlace, String country, String phoneNumber, 
			String personalIdentityNumber, Gender gender, String profession, String companyName, 
			double companyLatitude, double companyLongitude, int penaltyPoints, int loyaltyPoints, 
			LoyaltyProgram loyaltyProgram, Set<ExchangeTerm> exchangeTerms, Set<Complaint> complaints) {
		super(id, roles, isEnabled, userCode, emailAddress, username, password, 
				lastPasswordResetDate, firstName, lastName, residence, populatedPlace, country, 
				phoneNumber, personalIdentityNumber, gender, profession, companyName, 
				companyLatitude, companyLongitude);
		
		this.penaltyPoints = penaltyPoints;
		this.loyaltyPoints = loyaltyPoints;
		this.loyaltyProgram = loyaltyProgram;
		this.exchangeTerms = exchangeTerms;
		this.complaints = complaints;
	}
	
	public int getPenaltyPoints() {
		return penaltyPoints;
	}
	
	public void setPenaltyPoints(int penaltyPoints) {
		this.penaltyPoints = penaltyPoints;
	}
	
	public int getLoyaltyPoints() {
		return loyaltyPoints;
	}
	
	public void setLoyaltyPoints(int loyaltyPoints) {
		this.loyaltyPoints = loyaltyPoints;
	}
	
	public LoyaltyProgram getLoyaltyProgram() {
		return loyaltyProgram;
	}
	
	public void setLoyaltyProgram(LoyaltyProgram loyaltyProgram) {
		this.loyaltyProgram = loyaltyProgram;
	}
	
	public Set<ExchangeTerm> getExchangeTerms() {
		return exchangeTerms;
	}
	
	public void setExchangeTerms(Set<ExchangeTerm> exchangeTerms) {
		this.exchangeTerms = exchangeTerms;
	}
	
	public Set<Complaint> getComplaints() {
		return complaints;
	}
	
	public void setComplaints(Set<Complaint> complaints) {
		this.complaints = complaints;
	}
}
