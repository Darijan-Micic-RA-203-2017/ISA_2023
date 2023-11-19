package ftn.project.ISAMedicalEquipmentBackend.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.project.ISAMedicalEquipmentBackend.domain.user.CompanyAdministrator;

public interface CompanyAdministratorRepository extends JpaRepository<CompanyAdministrator, Long> {
	CompanyAdministrator findByUserCode(String userCode);
	CompanyAdministrator findByEmailAddress(String emailAddress);
	CompanyAdministrator findByUsername(String username);
}
