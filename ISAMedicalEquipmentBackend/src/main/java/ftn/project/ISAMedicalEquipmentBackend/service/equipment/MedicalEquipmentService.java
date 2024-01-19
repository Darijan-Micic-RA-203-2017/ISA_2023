package ftn.project.ISAMedicalEquipmentBackend.service.equipment;

import java.util.List;

import org.springframework.security.access.AccessDeniedException;

import ftn.project.ISAMedicalEquipmentBackend.domain.equipment.MedicalEquipment;
import ftn.project.ISAMedicalEquipmentBackend.dto.SearchCriterionDTO;

public interface MedicalEquipmentService {
	MedicalEquipment findById(long id) throws AccessDeniedException;
	MedicalEquipment findByName(String name);
	List<MedicalEquipment> findAll();
	List<MedicalEquipment> searchByName(SearchCriterionDTO searchCriterionDTO);
	List<MedicalEquipment> findAllOfCompany(long companyId);
}
