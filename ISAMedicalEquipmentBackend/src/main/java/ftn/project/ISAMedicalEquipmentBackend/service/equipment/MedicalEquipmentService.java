package ftn.project.ISAMedicalEquipmentBackend.service.equipment;

import java.util.List;

import org.springframework.security.access.AccessDeniedException;

import ftn.project.ISAMedicalEquipmentBackend.domain.equipment.MedicalEquipment;
import ftn.project.ISAMedicalEquipmentBackend.dto.SearchCriterionDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.equipment.MedicalEquipmentDTO;

public interface MedicalEquipmentService {
	MedicalEquipment findById(long id) throws AccessDeniedException;
	MedicalEquipment findByName(String name);
	List<MedicalEquipment> findByCompanyName(String companyName);
	List<MedicalEquipment> findAll();
	List<MedicalEquipment> searchEquipmentByName(SearchCriterionDTO searchCriterionDTO);
	List<MedicalEquipment> searchEquipmentOfCompanyByName(SearchCriterionDTO searchCriterionDTO, 
			String companyName);
	MedicalEquipment save(MedicalEquipmentDTO medicalEquipmentDTO);
}
