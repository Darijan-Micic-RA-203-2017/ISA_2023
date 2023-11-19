package ftn.project.ISAMedicalEquipmentBackend.service.impl.equipment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import ftn.project.ISAMedicalEquipmentBackend.domain.equipment.MedicalEquipment;
import ftn.project.ISAMedicalEquipmentBackend.dto.SearchCriterionDTO;
import ftn.project.ISAMedicalEquipmentBackend.repository.equipment.MedicalEquipmentRepository;
import ftn.project.ISAMedicalEquipmentBackend.service.equipment.MedicalEquipmentService;

@Service
public class MedicalEquipmentServiceImpl implements MedicalEquipmentService {
	private final MedicalEquipmentRepository medicalEquipmentRepository;
	
	@Autowired
	public MedicalEquipmentServiceImpl(MedicalEquipmentRepository medicalEquipmentRepository) {
		this.medicalEquipmentRepository = medicalEquipmentRepository;
	}
	
	@Override
	public MedicalEquipment findById(long id) throws AccessDeniedException {
		return medicalEquipmentRepository.findById(id).orElse(null);
	}
	
	@Override
	public MedicalEquipment findByName(String name) {
		return medicalEquipmentRepository.findByName(name);
	}
	
	@Override
	public List<MedicalEquipment> findAll() {
		return medicalEquipmentRepository.findAll();
	}
	
	@Override
	public List<MedicalEquipment> searchByName(SearchCriterionDTO searchCriterionDTO) {
		List<MedicalEquipment> equipment = new ArrayList<MedicalEquipment>();
		
		String criterion = searchCriterionDTO.getCriterion().toUpperCase();
		String name;
		for (MedicalEquipment equ: findAll()) {
			name = equ.getName().toUpperCase();
			if (name.contains(criterion)) {
				equipment.add(equ);
			}
		}
		
		return equipment;
	}
}
