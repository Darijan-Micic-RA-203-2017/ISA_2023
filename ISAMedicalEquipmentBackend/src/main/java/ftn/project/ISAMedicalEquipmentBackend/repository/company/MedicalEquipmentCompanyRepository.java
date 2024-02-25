package ftn.project.ISAMedicalEquipmentBackend.repository.company;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ftn.project.ISAMedicalEquipmentBackend.domain.company.MedicalEquipmentCompany;

@Repository
public interface MedicalEquipmentCompanyRepository extends 
		JpaRepository<MedicalEquipmentCompany, Long> {
	@Query(value = "SELECT c FROM MedicalEquipmentCompany c JOIN FETCH c.workTime wt")
	List<MedicalEquipmentCompany> getAll();
	
	@Query(value = "SELECT c FROM MedicalEquipmentCompany c JOIN FETCH c.workTime wt " 
			+ "JOIN FETCH c.administrators admins WHERE c.id = ?1")
	MedicalEquipmentCompany getById(long id);
	
	@Query(value = "SELECT c FROM MedicalEquipmentCompany c JOIN FETCH c.workTime wt " 
			+ "WHERE c.name = ?1")
	MedicalEquipmentCompany findByName(String name);
	
	@Query(value = "SELECT c FROM MedicalEquipmentCompany c JOIN FETCH c.workTime wt " 
			+ "WHERE c.populatedPlace = ?1")
	MedicalEquipmentCompany findByPopulatedPlace(String populatedPlace);
	
	@Query(value = "SELECT c FROM MedicalEquipmentCompany c JOIN FETCH c.workTime wt " 
			+ "WHERE c.country = ?1")
	MedicalEquipmentCompany findByCountry(String country);
}
