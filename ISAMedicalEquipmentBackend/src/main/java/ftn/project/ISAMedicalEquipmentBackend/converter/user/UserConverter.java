package ftn.project.ISAMedicalEquipmentBackend.converter.user;

import java.util.ArrayList;
import java.util.List;

import ftn.project.ISAMedicalEquipmentBackend.domain.user.CompanyAdministrator;
import ftn.project.ISAMedicalEquipmentBackend.domain.user.ProcurementManager;
import ftn.project.ISAMedicalEquipmentBackend.domain.user.SystemAdministrator;
import ftn.project.ISAMedicalEquipmentBackend.domain.user.User;
import ftn.project.ISAMedicalEquipmentBackend.dto.user.UserDTO;

public class UserConverter {
	public UserConverter() {}
	
	public static List<UserDTO> convertToDTOsList(Iterable<User> users) {
		if (users == null) {
			return null;
		}
		
		List<UserDTO> dtosList = new ArrayList<UserDTO>();
		for (User u: users) {
			dtosList.add(convertToDTO(u));
		}
		
		return dtosList;
	}
	
	public static UserDTO convertToDTO(User user) {
		if (user == null) {
			return null;
		}
		
		// REFERENCE: https://stackoverflow.com/questions/898909/is-it-possible-to-call-subclasses-methods-on-a-superclass-object
		if (user instanceof ProcurementManager) {
			return ProcurementManagerConverter.convertToDTO((ProcurementManager) user);
		} else if (user instanceof CompanyAdministrator) {
			return CompanyAdministratorConverter.convertToDTO((CompanyAdministrator) user);
		} else if (user instanceof SystemAdministrator) {
			return SystemAdministratorConverter.convertToDTO((SystemAdministrator) user);
		}
		
		return null;
	}
}
