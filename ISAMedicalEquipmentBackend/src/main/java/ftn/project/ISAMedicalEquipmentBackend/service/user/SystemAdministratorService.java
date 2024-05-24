package ftn.project.ISAMedicalEquipmentBackend.service.user;

import java.util.List;

import org.springframework.security.access.AccessDeniedException;

import ftn.project.ISAMedicalEquipmentBackend.domain.user.SystemAdministrator;
import ftn.project.ISAMedicalEquipmentBackend.dto.user.SystemAdministratorDTO;
import ftn.project.ISAMedicalEquipmentBackend.exception.ChangeOfEmailAddressException;

public interface SystemAdministratorService {
	SystemAdministrator findById(long id) throws AccessDeniedException;
	SystemAdministrator findByUserCode(String userCode);
	SystemAdministrator findByEmailAddress(String emailAddress);
	SystemAdministrator findByUsername(String username);
	List<SystemAdministrator> findAll() throws AccessDeniedException;
	SystemAdministrator edit(SystemAdministratorDTO systemAdministratorToBeEdited) 
			throws ChangeOfEmailAddressException;
}
