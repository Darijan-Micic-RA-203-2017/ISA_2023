package ftn.project.ISAMedicalEquipmentBackend.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.project.ISAMedicalEquipmentBackend.domain.user.LoyaltyProgram;

@Repository
public interface LoyaltyProgramRepository extends JpaRepository<LoyaltyProgram, Long> {
	LoyaltyProgram findByName(String name);
	LoyaltyProgram findByNecessaryPoints(int necessaryPoints);
	LoyaltyProgram findByPointsGainedForEachSuccessfulExchange(
			int pointsGainedForEachSuccessfulExchange);
}
