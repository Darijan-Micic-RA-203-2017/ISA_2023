package ftn.project.ISAMedicalEquipmentBackend.repository.equipment;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.project.ISAMedicalEquipmentBackend.domain.equipment.MedicalEquipment;

public interface MedicalEquipmentRepository extends JpaRepository<MedicalEquipment, Long> {
	MedicalEquipment findByName(String name);
}
