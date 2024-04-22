package ftn.project.ISAMedicalEquipmentBackend.repository.term;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ftn.project.ISAMedicalEquipmentBackend.domain.term.ExchangeTerm;

@Repository
public interface ExchangeTermRepository extends JpaRepository<ExchangeTerm, Long> {
	List<ExchangeTerm> findByStartingTime(Timestamp startingTime);
	
	List<ExchangeTerm> findByEndingTime(Timestamp endingTime);
	
	@Query(value = "SELECT et FROM ExchangeTerm et WHERE " 
			+ "et.startingTime >= ?1 AND et.endingTime <= ?2")
	List<ExchangeTerm> getAllOnSpecificDate(Timestamp date, Timestamp oneDayLater);
	
	@Query(value = "SELECT et FROM ExchangeTerm et JOIN FETCH et.company c WHERE " 
			+ "et.startingTime >= ?1 AND et.endingTime <= ?2 AND c.id = ?3")
	List<ExchangeTerm> getAllOnSpecificDateOfCompany(Timestamp date, Timestamp oneDayLater, 
			long companyId);
	
	@Query(value = "SELECT et FROM ExchangeTerm et JOIN FETCH et.procurementManager pm " 
			+ "WHERE pm.id = ?1")
	List<ExchangeTerm> getAllOfProcurementManager(long procurementManagerId);
	
	@Query(value = "SELECT et FROM ExchangeTerm et JOIN FETCH et.company c " 
			+ "WHERE c.id = ?1")
	List<ExchangeTerm> getAllOfCompany(long companyId);
	
	@Query(value = "SELECT et FROM ExchangeTerm et JOIN FETCH et.companyAdministrator ca " 
			+ "WHERE ca.id = ?1")
	List<ExchangeTerm> getAllOfCompanyAdministrator(long companyAdministratorId);
}
