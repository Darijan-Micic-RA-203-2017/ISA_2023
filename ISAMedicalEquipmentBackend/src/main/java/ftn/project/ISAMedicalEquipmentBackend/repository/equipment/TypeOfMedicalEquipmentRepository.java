package ftn.project.ISAMedicalEquipmentBackend.repository.equipment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.project.ISAMedicalEquipmentBackend.domain.equipment.TypeOfMedicalEquipment;

@Repository
public interface TypeOfMedicalEquipmentRepository extends JpaRepository<TypeOfMedicalEquipment, Long> {
	TypeOfMedicalEquipment findByName(String name);
}
