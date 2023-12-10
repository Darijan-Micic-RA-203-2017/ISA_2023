package ftn.project.ISAMedicalEquipmentBackend.repository.equipment;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.project.ISAMedicalEquipmentBackend.domain.equipment.TypeOfMedicalEquipment;

public interface TypeOfMedicalEquipmentRepository extends JpaRepository<TypeOfMedicalEquipment, Long> {
	TypeOfMedicalEquipment findByName(String name);
}
