package ftn.project.ISAMedicalEquipmentBackend.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ftn.project.ISAMedicalEquipmentBackend.domain.user.CompanyAdministrator;

@Repository
public interface CompanyAdministratorRepository extends JpaRepository<CompanyAdministrator, Long> {
	// REFERENCE: https://thorben-janssen.com/initialize-associations-spring-data-jpa/
	// REFERENCE: https://thorben-janssen.com/hibernate-tips-difference-join-left-join-fetch-join/
	@Query(value = "SELECT ca FROM CompanyAdministrator ca JOIN FETCH ca.roles r " 
			+ "JOIN FETCH ca.company c JOIN FETCH ca.loyaltyProgram lp " 
			+ "LEFT JOIN FETCH ca.exchangeTerms excterms LEFT JOIN FETCH ca.complaints cmlpts")
	List<CompanyAdministrator> getAll();
	
	@Query(value = "SELECT ca FROM CompanyAdministrator ca JOIN FETCH ca.roles r " 
			+ "JOIN FETCH ca.company c JOIN FETCH ca.loyaltyProgram lp " 
			+ "LEFT JOIN FETCH ca.exchangeTerms excterms LEFT JOIN FETCH ca.complaints cmlpts " 
			+ "WHERE ca.id = ?1")
	CompanyAdministrator getById(long id);
	
	@Query(value = "SELECT ca FROM CompanyAdministrator ca JOIN FETCH ca.roles r " 
			+ "JOIN FETCH ca.company c JOIN FETCH ca.loyaltyProgram lp " 
			+ "LEFT JOIN FETCH ca.exchangeTerms excterms LEFT JOIN FETCH ca.complaints cmlpts " 
			+ "WHERE ca.userCode = ?1")
	CompanyAdministrator findByUserCode(String userCode);
	
	@Query(value = "SELECT ca FROM CompanyAdministrator ca JOIN FETCH ca.roles r " 
			+ "JOIN FETCH ca.company c JOIN FETCH ca.loyaltyProgram lp " 
			+ "LEFT JOIN FETCH ca.exchangeTerms excterms LEFT JOIN FETCH ca.complaints cmlpts " 
			+ "WHERE ca.emailAddress = ?1")
	CompanyAdministrator findByEmailAddress(String emailAddress);
	
	@Query(value = "SELECT ca FROM CompanyAdministrator ca JOIN FETCH ca.roles r " 
			+ "JOIN FETCH ca.company c JOIN FETCH ca.loyaltyProgram lp " 
			+ "LEFT JOIN FETCH ca.exchangeTerms excterms LEFT JOIN FETCH ca.complaints cmlpts " 
			+ "WHERE ca.username = ?1")
	CompanyAdministrator findByUsername(String username);
	
	@Query(value = "SELECT ca FROM CompanyAdministrator ca JOIN FETCH ca.roles r " 
			+ "WHERE ca.username = ?1")
	CompanyAdministrator getByUsernameForAuthPurposes(String username);
}
