package ftn.project.ISAMedicalEquipmentBackend.repository.equipment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ftn.project.ISAMedicalEquipmentBackend.domain.equipment.MedicalEquipment;

@Repository
public interface MedicalEquipmentRepository extends JpaRepository<MedicalEquipment, Long> {
	@Query(value = "SELECT meq FROM MedicalEquipment meq JOIN FETCH meq.type t " 
			+ "JOIN FETCH meq.company c")
	List<MedicalEquipment> getAll();
	@Query(value = "SELECT meq FROM MedicalEquipment meq JOIN FETCH meq.type t " + 
			"JOIN FETCH meq.company c WHERE meq.id = ?1")
	MedicalEquipment getById(long id);
	@Query(value = "SELECT meq FROM MedicalEquipment meq JOIN FETCH meq.type t " + 
			"JOIN FETCH meq.company c WHERE meq.name = ?1")
	MedicalEquipment findByName(String name);
}
