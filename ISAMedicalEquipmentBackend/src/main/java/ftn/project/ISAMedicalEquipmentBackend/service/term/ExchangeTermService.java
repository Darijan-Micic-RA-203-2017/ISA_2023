package ftn.project.ISAMedicalEquipmentBackend.service.term;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.security.access.AccessDeniedException;

import ftn.project.ISAMedicalEquipmentBackend.domain.term.ExchangeTerm;
import ftn.project.ISAMedicalEquipmentBackend.dto.term.ExchangeTermDTO;

public interface ExchangeTermService {
	ExchangeTerm findById(long id) throws AccessDeniedException;
	List<ExchangeTerm> findByStartingTime(Timestamp startingTime);
	List<ExchangeTerm> findByEndingTime(Timestamp endingTime);
	List<ExchangeTerm> findAllOnSpecificDate(Timestamp date);
	List<ExchangeTerm> findAllOnSpecificDateOfCompany(Timestamp date, long companyId);
	List<ExchangeTerm> findAllOfProcurementManager(long procurementManagerId);
	List<ExchangeTerm> findAllOfCompany(long companyId);
	List<ExchangeTerm> findAllOfAdministrator(long administratorId);
	List<ExchangeTerm> findAll();
	ExchangeTerm reserveTerm(ExchangeTermDTO exchangeTermDTO);
}
