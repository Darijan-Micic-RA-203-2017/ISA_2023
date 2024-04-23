package ftn.project.ISAMedicalEquipmentBackend.domain.user;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User implements UserDetails {
	@Id
	@SequenceGenerator(name = "user_id_generator", sequenceName = "user_ids_sequence", 
		initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_generator")
	@Column(name = "id", nullable = false, updatable = false, columnDefinition = "bigserial")
	protected long id;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles_join_table", 
		joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}, 
		inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
	protected Set<UserRole> roles;
	
	@Column(name = "is_enabled", nullable = false)
	protected boolean isEnabled;
	
	@Column(name = "user_code", nullable = false, columnDefinition = "text")
	protected String userCode;
	
	@Column(name = "email_address", nullable = false)
	protected String emailAddress;
	
	@Column(name = "username", nullable = false)
	protected String username;
	
	@JsonIgnore
	@Column(name = "password", nullable = false)
	protected String password;
	
	@Column(name = "last_password_reset_date")
	protected Timestamp lastPasswordResetDate;
	
	@Column(name = "first_name", nullable = false)
	protected String firstName;
	
	@Column(name = "last_name", nullable = false)
	protected String lastName;
	
	@Column(name = "residence", nullable = false)
	protected String residence;
	
	@Column(name = "populated_place", nullable = false)
	protected String populatedPlace;
	
	@Column(name = "country", nullable = false)
	protected String country;
	
	@Column(name = "phone_number", nullable = false)
	protected String phoneNumber;
	
	@Column(name = "personal_identity_number", nullable = false)
	protected String personalIdentityNumber;
	
	@Column(name = "gender", nullable = false)
	@Enumerated(value = EnumType.STRING)
	protected Gender gender;
	
	@Column(name = "profession")
	protected String profession;
	
	@Column(name = "company_name")
	protected String companyName;
	
	public User() {}
	
	public User(long id, Set<UserRole> roles, boolean isEnabled, String userCode, 
			String emailAddress, String username, String password, Timestamp lastPasswordResetDate, 
			String firstName, String lastName, String residence, String populatedPlace, 
			String country, String phoneNumber, String personalIdentityNumber, Gender gender, 
			String profession, String companyName) {
		this.id = id;
		this.roles = roles;
		this.isEnabled = isEnabled;
		this.userCode = userCode;
		this.emailAddress = emailAddress;
		this.username = username;
		this.password = password;
		this.lastPasswordResetDate = lastPasswordResetDate;
		this.firstName = firstName;
		this.lastName = lastName;
		this.residence = residence;
		this.populatedPlace = populatedPlace;
		this.country = country;
		this.phoneNumber = phoneNumber;
		this.personalIdentityNumber = personalIdentityNumber;
		this.gender = gender;
		this.profession = profession;
		this.companyName = companyName;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public Set<UserRole> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<UserRole> roles) {
		this.roles = roles;
	}
	
	@Override
	public boolean isEnabled() {
		return isEnabled;
	}
	
	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	public String getUserCode() {
		return userCode;
	}
	
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	@Override
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		Timestamp currentMoment = new Timestamp(new Date().getTime());
		this.setLastPasswordResetDate(currentMoment);
		
		this.password = password;
	}
	
	public Timestamp getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}
	
	public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
		this.lastPasswordResetDate = lastPasswordResetDate;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getResidence() {
		return residence;
	}
	
	public void setResidence(String residence) {
		this.residence = residence;
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
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getPersonalIdentityNumber() {
		return personalIdentityNumber;
	}
	
	public void setPersonalIdentityNumber(String personalIdentityNumber) {
		this.personalIdentityNumber = personalIdentityNumber;
	}
	
	public Gender getGender() {
		return gender;
	}
	
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public String getProfession() {
		return profession;
	}
	
	public void setProfession(String profession) {
		this.profession = profession;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}
	
	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 29;
		int result = 1;
		
		result = prime * result + ((getEmailAddress() == null) ? 0 : getEmailAddress().hashCode());
		result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
		
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof User)) {
			return false;
		}
		
		User other = (User) obj;
		
		if (getId() != other.getId()) {
			return false;
		}
		
		if (getEmailAddress() == null) {
			if (other.getEmailAddress() != null) {
				return false;
			}
		} else if (!getEmailAddress().equals(other.getEmailAddress())) {
			return false;
		}
		
		if (getUsername() == null) {
			if (other.getUsername() != null) {
				return false;
			}
		} else if (!getUsername().equals(other.getUsername())) {
			return false;
		}
		
		return true;
	}
}
