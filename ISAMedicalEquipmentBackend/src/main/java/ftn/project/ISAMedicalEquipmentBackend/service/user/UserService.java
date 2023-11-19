package ftn.project.ISAMedicalEquipmentBackend.service.user;

import java.util.List;

import org.springframework.security.access.AccessDeniedException;

import ftn.project.ISAMedicalEquipmentBackend.domain.user.User;

public interface UserService {
	ProcurementManagerService getProcurementManagerService();
	CompanyAdministratorService getCompanyAdministratorService();
	SystemAdministratorService getSystemAdministratorService();
	User findById(long id) throws AccessDeniedException;
	User findByUserCode(String userCode);
	User findByEmailAddress(String emailAddress);
	User findByUsername(String username);
	List<User> findAll() throws AccessDeniedException;
}
