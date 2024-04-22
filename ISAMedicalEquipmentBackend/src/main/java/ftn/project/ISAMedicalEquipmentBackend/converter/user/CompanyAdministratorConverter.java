package ftn.project.ISAMedicalEquipmentBackend.converter.user;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import ftn.project.ISAMedicalEquipmentBackend.converter.complaint.ComplaintConverter;
import ftn.project.ISAMedicalEquipmentBackend.converter.term.ExchangeTermConverter;
import ftn.project.ISAMedicalEquipmentBackend.domain.user.CompanyAdministrator;
import ftn.project.ISAMedicalEquipmentBackend.domain.user.Gender;
import ftn.project.ISAMedicalEquipmentBackend.domain.user.UserRole;
import ftn.project.ISAMedicalEquipmentBackend.dto.complaint.ComplaintDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.term.ExchangeTermDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.user.CompanyAdministratorDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.user.LoyaltyProgramDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.user.UserRoleDTO;

public class CompanyAdministratorConverter {
	public CompanyAdministratorConverter() {}
	
	public static List<CompanyAdministratorDTO> convertToDTOsList(
			Iterable<CompanyAdministrator> companyAdministrators) {
		if (companyAdministrators == null) {
			return null;
		}
		
		List<CompanyAdministratorDTO> dtosList = new ArrayList<CompanyAdministratorDTO>();
		for (CompanyAdministrator ca: companyAdministrators) {
			dtosList.add(convertToDTO(ca));
		}
		
		return dtosList;
	}
	
	public static CompanyAdministratorDTO convertToDTO(CompanyAdministrator companyAdministrator) {
		if (companyAdministrator == null) {
			return null;
		}
		
		long id = companyAdministrator.getId();
		List<UserRoleDTO> roles = new ArrayList<UserRoleDTO>();
		for (UserRole r: companyAdministrator.getRoles()) {
			roles.add(UserRoleConverter.convertToDTO(r));
		}
		boolean isEnabled = companyAdministrator.isEnabled();
		String userCode = companyAdministrator.getUserCode();
		String emailAddress = companyAdministrator.getEmailAddress();
		String username = companyAdministrator.getUsername();
		String password = null;
		Timestamp lastPasswordResetDate = companyAdministrator.getLastPasswordResetDate();
		String firstName = companyAdministrator.getFirstName();
		String lastName = companyAdministrator.getLastName();
		String residence = companyAdministrator.getResidence();
		String populatedPlace = companyAdministrator.getPopulatedPlace();
		String country = companyAdministrator.getCountry();
		String phoneNumber = companyAdministrator.getPhoneNumber();
		String personalIdentityNumber = companyAdministrator.getPersonalIdentityNumber();
		Gender gender = companyAdministrator.getGender();
		String profession = companyAdministrator.getProfession();
		String companyName = companyAdministrator.getCompanyName();
		long companyId = companyAdministrator.getCompany().getId();
		int penaltyPoints = companyAdministrator.getPenaltyPoints();
		int loyaltyPoints = companyAdministrator.getLoyaltyPoints();
		LoyaltyProgramDTO loyaltyProgram = 
				LoyaltyProgramConverter.convertToDTO(companyAdministrator.getLoyaltyProgram());
		List<ExchangeTermDTO> exchangeTerms = 
				ExchangeTermConverter.convertToDTOsList(companyAdministrator.getExchangeTerms());
		List<ComplaintDTO> complaints = 
				ComplaintConverter.convertToDTOsList(companyAdministrator.getComplaints());
		
		CompanyAdministratorDTO companyAdministratorDTO = new CompanyAdministratorDTO(id, roles, 
				isEnabled, userCode, emailAddress, username, password, lastPasswordResetDate, 
				firstName, lastName, residence, populatedPlace, country, phoneNumber, 
				personalIdentityNumber, gender, profession, companyName, companyId, penaltyPoints, 
				loyaltyPoints, loyaltyProgram, exchangeTerms, complaints);
		
		return companyAdministratorDTO;
	}
}
