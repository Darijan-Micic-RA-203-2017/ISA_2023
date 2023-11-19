package ftn.project.ISAMedicalEquipmentBackend.service.company;

import java.util.List;

import org.springframework.security.access.AccessDeniedException;

import ftn.project.ISAMedicalEquipmentBackend.domain.company.MedicalEquipmentCompany;
import ftn.project.ISAMedicalEquipmentBackend.dto.SearchCriterionDTO;

public interface MedicalEquipmentCompanyService {
	MedicalEquipmentCompany findById(long id) throws AccessDeniedException;
	MedicalEquipmentCompany findByName(String name);
	MedicalEquipmentCompany findByPopulatedPlace(String populatedPlace);
	MedicalEquipmentCompany findByCountry(String country);
	List<MedicalEquipmentCompany> findAll();
	List<MedicalEquipmentCompany> searchByNameOrPopulatedPlace(SearchCriterionDTO searchCriterionDTO);
}
