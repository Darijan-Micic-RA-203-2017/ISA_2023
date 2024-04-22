package ftn.project.ISAMedicalEquipmentBackend.service.user;

import java.util.List;

import org.springframework.mail.MailException;
import org.springframework.security.access.AccessDeniedException;

import ftn.project.ISAMedicalEquipmentBackend.domain.user.ProcurementManager;
import ftn.project.ISAMedicalEquipmentBackend.dto.ProcurementManagerRegistrationReqDTO;

public interface ProcurementManagerService {
	ProcurementManager findById(long id) throws AccessDeniedException;
	ProcurementManager findByUserCode(String userCode);
	ProcurementManager findByEmailAddress(String emailAddress);
	ProcurementManager findByUsername(String username);
	List<ProcurementManager> findAll() throws AccessDeniedException;
	ProcurementManager save(
			ProcurementManagerRegistrationReqDTO procurementManagerRegistrationReqDTO);
	String generateUserCodeFrom(String password);
	void sendActivationEmail(ProcurementManager newProcurementManager) throws MailException;
	void activateAccount(String userCodeOfNewRegisteredUser);
}
