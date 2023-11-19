package ftn.project.ISAMedicalEquipmentBackend.repository.company;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.project.ISAMedicalEquipmentBackend.domain.company.MedicalEquipmentCompany;

public interface MedicalEquipmentCompanyRepository extends 
		JpaRepository<MedicalEquipmentCompany, Long> {
	MedicalEquipmentCompany findByName(String name);
	MedicalEquipmentCompany findByPopulatedPlace(String populatedPlace);
	MedicalEquipmentCompany findByCountry(String country);
}
