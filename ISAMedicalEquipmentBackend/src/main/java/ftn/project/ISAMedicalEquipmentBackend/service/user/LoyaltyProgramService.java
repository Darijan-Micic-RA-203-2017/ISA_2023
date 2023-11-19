package ftn.project.ISAMedicalEquipmentBackend.service.user;

import java.util.List;

import org.springframework.security.access.AccessDeniedException;

import ftn.project.ISAMedicalEquipmentBackend.domain.user.LoyaltyProgram;

public interface LoyaltyProgramService {
	LoyaltyProgram findById(long id) throws AccessDeniedException;
	LoyaltyProgram findByName(String name);
	LoyaltyProgram findByNecessaryPoints(int necessaryPoints);
	LoyaltyProgram findByPointsGainedForEachSuccessfulExchange(
			int pointsGainedForEachSuccessfulExchange);
	List<LoyaltyProgram> findAll() throws AccessDeniedException;
}
