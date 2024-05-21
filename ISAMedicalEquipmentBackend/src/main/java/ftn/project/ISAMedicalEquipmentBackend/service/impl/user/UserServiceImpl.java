package ftn.project.ISAMedicalEquipmentBackend.service.impl.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import ftn.project.ISAMedicalEquipmentBackend.domain.user.CompanyAdministrator;
import ftn.project.ISAMedicalEquipmentBackend.domain.user.ProcurementManager;
import ftn.project.ISAMedicalEquipmentBackend.domain.user.SystemAdministrator;
import ftn.project.ISAMedicalEquipmentBackend.domain.user.User;
import ftn.project.ISAMedicalEquipmentBackend.dto.user.CompanyAdministratorDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.user.ProcurementManagerDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.user.SystemAdministratorDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.user.UserDTO;
import ftn.project.ISAMedicalEquipmentBackend.service.user.CompanyAdministratorService;
import ftn.project.ISAMedicalEquipmentBackend.service.user.ProcurementManagerService;
import ftn.project.ISAMedicalEquipmentBackend.service.user.SystemAdministratorService;
import ftn.project.ISAMedicalEquipmentBackend.service.user.UserService;

@Service
public class UserServiceImpl implements UserService {
	private final ProcurementManagerService procurementManagerService;
	private final CompanyAdministratorService companyAdministratorService;
	private final SystemAdministratorService systemAdministratorService;
	
	@Autowired
	public UserServiceImpl(ProcurementManagerService procurementManagerService, 
			CompanyAdministratorService companyAdministratorService, 
			SystemAdministratorService systemAdministratorService) {
		this.procurementManagerService = procurementManagerService;
		this.companyAdministratorService = companyAdministratorService;
		this.systemAdministratorService = systemAdministratorService;
	}
	
	@Override
	public ProcurementManagerService getProcurementManagerService() {
		return procurementManagerService;
	}
	
	@Override
	public CompanyAdministratorService getCompanyAdministratorService() {
		return companyAdministratorService;
	}
	
	@Override
	public SystemAdministratorService getSystemAdministratorService() {
		return systemAdministratorService;
	}
	
	@Override
	public User findById(long id) throws AccessDeniedException {
		ProcurementManager procurementManager = procurementManagerService.findById(id);
		if (procurementManager != null) {
			System.out.println("\nUsername of found procurement manager: " 
					+ procurementManager.getUsername() + "\n");
			return procurementManager;
		}
		System.out.println("\nNo procurement manager with specified id was found!\n");
		
		CompanyAdministrator companyAdministrator = companyAdministratorService.findById(id);
		if (companyAdministrator != null) {
			System.out.println("\nUsername of found company administrator: " 
					+ companyAdministrator.getUsername() + "\n");
			return companyAdministrator;
		}
		System.out.println("\nNo company administrator with specified id was found!\n");
		
		SystemAdministrator systemAdministrator = systemAdministratorService.findById(id);
		if (systemAdministrator != null) {
			System.out.println("\nUsername of found system administrator: " 
					+ systemAdministrator.getUsername() + "\n");
			return systemAdministrator;
		}
		System.out.println("\nNo system administrator with specified id was found!\n");
		
		return null;
	}
	
	@Override
	public User findByUserCode(String userCode) {
		ProcurementManager procurementManager = 
				procurementManagerService.findByUserCode(userCode);
		if (procurementManager != null) {
			return procurementManager;
		}
		
		CompanyAdministrator companyAdministrator = 
				companyAdministratorService.findByUserCode(userCode);
		if (companyAdministrator != null) {
			return companyAdministrator;
		}
		
		SystemAdministrator systemAdministrator = 
				systemAdministratorService.findByUserCode(userCode);
		if (systemAdministrator != null) {
			return systemAdministrator;
		}
		
		return null;
	}
	
	@Override
	public User findByEmailAddress(String emailAddress) {
		ProcurementManager procurementManager = 
				procurementManagerService.findByEmailAddress(emailAddress);
		if (procurementManager != null) {
			return procurementManager;
		}
		
		CompanyAdministrator companyAdministrator = 
				companyAdministratorService.findByEmailAddress(emailAddress);
		if (companyAdministrator != null) {
			return companyAdministrator;
		}
		
		SystemAdministrator systemAdministrator = 
				systemAdministratorService.findByEmailAddress(emailAddress);
		if (systemAdministrator != null) {
			return systemAdministrator;
		}
		
		return null;
	}
	
	@Override
	public User findByUsername(String username) {
		ProcurementManager procurementManager = 
				procurementManagerService.findByUsername(username);
		if (procurementManager != null) {
			return procurementManager;
		}
		
		CompanyAdministrator companyAdministrator = 
				companyAdministratorService.findByUsername(username);
		if (companyAdministrator != null) {
			return companyAdministrator;
		}
		
		SystemAdministrator systemAdministrator = 
				systemAdministratorService.findByUsername(username);
		if (systemAdministrator != null) {
			return systemAdministrator;
		}
		
		return null;
	}
	
	@Override
	public List<User> findAll() throws AccessDeniedException {
		List<User> allUsers = new ArrayList<User>();
		
		List<ProcurementManager> procurementManagers = procurementManagerService.findAll();
		System.out.println("\nTotal number of procurement managers: " + procurementManagers.size());
		if (!procurementManagers.isEmpty()) {
			allUsers.addAll(procurementManagers);
		}
		
		List<CompanyAdministrator> companyAdministrators = companyAdministratorService.findAll();
		System.out.println("\nTotal number of company administrators: " + companyAdministrators.size());
		if (!companyAdministrators.isEmpty()) {
			allUsers.addAll(companyAdministrators);
		}
		
		List<SystemAdministrator> systemAdministrators = systemAdministratorService.findAll();
		System.out.println("\nTotal number of system administrators: " + systemAdministrators.size());
		if (!systemAdministrators.isEmpty()) {
			allUsers.addAll(systemAdministrators);
		}
		
		return allUsers;
	}
	
	@Override
	public User edit(UserDTO userToBeEdited) {
		ProcurementManagerDTO pmToBeEdited = null;
		CompanyAdministratorDTO caToBeEdited = null;
		SystemAdministratorDTO saToBeEdited = null;
		// REFERENCE: https://www.simplilearn.com/tutorials/java-tutorial/instanceof-in-java
		if (userToBeEdited instanceof ProcurementManagerDTO) {
			pmToBeEdited = (ProcurementManagerDTO) userToBeEdited;
			
			return procurementManagerService.edit(pmToBeEdited);
		} else if (userToBeEdited instanceof CompanyAdministratorDTO) {
			caToBeEdited = (CompanyAdministratorDTO) userToBeEdited;
			
			return companyAdministratorService.edit(caToBeEdited);
		} else if (userToBeEdited instanceof SystemAdministratorDTO) {
			saToBeEdited = (SystemAdministratorDTO) userToBeEdited;
			
			return systemAdministratorService.edit(saToBeEdited);
		}
		
		return null;
	}
}
