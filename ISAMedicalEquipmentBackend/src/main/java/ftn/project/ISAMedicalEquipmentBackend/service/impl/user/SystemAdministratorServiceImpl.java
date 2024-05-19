package ftn.project.ISAMedicalEquipmentBackend.service.impl.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ftn.project.ISAMedicalEquipmentBackend.domain.user.SystemAdministrator;
import ftn.project.ISAMedicalEquipmentBackend.dto.user.SystemAdministratorDTO;
import ftn.project.ISAMedicalEquipmentBackend.repository.user.SystemAdministratorRepository;
import ftn.project.ISAMedicalEquipmentBackend.service.user.SystemAdministratorService;

@Service
public class SystemAdministratorServiceImpl implements SystemAdministratorService {
	private final SystemAdministratorRepository systemAdministratorRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public SystemAdministratorServiceImpl(
			SystemAdministratorRepository systemAdministratorRepository, 
			PasswordEncoder passwordEncoder) {
		this.systemAdministratorRepository = systemAdministratorRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public SystemAdministrator findById(long id) throws AccessDeniedException {
		return systemAdministratorRepository.getById(id);
	}
	
	@Override
	public SystemAdministrator findByUserCode(String userCode) {
		return systemAdministratorRepository.findByUserCode(userCode);
	}
	
	@Override
	public SystemAdministrator findByEmailAddress(String emailAddress) {
		return systemAdministratorRepository.findByEmailAddress(emailAddress);
	}
	
	@Override
	public SystemAdministrator findByUsername(String username) {
		return systemAdministratorRepository.findByUsername(username);
	}
	
	@Override
	public List<SystemAdministrator> findAll() throws AccessDeniedException {
		return systemAdministratorRepository.getAll();
	}
	
	@Override
	@Transactional
	public SystemAdministrator edit(SystemAdministratorDTO systemAdministratorToBeEdited) {
		System.out.println("Entered edit method of SystemAdministratorService.");
		SystemAdministrator originalSystemAdministrator = 
				findById(systemAdministratorToBeEdited.getId());
		
		originalSystemAdministrator.setUsername(systemAdministratorToBeEdited.getUsername());
		String newPassword = systemAdministratorToBeEdited.getPassword();
		if (newPassword != null) {
			originalSystemAdministrator.setPassword(passwordEncoder.encode(newPassword));
			originalSystemAdministrator.setLastPasswordResetDate(
					systemAdministratorToBeEdited.getLastPasswordResetDate());
		}
		originalSystemAdministrator.setFirstName(systemAdministratorToBeEdited.getFirstName());
		originalSystemAdministrator.setLastName(systemAdministratorToBeEdited.getLastName());
		originalSystemAdministrator.setResidence(systemAdministratorToBeEdited.getResidence());
		originalSystemAdministrator.setPopulatedPlace(
				systemAdministratorToBeEdited.getPopulatedPlace());
		originalSystemAdministrator.setCountry(systemAdministratorToBeEdited.getCountry());
		originalSystemAdministrator.setPhoneNumber(systemAdministratorToBeEdited.getPhoneNumber());
		originalSystemAdministrator.setPersonalIdentityNumber(
				systemAdministratorToBeEdited.getPersonalIdentityNumber());
		originalSystemAdministrator.setGender(systemAdministratorToBeEdited.getGender());
		originalSystemAdministrator.setProfession(systemAdministratorToBeEdited.getProfession());
		originalSystemAdministrator.setCompanyName(systemAdministratorToBeEdited.getCompanyName());
		
		return systemAdministratorRepository.saveAndFlush(originalSystemAdministrator);
	}
}
