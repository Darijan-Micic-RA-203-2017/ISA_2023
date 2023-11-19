package ftn.project.ISAMedicalEquipmentBackend.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.project.ISAMedicalEquipmentBackend.domain.user.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
	UserRole findByName(String name);
}
