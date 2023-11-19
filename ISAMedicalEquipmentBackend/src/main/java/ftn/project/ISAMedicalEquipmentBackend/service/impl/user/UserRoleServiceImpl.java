package ftn.project.ISAMedicalEquipmentBackend.service.impl.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.project.ISAMedicalEquipmentBackend.domain.user.UserRole;
import ftn.project.ISAMedicalEquipmentBackend.repository.user.UserRoleRepository;
import ftn.project.ISAMedicalEquipmentBackend.service.user.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {
	private final UserRoleRepository userRoleRepository;
	
	@Autowired
	public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
		this.userRoleRepository = userRoleRepository;
	}
	
	@Override
	public UserRole findById(long id) {
		return userRoleRepository.findById(id).orElse(null);
	}
	
	@Override
	public UserRole findByName(String name) {
		return userRoleRepository.findByName(name);
	}
	
	@Override
	public List<UserRole> findAll() {
		return userRoleRepository.findAll();
	}
}
