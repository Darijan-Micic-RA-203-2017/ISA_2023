package ftn.project.ISAMedicalEquipmentBackend.dto;

import ftn.project.ISAMedicalEquipmentBackend.domain.user.Gender;

public class ProcurementManagerRegistrationReqDTO {
	private String emailAddress;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String residence;
	private String populatedPlace;
	private String country;
	private String phoneNumber;
	private String personalIdentityNumber;
	private Gender gender;
	private String profession;
	private String companyName;
	
	public ProcurementManagerRegistrationReqDTO() {}
	
	public ProcurementManagerRegistrationReqDTO(String emailAddress, String username, 
			String password, String firstName, String lastName, String residence, 
			String populatedPlace, String country, String phoneNumber, 
			String personalIdentityNumber, Gender gender, String profession, String companyName) {
		this.emailAddress = emailAddress;
		this.username = username;
		this.password = password;
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
		this.password = password;
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
