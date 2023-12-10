package ftn.project.ISAMedicalEquipmentBackend.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.project.ISAMedicalEquipmentBackend.domain.user.CompanyAdministrator;

@Repository
public interface CompanyAdministratorRepository extends JpaRepository<CompanyAdministrator, Long> {
	CompanyAdministrator findByUserCode(String userCode);
	CompanyAdministrator findByEmailAddress(String emailAddress);
	CompanyAdministrator findByUsername(String username);
}
