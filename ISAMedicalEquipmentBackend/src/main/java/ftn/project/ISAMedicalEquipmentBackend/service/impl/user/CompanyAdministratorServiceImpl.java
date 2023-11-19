package ftn.project.ISAMedicalEquipmentBackend.service.impl.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import ftn.project.ISAMedicalEquipmentBackend.domain.user.CompanyAdministrator;
import ftn.project.ISAMedicalEquipmentBackend.repository.user.CompanyAdministratorRepository;
import ftn.project.ISAMedicalEquipmentBackend.service.user.CompanyAdministratorService;

@Service
public class CompanyAdministratorServiceImpl implements CompanyAdministratorService {
	private final CompanyAdministratorRepository companyAdministratorRepository;
	
	@Autowired
	public CompanyAdministratorServiceImpl(
			CompanyAdministratorRepository companyAdministratorRepository) {
		this.companyAdministratorRepository = companyAdministratorRepository;
	}
	
	@Override
	public CompanyAdministrator findById(long id) throws AccessDeniedException {
		return companyAdministratorRepository.findById(id).orElse(null);
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
		return companyAdministratorRepository.findAll();
	}
}
