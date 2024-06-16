package ftn.project.ISAMedicalEquipmentBackend.domain.user;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "system_administrators")
public class SystemAdministrator extends User {
	@Column(name = "employed_since", nullable = false)
	private Timestamp employedSince;
	
	public SystemAdministrator() {}
	
	public SystemAdministrator(long id, Set<UserRole> roles, boolean isEnabled, String userCode, 
			String emailAddress, String username, String password, Timestamp lastPasswordResetDate, 
			String firstName, String lastName, String residence, String populatedPlace, 
			String country, String phoneNumber, String personalIdentityNumber, Gender gender, 
			String profession, String companyName, double companyLatitude, double companyLongitude, 
			Timestamp employedSince) {
		super(id, roles, isEnabled, userCode, emailAddress, username, password, 
				lastPasswordResetDate, firstName, lastName, residence, populatedPlace, country, 
				phoneNumber, personalIdentityNumber, gender, profession, companyName, 
				companyLatitude, companyLongitude);
		
		this.employedSince = employedSince;
	}
	
	public Timestamp getEmployedSince() {
		return employedSince;
	}
	
	public void setEmployedSince(Timestamp employedSince) {
		this.employedSince = employedSince;
	}
}
