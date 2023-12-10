package ftn.project.ISAMedicalEquipmentBackend.service.impl.equipment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import ftn.project.ISAMedicalEquipmentBackend.domain.equipment.TypeOfMedicalEquipment;
import ftn.project.ISAMedicalEquipmentBackend.repository.equipment.TypeOfMedicalEquipmentRepository;
import ftn.project.ISAMedicalEquipmentBackend.service.equipment.TypeOfMedicalEquipmentService;

@Service
public class TypeOfMedicalEquipmentServiceImpl implements TypeOfMedicalEquipmentService {
	private final TypeOfMedicalEquipmentRepository typeOfMedicalEquipmentRepository;
	
	@Autowired
	public TypeOfMedicalEquipmentServiceImpl(
			TypeOfMedicalEquipmentRepository typeOfMedicalEquipmentRepository) {
		this.typeOfMedicalEquipmentRepository = typeOfMedicalEquipmentRepository;
	}
	
	@Override
	public TypeOfMedicalEquipment findById(long id) throws AccessDeniedException {
		return typeOfMedicalEquipmentRepository.findById(id).orElse(null);
	}
	
	@Override
	public TypeOfMedicalEquipment findByName(String name) {
		return typeOfMedicalEquipmentRepository.findByName(name);
	}
	
	@Override
	public List<TypeOfMedicalEquipment> findAll() {
		return typeOfMedicalEquipmentRepository.findAll();
	}
}
