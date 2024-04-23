package ftn.project.ISAMedicalEquipmentBackend.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ftn.project.ISAMedicalEquipmentBackend.domain.user.SystemAdministrator;

@Repository
public interface SystemAdministratorRepository extends JpaRepository<SystemAdministrator, Long> {
	@Query(value = "SELECT sa FROM SystemAdministrator sa JOIN FETCH sa.roles r")
	List<SystemAdministrator> getAll();
	
	@Query(value = "SELECT sa FROM SystemAdministrator sa JOIN FETCH sa.roles r " 
			+ "WHERE sa.id = ?1")
	SystemAdministrator getById(long id);
	
	@Query(value = "SELECT sa FROM SystemAdministrator sa JOIN FETCH sa.roles r " 
			+ "WHERE sa.userCode = ?1")
	SystemAdministrator findByUserCode(String userCode);
	
	@Query(value = "SELECT sa FROM SystemAdministrator sa JOIN FETCH sa.roles r " 
			+ "WHERE sa.emailAddress = ?1")
	SystemAdministrator findByEmailAddress(String emailAddress);
	
	@Query(value = "SELECT sa FROM SystemAdministrator sa JOIN FETCH sa.roles r " 
			+ "WHERE sa.username = ?1")
	SystemAdministrator findByUsername(String username);
	
	@Query(value = "SELECT sa FROM SystemAdministrator sa JOIN FETCH sa.roles r " 
			+ "WHERE sa.username = ?1")
	SystemAdministrator getByUsernameForAuthPurposes(String username);
}
