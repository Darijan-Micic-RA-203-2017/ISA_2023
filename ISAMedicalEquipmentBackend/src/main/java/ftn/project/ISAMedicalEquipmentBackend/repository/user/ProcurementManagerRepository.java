package ftn.project.ISAMedicalEquipmentBackend.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ftn.project.ISAMedicalEquipmentBackend.domain.user.ProcurementManager;

@Repository
public interface ProcurementManagerRepository extends 
		JpaRepository<ProcurementManager, Long> {
	// REFERENCE: https://thorben-janssen.com/initialize-associations-spring-data-jpa/
	// REFERENCE: https://thorben-janssen.com/hibernate-tips-difference-join-left-join-fetch-join/
	@Query(value = "SELECT pm FROM ProcurementManager pm JOIN FETCH pm.roles r " 
			+ "JOIN FETCH pm.loyaltyProgram lp LEFT JOIN FETCH pm.exchangeTerms excterms " 
			+ "LEFT JOIN FETCH pm.complaints cmplts")
	List<ProcurementManager> getAll();
	
	@Query(value = "SELECT pm FROM ProcurementManager pm JOIN FETCH pm.roles r " 
			+ "JOIN FETCH pm.loyaltyProgram lp LEFT JOIN FETCH pm.exchangeTerms excterms " 
			+ "LEFT JOIN FETCH pm.complaints cmplts " 
			+ "WHERE pm.id = ?1")
	ProcurementManager getById(long id);
	
	@Query(value = "SELECT pm FROM ProcurementManager pm JOIN FETCH pm.roles r " 
			+ "JOIN FETCH pm.loyaltyProgram lp LEFT JOIN FETCH pm.exchangeTerms excterms " 
			+ "LEFT JOIN FETCH pm.complaints cmplts " 
			+ "WHERE pm.userCode = ?1")
	ProcurementManager findByUserCode(String userCode);
	
	@Query(value = "SELECT pm FROM ProcurementManager pm JOIN FETCH pm.roles r " 
			+ "JOIN FETCH pm.loyaltyProgram lp LEFT JOIN FETCH pm.exchangeTerms excterms " 
			+ "LEFT JOIN FETCH pm.complaints cmplts " 
			+ "WHERE pm.emailAddress = ?1")
	ProcurementManager findByEmailAddress(String emailAddress);
	
	@Query(value = "SELECT pm FROM ProcurementManager pm JOIN FETCH pm.roles r " 
			+ "JOIN FETCH pm.loyaltyProgram lp LEFT JOIN FETCH pm.exchangeTerms excterms " 
			+ "LEFT JOIN FETCH pm.complaints cmplts " 
			+ "WHERE pm.username = ?1")
	ProcurementManager findByUsername(String username);
}
