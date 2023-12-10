package ftn.project.ISAMedicalEquipmentBackend.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.project.ISAMedicalEquipmentBackend.domain.user.SystemAdministrator;

@Repository
public interface SystemAdministratorRepository extends JpaRepository<SystemAdministrator, Long> {
	SystemAdministrator findByUserCode(String userCode);
	SystemAdministrator findByEmailAddress(String emailAddress);
	SystemAdministrator findByUsername(String username);
}
