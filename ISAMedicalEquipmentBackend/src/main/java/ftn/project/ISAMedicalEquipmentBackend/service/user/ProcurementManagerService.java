package ftn.project.ISAMedicalEquipmentBackend.service.user;

import java.util.List;

import org.springframework.mail.MailException;
import org.springframework.security.access.AccessDeniedException;

import ftn.project.ISAMedicalEquipmentBackend.domain.user.ProcurementManagerOfHospital;
import ftn.project.ISAMedicalEquipmentBackend.dto.ProcurementManagerRegistrationReqDTO;

public interface ProcurementManagerService {
	ProcurementManagerOfHospital findById(long id) throws AccessDeniedException;
	ProcurementManagerOfHospital findByUserCode(String userCode);
	ProcurementManagerOfHospital findByEmailAddress(String emailAddress);
	ProcurementManagerOfHospital findByUsername(String username);
	List<ProcurementManagerOfHospital> findAll() throws AccessDeniedException;
	ProcurementManagerOfHospital save(
			ProcurementManagerRegistrationReqDTO procurementManagerRegistrationReqDTO);
	String generateUserCodeFrom(String password);
	void sendActivationEmail(ProcurementManagerOfHospital newProcurementManager) 
			throws MailException;
	void activateAccount(String userCodeOfNewRegisteredUser);
}
