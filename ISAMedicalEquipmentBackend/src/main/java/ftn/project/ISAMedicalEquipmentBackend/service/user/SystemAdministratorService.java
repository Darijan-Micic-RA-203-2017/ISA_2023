package ftn.project.ISAMedicalEquipmentBackend.service.user;

import java.util.List;

import org.springframework.security.access.AccessDeniedException;

import ftn.project.ISAMedicalEquipmentBackend.domain.user.SystemAdministrator;

public interface SystemAdministratorService {
	SystemAdministrator findById(long id) throws AccessDeniedException;
	SystemAdministrator findByUserCode(String userCode);
	SystemAdministrator findByEmailAddress(String emailAddress);
	SystemAdministrator findByUsername(String username);
	List<SystemAdministrator> findAll() throws AccessDeniedException;
}
