package ftn.project.ISAMedicalEquipmentBackend.service.impl.term;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import ftn.project.ISAMedicalEquipmentBackend.domain.term.ExchangeTerm;
import ftn.project.ISAMedicalEquipmentBackend.repository.term.ExchangeTermRepository;
import ftn.project.ISAMedicalEquipmentBackend.service.term.ExchangeTermService;

@Service
public class ExchangeTermServiceImpl implements ExchangeTermService {
	private final ExchangeTermRepository exchangeTermRepository;
	
	@Autowired
	public ExchangeTermServiceImpl(ExchangeTermRepository exchangeTermRepository) {
		this.exchangeTermRepository = exchangeTermRepository;
	}

	@Override
	public ExchangeTerm findById(long id) throws AccessDeniedException {
		return exchangeTermRepository.findById(id).orElse(null);
	}

	@Override
	public List<ExchangeTerm> findByStartingTime(Timestamp startingTime) {
		return exchangeTermRepository.findByStartingTime(startingTime);
	}

	@Override
	public List<ExchangeTerm> findByEndingTime(Timestamp endingTime) {
		return exchangeTermRepository.findByEndingTime(endingTime);
	}
	
	@Override
	public List<ExchangeTerm> findAllOnSpecificDate(Timestamp date) {
		// REFERENCE: https://www.baeldung.com/java-increment-date
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		Timestamp oneDayLater = new Timestamp(calendar.getTime().getTime());
		
		return exchangeTermRepository.getAllOnSpecificDate(date, oneDayLater);
	}

	@Override
	public List<ExchangeTerm> findAllOnSpecificDateOfCompany(Timestamp date, long companyId) {
		// REFERENCE: https://www.baeldung.com/java-increment-date
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		Timestamp oneDayLater = new Timestamp(calendar.getTime().getTime());
		
		return exchangeTermRepository.getAllOnSpecificDateOfCompany(date, oneDayLater, companyId);
	}

	@Override
	public List<ExchangeTerm> findAllOfProcurementManager(long procurementManagerId) {
		return exchangeTermRepository.getAllOfProcurementManager(procurementManagerId);
	}

	@Override
	public List<ExchangeTerm> findAllOfCompany(long companyId) {
		return exchangeTermRepository.getAllOfCompany(companyId);
	}

	@Override
	public List<ExchangeTerm> findAllOfAdministrator(long administratorId) {
		return exchangeTermRepository.getAllOfAdministrator(administratorId);
	}

	@Override
	public List<ExchangeTerm> findAll() {
		return exchangeTermRepository.findAll();
	}
}
