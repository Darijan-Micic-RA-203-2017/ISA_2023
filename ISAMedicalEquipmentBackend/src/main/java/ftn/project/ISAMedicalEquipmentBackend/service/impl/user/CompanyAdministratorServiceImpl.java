package ftn.project.ISAMedicalEquipmentBackend.service.impl.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ftn.project.ISAMedicalEquipmentBackend.domain.user.CompanyAdministrator;
import ftn.project.ISAMedicalEquipmentBackend.dto.user.CompanyAdministratorDTO;
import ftn.project.ISAMedicalEquipmentBackend.repository.user.CompanyAdministratorRepository;
import ftn.project.ISAMedicalEquipmentBackend.service.user.CompanyAdministratorService;

@Service
public class CompanyAdministratorServiceImpl implements CompanyAdministratorService {
	private final CompanyAdministratorRepository companyAdministratorRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public CompanyAdministratorServiceImpl(
			CompanyAdministratorRepository companyAdministratorRepository, 
			PasswordEncoder passwordEncoder) {
		this.companyAdministratorRepository = companyAdministratorRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public CompanyAdministrator findById(long id) throws AccessDeniedException {
		return companyAdministratorRepository.getById(id);
	}
	
	@Override
	public CompanyAdministrator findByUserCode(String userCode) {
		return companyAdministratorRepository.findByUserCode(userCode);
	}
	
	@Override
	public CompanyAdministrator findByEmailAddress(String emailAddress) {
		return companyAdministratorRepository.findByEmailAddress(emailAddress);
	}
	
	@Override
	public CompanyAdministrator findByUsername(String username) {
		return companyAdministratorRepository.findByUsername(username);
	}
	
	@Override
	public List<CompanyAdministrator> findAll() throws AccessDeniedException {
		return companyAdministratorRepository.getAll();
	}
	
	@Override
	@Transactional
	public CompanyAdministrator edit(CompanyAdministratorDTO companyAdministratorToBeEdited) {
		System.out.println("Entered edit method of CompanyAdministratorService.");
		CompanyAdministrator originalCompanyAdministrator = 
				findById(companyAdministratorToBeEdited.getId());
		
		originalCompanyAdministrator.setUsername(companyAdministratorToBeEdited.getUsername());
		originalCompanyAdministrator.setPassword(
				passwordEncoder.encode(companyAdministratorToBeEdited.getPassword()));
		originalCompanyAdministrator.setLastPasswordResetDate(
				companyAdministratorToBeEdited.getLastPasswordResetDate());
		originalCompanyAdministrator.setFirstName(companyAdministratorToBeEdited.getFirstName());
		originalCompanyAdministrator.setLastName(companyAdministratorToBeEdited.getLastName());
		originalCompanyAdministrator.setResidence(companyAdministratorToBeEdited.getResidence());
		originalCompanyAdministrator.setPopulatedPlace(
				companyAdministratorToBeEdited.getPopulatedPlace());
		originalCompanyAdministrator.setCountry(companyAdministratorToBeEdited.getCountry());
		originalCompanyAdministrator.setPhoneNumber(companyAdministratorToBeEdited.getPhoneNumber());
		originalCompanyAdministrator.setPersonalIdentityNumber(
				companyAdministratorToBeEdited.getPersonalIdentityNumber());
		originalCompanyAdministrator.setGender(companyAdministratorToBeEdited.getGender());
		originalCompanyAdministrator.setProfession(companyAdministratorToBeEdited.getProfession());
		originalCompanyAdministrator.setCompanyName(companyAdministratorToBeEdited.getCompanyName());
		
		return companyAdministratorRepository.saveAndFlush(originalCompanyAdministrator);
	}
}
