package ftn.project.ISAMedicalEquipmentBackend.converter.term;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import ftn.project.ISAMedicalEquipmentBackend.domain.term.ExchangeTerm;
import ftn.project.ISAMedicalEquipmentBackend.dto.term.ExchangeTermDTO;

public class ExchangeTermConverter {
	public ExchangeTermConverter() {}
	
	public static List<ExchangeTermDTO> convertToDTOsList(Iterable<ExchangeTerm> exchangeTerms) {
		if (exchangeTerms == null) {
			return null;
		}
		
		List<ExchangeTermDTO> dtosList = new ArrayList<ExchangeTermDTO>();
		for (ExchangeTerm equ: exchangeTerms) {
			dtosList.add(convertToDTO(equ));
		}
		
		return dtosList;
	}
	
	public static ExchangeTermDTO convertToDTO(ExchangeTerm exchangeTerm) {
		if (exchangeTerm == null) {
			return null;
		}
		
		long id = exchangeTerm.getId();
		Timestamp startingTime = exchangeTerm.getStartingTime();
		Timestamp endingTime = exchangeTerm.getEndingTime();
		long procurementManagerId = exchangeTerm.getProcurementManager().getId();
		long companyId = exchangeTerm.getCompany().getId();
		long companyAdministratorId = exchangeTerm.getCompanyAdministrator().getId();
		
		ExchangeTermDTO dto = new ExchangeTermDTO(id, startingTime, endingTime, 
				procurementManagerId, companyId, companyAdministratorId);
		
		return dto;
	}
}
