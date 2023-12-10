package ftn.project.ISAMedicalEquipmentBackend.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.project.ISAMedicalEquipmentBackend.domain.user.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
	UserRole findByName(String name);
}
