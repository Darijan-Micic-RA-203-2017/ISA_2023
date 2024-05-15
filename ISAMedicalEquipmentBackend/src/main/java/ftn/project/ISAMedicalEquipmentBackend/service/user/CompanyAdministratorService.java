package ftn.project.ISAMedicalEquipmentBackend.service.user;

import java.util.List;

import org.springframework.security.access.AccessDeniedException;

import ftn.project.ISAMedicalEquipmentBackend.domain.user.CompanyAdministrator;
import ftn.project.ISAMedicalEquipmentBackend.dto.user.CompanyAdministratorDTO;

public interface CompanyAdministratorService {
	CompanyAdministrator findById(long id) throws AccessDeniedException;
	CompanyAdministrator findByUserCode(String userCode);
	CompanyAdministrator findByEmailAddress(String emailAddress);
	CompanyAdministrator findByUsername(String username);
	List<CompanyAdministrator> findAll() throws AccessDeniedException;
	CompanyAdministrator edit(CompanyAdministratorDTO companyAdministratorToBeEdited);
}
