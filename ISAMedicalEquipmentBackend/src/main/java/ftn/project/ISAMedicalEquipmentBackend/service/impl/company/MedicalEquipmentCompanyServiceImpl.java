package ftn.project.ISAMedicalEquipmentBackend.service.impl.company;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import ftn.project.ISAMedicalEquipmentBackend.domain.company.MedicalEquipmentCompany;
import ftn.project.ISAMedicalEquipmentBackend.dto.SearchCriterionDTO;
import ftn.project.ISAMedicalEquipmentBackend.repository.company.MedicalEquipmentCompanyRepository;
import ftn.project.ISAMedicalEquipmentBackend.service.company.MedicalEquipmentCompanyService;

@Service
public class MedicalEquipmentCompanyServiceImpl implements MedicalEquipmentCompanyService {
	private final MedicalEquipmentCompanyRepository medicalEquipmentCompanyRepository;
	
	@Autowired
	public MedicalEquipmentCompanyServiceImpl(
			MedicalEquipmentCompanyRepository medicalEquipmentCompanyRepository) {
		this.medicalEquipmentCompanyRepository = medicalEquipmentCompanyRepository;
	}
	
	@Override
	public MedicalEquipmentCompany findById(long id) throws AccessDeniedException {
		return medicalEquipmentCompanyRepository.getById(id);
	}
	
	@Override
	public MedicalEquipmentCompany findByName(String name) {
		return medicalEquipmentCompanyRepository.findByName(name);
	}
	
	@Override
	public MedicalEquipmentCompany findByPopulatedPlace(String populatedPlace) {
		return medicalEquipmentCompanyRepository.findByPopulatedPlace(populatedPlace);
	}
	
	@Override
	public MedicalEquipmentCompany findByCountry(String country) {
		return medicalEquipmentCompanyRepository.findByCountry(country);
	}
	
	@Override
	public List<MedicalEquipmentCompany> findAll() {
		return medicalEquipmentCompanyRepository.getAll();
	}
	
	@Override
	public List<MedicalEquipmentCompany> searchByNameOrPopulatedPlace(
			SearchCriterionDTO searchCriterionDTO) {
		List<MedicalEquipmentCompany> companies = new ArrayList<MedicalEquipmentCompany>();
		
		String criterion = searchCriterionDTO.getCriterion().toUpperCase();
		String name;
		String populatedPlace;
		for (MedicalEquipmentCompany company: findAll()) {
			name = company.getName().toUpperCase();
			populatedPlace = company.getPopulatedPlace().toUpperCase();
			if (name.contains(criterion) || populatedPlace.contains(criterion)) {
				companies.add(company);
			}
		}
		
		return companies;
	}
}
