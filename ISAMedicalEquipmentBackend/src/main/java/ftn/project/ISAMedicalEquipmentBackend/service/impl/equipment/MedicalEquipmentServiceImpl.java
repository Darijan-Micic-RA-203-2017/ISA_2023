package ftn.project.ISAMedicalEquipmentBackend.service.impl.equipment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import ftn.project.ISAMedicalEquipmentBackend.domain.equipment.MedicalEquipment;
import ftn.project.ISAMedicalEquipmentBackend.domain.equipment.TypeOfMedicalEquipment;
import ftn.project.ISAMedicalEquipmentBackend.dto.SearchCriterionDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.equipment.MedicalEquipmentDTO;
import ftn.project.ISAMedicalEquipmentBackend.repository.equipment.MedicalEquipmentRepository;
import ftn.project.ISAMedicalEquipmentBackend.service.equipment.MedicalEquipmentService;
import ftn.project.ISAMedicalEquipmentBackend.service.equipment.TypeOfMedicalEquipmentService;

@Service
public class MedicalEquipmentServiceImpl implements MedicalEquipmentService {
	private final MedicalEquipmentRepository medicalEquipmentRepository;
	private final TypeOfMedicalEquipmentService typeOfMedicalEquipmentService;
	
	@Autowired
	public MedicalEquipmentServiceImpl(MedicalEquipmentRepository medicalEquipmentRepository, 
			TypeOfMedicalEquipmentService typeOfMedicalEquipmentService) {
		this.medicalEquipmentRepository = medicalEquipmentRepository;
		this.typeOfMedicalEquipmentService = typeOfMedicalEquipmentService;
	}
	
	@Override
	public MedicalEquipment findById(long id) throws AccessDeniedException {
		return medicalEquipmentRepository.getById(id);
	}
	
	@Override
	public MedicalEquipment findByName(String name) {
		return medicalEquipmentRepository.findByName(name);
	}
	
	@Override
	public List<MedicalEquipment> findByCompanyName(String companyName) {
		return medicalEquipmentRepository.findByCompanyName(companyName);
	}
	
	@Override
	public List<MedicalEquipment> findAll() {
		return medicalEquipmentRepository.getAll();
	}
	
	@Override
	public List<MedicalEquipment> searchEquipmentByName(SearchCriterionDTO searchCriterionDTO) {
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
	
	@Override
	public List<MedicalEquipment> searchEquipmentOfCompanyByName(
			SearchCriterionDTO searchCriterionDTO, String companyName) {
		List<MedicalEquipment> equipmentOfCompany = new ArrayList<MedicalEquipment>();
		
		String criterion = searchCriterionDTO.getCriterion().toUpperCase();
		String name;
		for (MedicalEquipment equ: findByCompanyName(companyName)) {
			name = equ.getName().toUpperCase();
			if (name.contains(criterion)) {
				equipmentOfCompany.add(equ);
			}
		}
		
		return equipmentOfCompany;
	}
	
	@Override
	public boolean isThereEnoughEquipmentForOrder(MedicalEquipment equipment, 
			int requestedAmountInOrder) {
		boolean isThereEnough = true;
		if (equipment.getAmount() < requestedAmountInOrder) {
			isThereEnough = false;
		}
		
		return isThereEnough;
	}
	
	@Override
	public MedicalEquipment save(MedicalEquipmentDTO medicalEquipmentDTO) {
		long id = medicalEquipmentDTO.getId();
		TypeOfMedicalEquipment type = 
				typeOfMedicalEquipmentService.findById(medicalEquipmentDTO.getType().getId());
		String name = medicalEquipmentDTO.getName();
		double price = medicalEquipmentDTO.getPrice();
		int amount = medicalEquipmentDTO.getAmount();
		String companyName = medicalEquipmentDTO.getCompanyName();
		
		MedicalEquipment medicalEquipment = new MedicalEquipment(id, type, name, price, amount, 
				companyName);
		
		return medicalEquipmentRepository.saveAndFlush(medicalEquipment);
	}
}
