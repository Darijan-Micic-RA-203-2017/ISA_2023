package ftn.project.ISAMedicalEquipmentBackend.service.equipment;

import java.util.List;

import org.springframework.security.access.AccessDeniedException;

import ftn.project.ISAMedicalEquipmentBackend.domain.equipment.TypeOfMedicalEquipment;

public interface TypeOfMedicalEquipmentService {
	TypeOfMedicalEquipment findById(long id) throws AccessDeniedException;
	TypeOfMedicalEquipment findByName(String name);
	List<TypeOfMedicalEquipment> findAll();
}
