package ftn.project.ISAMedicalEquipmentBackend.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.project.ISAMedicalEquipmentBackend.domain.user.LoyaltyProgram;

public interface LoyaltyProgramRepository extends JpaRepository<LoyaltyProgram, Long> {
	LoyaltyProgram findByName(String name);
	LoyaltyProgram findByNecessaryPoints(int necessaryPoints);
	LoyaltyProgram findByPointsGainedForEachSuccessfulExchange(
			int pointsGainedForEachSuccessfulExchange);
}
