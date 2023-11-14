package ftn.project.ISAMedicalEquipmentBackend.domain.user;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "procurement_managers")
public class ProcurementManagerOfHospital extends User {
	@Column(name = "penalty_points", nullable = false)
	private int penaltyPoints;
	
	@Column(name = "loyalty_points", nullable = false)
	private int loyaltyPoints;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "loyalty_program_id")
	private LoyaltyProgram loyaltyProgram;
	
	public ProcurementManagerOfHospital() {}
	
	public ProcurementManagerOfHospital(long id, Set<UserRole> roles, boolean isEnabled, 
			String userCode, String emailAddress, String username, String password, 
			Timestamp lastPasswordResetDate, String firstName, String lastName, String residence, 
			String populatedPlace, String country, String phoneNumber, 
			String personalIdentityNumber, Gender gender, String profession, String companyName, 
			int penaltyPoints, int loyaltyPoints, LoyaltyProgram loyaltyProgram) {
		super(id, roles, isEnabled, userCode, emailAddress, username, password, 
				lastPasswordResetDate, firstName, lastName, residence, populatedPlace, country, 
				phoneNumber, personalIdentityNumber, gender, profession, companyName);
		
		this.penaltyPoints = penaltyPoints;
		this.loyaltyPoints = loyaltyPoints;
		this.loyaltyProgram = loyaltyProgram;
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
}
