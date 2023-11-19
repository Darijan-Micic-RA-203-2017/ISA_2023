package ftn.project.ISAMedicalEquipmentBackend.service.user;

import java.util.List;

import ftn.project.ISAMedicalEquipmentBackend.domain.user.UserRole;

public interface UserRoleService {
	UserRole findById(long id);
	UserRole findByName(String name);
	List<UserRole> findAll();
}
