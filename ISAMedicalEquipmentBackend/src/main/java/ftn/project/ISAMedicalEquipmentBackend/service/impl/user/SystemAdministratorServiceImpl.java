package ftn.project.ISAMedicalEquipmentBackend.service.impl.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import ftn.project.ISAMedicalEquipmentBackend.domain.user.SystemAdministrator;
import ftn.project.ISAMedicalEquipmentBackend.repository.user.SystemAdministratorRepository;
import ftn.project.ISAMedicalEquipmentBackend.service.user.SystemAdministratorService;

@Service
public class SystemAdministratorServiceImpl implements SystemAdministratorService {
	private final SystemAdministratorRepository systemAdministratorRepository;
	
	@Autowired
	public SystemAdministratorServiceImpl(
			SystemAdministratorRepository systemAdministratorRepository) {
		this.systemAdministratorRepository = systemAdministratorRepository;
	}
	
	@Override
	public SystemAdministrator findById(long id) throws AccessDeniedException {
		return systemAdministratorRepository.findById(id).orElse(null);
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
		return systemAdministratorRepository.findAll();
	}
}
