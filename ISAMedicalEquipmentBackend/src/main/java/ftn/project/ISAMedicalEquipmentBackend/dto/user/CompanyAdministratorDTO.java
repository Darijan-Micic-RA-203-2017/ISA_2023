package ftn.project.ISAMedicalEquipmentBackend.dto.user;

import java.sql.Timestamp;
import java.util.List;

import ftn.project.ISAMedicalEquipmentBackend.domain.user.Gender;
import ftn.project.ISAMedicalEquipmentBackend.dto.complaint.ComplaintDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.term.ExchangeTermDTO;

public class CompanyAdministratorDTO extends UserDTO {
	private long companyId;
	private int penaltyPoints;
	private int loyaltyPoints;
	private LoyaltyProgramDTO loyaltyProgram;
	private List<ExchangeTermDTO> exchangeTerms;
	private List<ComplaintDTO> complaints;
	
	public CompanyAdministratorDTO() {}
	
	public CompanyAdministratorDTO(long id, List<UserRoleDTO> roles, boolean isEnabled, 
			String userCode, String emailAddress, String username, String password, 
			Timestamp lastPasswordResetDate, String firstName, String lastName, String residence, 
			String populatedPlace, String country, String phoneNumber, String personalIdentityNumber, 
			Gender gender, String profession, String companyName, double companyLatitude, 
			double companyLongitude, long companyId, int penaltyPoints, int loyaltyPoints, 
			LoyaltyProgramDTO loyaltyProgram, List<ExchangeTermDTO> exchangeTerms, 
			List<ComplaintDTO> complaints) {
		super(id, roles, isEnabled, userCode, emailAddress, username, password, 
				lastPasswordResetDate, firstName, lastName, residence, populatedPlace, country, 
				phoneNumber, personalIdentityNumber, gender, profession, companyName, 
				companyLatitude, companyLongitude);
		
		this.companyId = companyId;
		this.penaltyPoints = penaltyPoints;
		this.loyaltyPoints = loyaltyPoints;
		this.loyaltyProgram = loyaltyProgram;
		this.exchangeTerms = exchangeTerms;
		this.complaints = complaints;
	}
	
	public long getCompanyId() {
		return companyId;
	}
	
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
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
	
	public LoyaltyProgramDTO getLoyaltyProgram() {
		return loyaltyProgram;
	}
	
	public void setLoyaltyProgram(LoyaltyProgramDTO loyaltyProgram) {
		this.loyaltyProgram = loyaltyProgram;
	}
	
	public List<ExchangeTermDTO> getExchangeTerms() {
		return exchangeTerms;
	}
	
	public void setExchangeTerms(List<ExchangeTermDTO> exchangeTerms) {
		this.exchangeTerms = exchangeTerms;
	}
	
	public List<ComplaintDTO> getComplaints() {
		return complaints;
	}
	
	public void setComplaints(List<ComplaintDTO> complaints) {
		this.complaints = complaints;
	}
}
