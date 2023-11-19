package ftn.project.ISAMedicalEquipmentBackend.service.impl.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ftn.project.ISAMedicalEquipmentBackend.domain.user.CompanyAdministrator;
import ftn.project.ISAMedicalEquipmentBackend.domain.user.ProcurementManagerOfHospital;
import ftn.project.ISAMedicalEquipmentBackend.domain.user.SystemAdministrator;
import ftn.project.ISAMedicalEquipmentBackend.repository.user.CompanyAdministratorRepository;
import ftn.project.ISAMedicalEquipmentBackend.repository.user.ProcurementManagerRepository;
import ftn.project.ISAMedicalEquipmentBackend.repository.user.SystemAdministratorRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	private final ProcurementManagerRepository procurementManagerRepository;
	private final CompanyAdministratorRepository companyAdministratorRepository;
	private final SystemAdministratorRepository systemAdministratorRepository;
	
	@Autowired
	public CustomUserDetailsService(ProcurementManagerRepository procurementManagerRepository, 
			CompanyAdministratorRepository companyAdministratorRepository, 
			SystemAdministratorRepository systemAdministratorRepository) {
		this.procurementManagerRepository = procurementManagerRepository;
		this.companyAdministratorRepository = companyAdministratorRepository;
		this.systemAdministratorRepository = systemAdministratorRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ProcurementManagerOfHospital procurementManager = 
				procurementManagerRepository.findByUsername(username);
		if (procurementManager != null) {
			return procurementManager;
		}
		
		CompanyAdministrator companyAdministrator = 
				companyAdministratorRepository.findByUsername(username);
		if (companyAdministrator != null) {
			return companyAdministrator;
		}
		
		SystemAdministrator systemAdministrator = 
				systemAdministratorRepository.findByUsername(username);
		if (systemAdministrator != null) {
			return systemAdministrator;
		}
		
		throw new UsernameNotFoundException(String.format(
				"No user with username \"%s\" was found!", username));
	}
}
