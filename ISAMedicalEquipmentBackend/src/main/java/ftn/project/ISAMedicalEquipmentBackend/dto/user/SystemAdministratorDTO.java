package ftn.project.ISAMedicalEquipmentBackend.dto.user;

import java.sql.Timestamp;
import java.util.List;

import ftn.project.ISAMedicalEquipmentBackend.domain.user.Gender;

public class SystemAdministratorDTO extends UserDTO {
	private Timestamp employedSince;
	
	public SystemAdministratorDTO() {}
	
	public SystemAdministratorDTO(long id, List<UserRoleDTO> roles, boolean isEnabled, 
			String userCode, String emailAddress, String username, String password, 
			Timestamp lastPasswordResetDate, String firstName, String lastName, String residence, 
			String populatedPlace, String country, String phoneNumber, 
			String personalIdentityNumber, Gender gender, String profession, String companyName, 
			Timestamp employedSince) {
		super(id, roles, isEnabled, userCode, emailAddress, username, password, 
				lastPasswordResetDate, firstName, lastName, residence, populatedPlace, country, 
				phoneNumber, personalIdentityNumber, gender, profession, companyName);
		
		this.employedSince = employedSince;
	}
	
	public Timestamp getEmployedSince() {
		return employedSince;
	}
	
	public void setEmployedSince(Timestamp employedSince) {
		this.employedSince = employedSince;
	}
}
