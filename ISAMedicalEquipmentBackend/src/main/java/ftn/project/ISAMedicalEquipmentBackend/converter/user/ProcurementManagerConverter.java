package ftn.project.ISAMedicalEquipmentBackend.converter.user;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import ftn.project.ISAMedicalEquipmentBackend.converter.complaint.ComplaintConverter;
import ftn.project.ISAMedicalEquipmentBackend.converter.term.ExchangeTermConverter;
import ftn.project.ISAMedicalEquipmentBackend.domain.user.Gender;
import ftn.project.ISAMedicalEquipmentBackend.domain.user.ProcurementManagerOfHospital;
import ftn.project.ISAMedicalEquipmentBackend.domain.user.UserRole;
import ftn.project.ISAMedicalEquipmentBackend.dto.complaint.ComplaintDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.term.ExchangeTermDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.user.LoyaltyProgramDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.user.ProcurementManagerOfHospitalDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.user.UserRoleDTO;

public class ProcurementManagerConverter {
	public ProcurementManagerConverter() {}
	
	public static List<ProcurementManagerOfHospitalDTO> convertToDTOsList(
			Iterable<ProcurementManagerOfHospital> procurementManagers) {
		if (procurementManagers == null) {
			return null;
		}
		
		List<ProcurementManagerOfHospitalDTO> dtosList = 
				new ArrayList<ProcurementManagerOfHospitalDTO>();
		for (ProcurementManagerOfHospital pm: procurementManagers) {
			dtosList.add(convertToDTO(pm));
		}
		
		return dtosList;
	}
	
	public static ProcurementManagerOfHospitalDTO convertToDTO(
			ProcurementManagerOfHospital procurementManager) {
		if (procurementManager == null) {
			return null;
		}
		
		long id = procurementManager.getId();
		List<UserRoleDTO> roles = new ArrayList<UserRoleDTO>();
		for (UserRole r: procurementManager.getRoles()) {
			roles.add(UserRoleConverter.convertToDTO(r));
		}
		boolean isEnabled = procurementManager.isEnabled();
		String userCode = procurementManager.getUserCode();
		String emailAddress = procurementManager.getEmailAddress();
		String username = procurementManager.getUsername();
		String password = null;
		Timestamp lastPasswordResetDate = procurementManager.getLastPasswordResetDate();
		String firstName = procurementManager.getFirstName();
		String lastName = procurementManager.getLastName();
		String residence = procurementManager.getResidence();
		String populatedPlace = procurementManager.getPopulatedPlace();
		String country = procurementManager.getCountry();
		String phoneNumber = procurementManager.getPhoneNumber();
		String personalIdentityNumber = procurementManager.getPersonalIdentityNumber();
		Gender gender = procurementManager.getGender();
		String profession = procurementManager.getProfession();
		String companyName = procurementManager.getCompanyName();
		int penaltyPoints = procurementManager.getPenaltyPoints();
		int loyaltyPoints = procurementManager.getLoyaltyPoints();
		LoyaltyProgramDTO loyaltyProgram = 
				LoyaltyProgramConverter.convertToDTO(procurementManager.getLoyaltyProgram());
		List<ExchangeTermDTO> exchangeTerms = 
				ExchangeTermConverter.convertToDTOsList(procurementManager.getExchangeTerms());
		List<ComplaintDTO> complaints = 
				ComplaintConverter.convertToDTOsList(procurementManager.getComplaints());
		
		ProcurementManagerOfHospitalDTO procurementManagerDTO = 
				new ProcurementManagerOfHospitalDTO(id, roles, isEnabled, userCode, emailAddress, 
						username, password, lastPasswordResetDate, firstName, lastName, residence, 
						populatedPlace, country, phoneNumber, personalIdentityNumber, gender, 
						profession, companyName, penaltyPoints, loyaltyPoints, loyaltyProgram, 
						exchangeTerms, complaints);
		
		return procurementManagerDTO;
	}
}
