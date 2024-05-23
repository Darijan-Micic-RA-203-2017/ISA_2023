package ftn.project.ISAMedicalEquipmentBackend.validation.validator;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ftn.project.ISAMedicalEquipmentBackend.dto.user.UserDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.user.UserRoleDTO;

/** REFERENCE: https://docs.spring.io/spring-framework/reference/core/validation/validator.html */
public class ValidatorForUserDTO implements Validator {
	private static final int MINIMUM_LENGTH_FOR_USERNAME_AND_PASSWORD = 4;
	private static final int MAXIMUM_LENGTH_FOR_USERNAME_AND_PASSWORD = 32;
	
	private static final Pattern emailAddressPattern = 
			Pattern.compile("^[a-z0-9\\_\\-\\.]+@[a-z]+\\.[a-z\\.]+$");
	private static final Pattern usernamePattern = 
			Pattern.compile("^[A-Z][A-Za-z0-9\\_\\-\\.]{2,}[A-Za-z0-9\\.]$");
	private static final Pattern firstAndLastNamePattern = 
			Pattern.compile("^[A-Z\\p{L}][a-z\\p{L}]+([ -][A-Z\\p{L}][a-z\\p{L}]+)*$");
	private static final Pattern phoneNumberPattern = 
			Pattern.compile("^\\+[0-9]{1,3} [0-9]{4,13}$");
	private static final Pattern personalIdentityNumberPattern = 
			Pattern.compile("^[0-9]{13}$");
	
	public ValidatorForUserDTO() {}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return UserDTO.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		UserDTO userDTO = (UserDTO) target;
		
		long id = userDTO.getId();
		if (id <= 0) {
			errors.rejectValue("id", "field.min", null, 
					"Id is not positive!");
		}
		
		ValidationUtils.rejectIfEmpty(errors, "roles", 
				"field.required", "No roles are assigned!");
		List<UserRoleDTO> roles = userDTO.getRoles();
		if (roles.size() != 1) {
			errors.rejectValue("roles", "field.length", null, 
					"Exactly one role must be assigned!");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userCode", 
				"field.required", "User code is empty!");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailAddress", 
				"field.required", "Email address is empty!");
		String emailAddress = userDTO.getEmailAddress();
		if (emailAddress != null && !emailAddress.trim().isEmpty()) {
			Matcher emailAddressMatcher = emailAddressPattern.matcher(emailAddress);
			if (!emailAddressMatcher.matches()) {
				errors.rejectValue("emailAddress", "field.pattern", null, 
						"Email address is not entered in proper format!");
			}
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", 
				"field.required", "Username is empty!");
		String username = userDTO.getUsername();
		if (username != null && !username.trim().isEmpty()) {
			if (username.length() < MINIMUM_LENGTH_FOR_USERNAME_AND_PASSWORD) {
				errors.rejectValue("username", "field.min.length", 
						new Object[] {Integer.valueOf(MINIMUM_LENGTH_FOR_USERNAME_AND_PASSWORD)}, 
						"Username must contain at least " + 
								MINIMUM_LENGTH_FOR_USERNAME_AND_PASSWORD + " characters!");
			}
			
			if (username.length() > MAXIMUM_LENGTH_FOR_USERNAME_AND_PASSWORD) {
				errors.rejectValue("username", "field.max.length", 
						new Object[] {Integer.valueOf(MAXIMUM_LENGTH_FOR_USERNAME_AND_PASSWORD)}, 
						"Username must contain at most " + 
								MAXIMUM_LENGTH_FOR_USERNAME_AND_PASSWORD + " characters!");
			}
			
			Matcher usernameMatcher = usernamePattern.matcher(username);
			if (!usernameMatcher.matches()) {
				errors.rejectValue("username", "field.pattern", null, 
						"Username must begin with a capital letter and end with a letter or " + 
								"dot!\nCharacters '_', '-' and '.' are allowed.");
			}
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", 
				"field.required", "Password is empty!");
		String password = userDTO.getPassword();
		if (password != null && !password.trim().isEmpty()) {
			if (password.length() < MINIMUM_LENGTH_FOR_USERNAME_AND_PASSWORD) {
				errors.rejectValue("password", "field.min.length", 
						new Object[] {Integer.valueOf(MINIMUM_LENGTH_FOR_USERNAME_AND_PASSWORD)}, 
						"Password must contain at least " + 
								MINIMUM_LENGTH_FOR_USERNAME_AND_PASSWORD + " characters!");
			}
			
			if (password.length() > MAXIMUM_LENGTH_FOR_USERNAME_AND_PASSWORD) {
				errors.rejectValue("password", "field.max.length", 
						new Object[] {Integer.valueOf(MAXIMUM_LENGTH_FOR_USERNAME_AND_PASSWORD)}, 
						"Password must contain at most " + 
								MAXIMUM_LENGTH_FOR_USERNAME_AND_PASSWORD + " characters!");
			}
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", 
				"field.required", "First name is empty!");
		String firstName = userDTO.getFirstName();
		if (firstName != null && !firstName.trim().isEmpty()) {
			Matcher firstNameMatcher = firstAndLastNamePattern.matcher(firstName);
			if (!firstNameMatcher.matches()) {
				errors.rejectValue("firstName", "field.pattern", null, 
						"First name is not entered in proper format!");
			}
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", 
				"field.required", "Last name is empty!");
		String lastName = userDTO.getLastName();
		if (lastName != null && !lastName.trim().isEmpty()) {
			Matcher lastNameMatcher = firstAndLastNamePattern.matcher(lastName);
			if (!lastNameMatcher.matches()) {
				errors.rejectValue("lastName", "field.pattern", null, 
						"Last name is not entered in proper format!");
			}
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "residence", 
				"field.required", "Residence is empty!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "populatedPlace", 
				"field.required", "Populated place is empty!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "country", 
				"field.required", "Country is empty!");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", 
				"field.required", "Phone number is empty!");
		String phoneNumber = userDTO.getPhoneNumber();
		if (phoneNumber != null && !phoneNumber.trim().isEmpty()) {
			Matcher phoneNumberMatcher = phoneNumberPattern.matcher(phoneNumber);
			if (!phoneNumberMatcher.matches()) {
				errors.rejectValue("phoneNumber", "field.pattern", null, 
						"Phone number is not entered in proper format! E.g. +381 61250250");
			}
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "personalIdentityNumber", 
				"field.required", "Personal identity number is empty!");
		String personalIdentityNumber = 
				userDTO.getPersonalIdentityNumber();
		if (personalIdentityNumber != null && !personalIdentityNumber.trim().isEmpty()) {
			Matcher personalIdentityNumberMatcher = 
					personalIdentityNumberPattern.matcher(personalIdentityNumber);
			if (!personalIdentityNumberMatcher.matches()) {
				errors.rejectValue("personalIdentityNumber", "field.pattern", null, 
						"Personal identity number is not entered in proper format! " + 
						"It must contain exactly 13 digits!");
			}
		}
	}
}
