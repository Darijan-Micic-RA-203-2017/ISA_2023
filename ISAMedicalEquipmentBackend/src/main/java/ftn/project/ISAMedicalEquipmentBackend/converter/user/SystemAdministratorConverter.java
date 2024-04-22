package ftn.project.ISAMedicalEquipmentBackend.converter.user;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import ftn.project.ISAMedicalEquipmentBackend.domain.user.Gender;
import ftn.project.ISAMedicalEquipmentBackend.domain.user.SystemAdministrator;
import ftn.project.ISAMedicalEquipmentBackend.domain.user.UserRole;
import ftn.project.ISAMedicalEquipmentBackend.dto.user.SystemAdministratorDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.user.UserRoleDTO;

public class SystemAdministratorConverter {
	public SystemAdministratorConverter() {}
	
	public static List<SystemAdministratorDTO> convertToDTOsList(
			Iterable<SystemAdministrator> systemAdministrators) {
		if (systemAdministrators == null) {
			return null;
		}
		
		List<SystemAdministratorDTO> dtosList = new ArrayList<SystemAdministratorDTO>();
		for (SystemAdministrator sa: systemAdministrators) {
			dtosList.add(convertToDTO(sa));
		}
		
		return dtosList;
	}
	
	public static SystemAdministratorDTO convertToDTO(SystemAdministrator systemAdministrator) {
		if (systemAdministrator == null) {
			return null;
		}
		
		long id = systemAdministrator.getId();
		List<UserRoleDTO> roles = new ArrayList<UserRoleDTO>();
		for (UserRole r: systemAdministrator.getRoles()) {
			roles.add(UserRoleConverter.convertToDTO(r));
		}
		boolean isEnabled = systemAdministrator.isEnabled();
		String userCode = systemAdministrator.getUserCode();
		String emailAddress = systemAdministrator.getEmailAddress();
		String username = systemAdministrator.getUsername();
		String password = null;
		Timestamp lastPasswordResetDate = systemAdministrator.getLastPasswordResetDate();
		String firstName = systemAdministrator.getFirstName();
		String lastName = systemAdministrator.getLastName();
		String residence = systemAdministrator.getResidence();
		String populatedPlace = systemAdministrator.getPopulatedPlace();
		String country = systemAdministrator.getCountry();
		String phoneNumber = systemAdministrator.getPhoneNumber();
		String personalIdentityNumber = systemAdministrator.getPersonalIdentityNumber();
		Gender gender = systemAdministrator.getGender();
		String profession = systemAdministrator.getProfession();
		String companyName = systemAdministrator.getCompanyName();
		Timestamp employedSince = systemAdministrator.getEmployedSince();
		
		SystemAdministratorDTO systemAdministratorDTO = new SystemAdministratorDTO(id, roles, 
				isEnabled, userCode, emailAddress, username, password, lastPasswordResetDate, 
				firstName, lastName, residence, populatedPlace, country, phoneNumber, 
				personalIdentityNumber, gender, profession, companyName, employedSince);
		
		return systemAdministratorDTO;
	}
}
