package ftn.project.ISAMedicalEquipmentBackend.converter.user;

import java.util.ArrayList;
import java.util.List;

import ftn.project.ISAMedicalEquipmentBackend.domain.user.LoyaltyProgram;
import ftn.project.ISAMedicalEquipmentBackend.dto.user.LoyaltyProgramDTO;

public class LoyaltyProgramConverter {
	public LoyaltyProgramConverter() {}
	
	public static List<LoyaltyProgramDTO> convertToDTOsList(Iterable<LoyaltyProgram> loyaltyPrograms) {
		if (loyaltyPrograms == null) {
			return null;
		}
		
		List<LoyaltyProgramDTO> dtosList = new ArrayList<LoyaltyProgramDTO>();
		for (LoyaltyProgram lP: loyaltyPrograms) {
			dtosList.add(convertToDTO(lP));
		}
		
		return dtosList;
	}
	
	public static LoyaltyProgramDTO convertToDTO(LoyaltyProgram loyaltyProgram) {
		if (loyaltyProgram == null) {
			return null;
		}
		
		long id = loyaltyProgram.getId();
		String name = loyaltyProgram.getName();
		int necessaryPoints = loyaltyProgram.getNecessaryPoints();
		int pointsGainedForEachSuccessfulExchange = 
				loyaltyProgram.getPointsGainedForEachSuccessfulExchange();
		
		LoyaltyProgramDTO dto = new LoyaltyProgramDTO(id, name, necessaryPoints, 
				pointsGainedForEachSuccessfulExchange);
		
		return dto;
	}
}
