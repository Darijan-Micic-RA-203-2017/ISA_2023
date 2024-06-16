package ftn.project.ISAMedicalEquipmentBackend.converter.user;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import ftn.project.ISAMedicalEquipmentBackend.converter.complaint.ComplaintConverter;
import ftn.project.ISAMedicalEquipmentBackend.converter.term.ExchangeTermConverter;
import ftn.project.ISAMedicalEquipmentBackend.domain.user.Gender;
import ftn.project.ISAMedicalEquipmentBackend.domain.user.ProcurementManager;
import ftn.project.ISAMedicalEquipmentBackend.dto.complaint.ComplaintDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.term.ExchangeTermDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.user.LoyaltyProgramDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.user.ProcurementManagerDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.user.UserRoleDTO;

public class ProcurementManagerConverter {
	public ProcurementManagerConverter() {}
	
	public static List<ProcurementManagerDTO> convertToDTOsList(
			Iterable<ProcurementManager> procurementManagers) {
		if (procurementManagers == null) {
			return null;
		}
		
		List<ProcurementManagerDTO> dtosList = new ArrayList<ProcurementManagerDTO>();
		for (ProcurementManager pm: procurementManagers) {
			dtosList.add(convertToDTO(pm));
		}
		
		return dtosList;
	}
	
	public static ProcurementManagerDTO convertToDTO(ProcurementManager procurementManager) {
		if (procurementManager == null) {
			return null;
		}
		
		long id = procurementManager.getId();
		List<UserRoleDTO> roles = 
				UserRoleConverter.convertToDTOsList(procurementManager.getRoles());
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
		double companyLatitude = procurementManager.getCompanyLatitude();
		double companyLongitude = procurementManager.getCompanyLongitude();
		int penaltyPoints = procurementManager.getPenaltyPoints();
		int loyaltyPoints = procurementManager.getLoyaltyPoints();
		LoyaltyProgramDTO loyaltyProgram = 
				LoyaltyProgramConverter.convertToDTO(procurementManager.getLoyaltyProgram());
		List<ExchangeTermDTO> exchangeTerms = 
				ExchangeTermConverter.convertToDTOsList(procurementManager.getExchangeTerms());
		List<ComplaintDTO> complaints = 
				ComplaintConverter.convertToDTOsList(procurementManager.getComplaints());
		
		ProcurementManagerDTO procurementManagerDTO = new ProcurementManagerDTO(id, roles, 
				isEnabled, userCode, emailAddress, username, password, lastPasswordResetDate, 
				firstName, lastName, residence, populatedPlace, country, phoneNumber, 
				personalIdentityNumber, gender, profession, companyName, companyLatitude, 
				companyLongitude, penaltyPoints, loyaltyPoints, loyaltyProgram, exchangeTerms, 
				complaints);
		
		return procurementManagerDTO;
	}
}
