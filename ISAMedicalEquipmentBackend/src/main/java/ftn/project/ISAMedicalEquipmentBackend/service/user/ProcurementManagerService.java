package ftn.project.ISAMedicalEquipmentBackend.service.user;

import java.util.List;

import org.springframework.security.access.AccessDeniedException;

import ftn.project.ISAMedicalEquipmentBackend.domain.user.ProcurementManagerOfHospital;

public interface ProcurementManagerService {
	ProcurementManagerOfHospital findById(long id) throws AccessDeniedException;
	ProcurementManagerOfHospital findByUserCode(String userCode);
	ProcurementManagerOfHospital findByEmailAddress(String emailAddress);
	ProcurementManagerOfHospital findByUsername(String username);
	List<ProcurementManagerOfHospital> findAll() throws AccessDeniedException;
}
