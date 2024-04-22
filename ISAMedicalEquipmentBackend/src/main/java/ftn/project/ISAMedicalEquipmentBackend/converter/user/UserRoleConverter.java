package ftn.project.ISAMedicalEquipmentBackend.converter.user;

import java.util.ArrayList;
import java.util.List;

import ftn.project.ISAMedicalEquipmentBackend.domain.user.UserRole;
import ftn.project.ISAMedicalEquipmentBackend.dto.user.UserRoleDTO;

public class UserRoleConverter {
	public UserRoleConverter() {}
	
	public static List<UserRoleDTO> convertToDTOsList(Iterable<UserRole> userRoles) {
		if (userRoles == null) {
			return null;
		}
		
		List<UserRoleDTO> dtosList = new ArrayList<UserRoleDTO>();
		for (UserRole uR: userRoles) {
			dtosList.add(convertToDTO(uR));
		}
		
		return dtosList;
	}
	
	public static UserRoleDTO convertToDTO(UserRole userRole) {
		if (userRole == null) {
			return null;
		}
		
		long id = userRole.getId();
		String name = userRole.getName();
		
		UserRoleDTO dto = new UserRoleDTO(id, name);
		
		return dto;
	}
}
