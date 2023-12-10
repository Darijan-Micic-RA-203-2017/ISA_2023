package ftn.project.ISAMedicalEquipmentBackend.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.project.ISAMedicalEquipmentBackend.domain.user.ProcurementManagerOfHospital;

@Repository
public interface ProcurementManagerRepository extends 
		JpaRepository<ProcurementManagerOfHospital, Long> {
	ProcurementManagerOfHospital findByUserCode(String userCode);
	ProcurementManagerOfHospital findByEmailAddress(String emailAddress);
	ProcurementManagerOfHospital findByUsername(String username);
}
