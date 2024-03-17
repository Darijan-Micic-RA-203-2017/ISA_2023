package ftn.project.ISAMedicalEquipmentBackend.dto.user;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import ftn.project.ISAMedicalEquipmentBackend.domain.user.Gender;

public abstract class UserDTO {
	protected long id;
	protected List<UserRoleDTO> roles;
	protected boolean isEnabled;
	protected String userCode;
	protected String emailAddress;
	protected String username;
	protected String password;
	protected Timestamp lastPasswordResetDate;
	protected String firstName;
	protected String lastName;
	protected String residence;
	protected String populatedPlace;
	protected String country;
	protected String phoneNumber;
	protected String personalIdentityNumber;
	protected Gender gender;
	protected String profession;
	protected String companyName;
	
	public UserDTO() {}
	
	public UserDTO(long id, List<UserRoleDTO> roles, boolean isEnabled, String userCode, 
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
	
	public List<UserRoleDTO> getRoles() {
		return roles;
	}
	
	public void setRoles(List<UserRoleDTO> roles) {
		this.roles = roles;
	}
	
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
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
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
}
