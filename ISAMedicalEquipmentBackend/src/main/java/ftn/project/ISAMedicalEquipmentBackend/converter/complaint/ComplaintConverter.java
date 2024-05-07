package ftn.project.ISAMedicalEquipmentBackend.converter.complaint;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import ftn.project.ISAMedicalEquipmentBackend.domain.complaint.Complaint;
import ftn.project.ISAMedicalEquipmentBackend.domain.complaint.ComplaintStatus;
import ftn.project.ISAMedicalEquipmentBackend.domain.complaint.ComplaintSubject;
import ftn.project.ISAMedicalEquipmentBackend.dto.complaint.ComplaintDTO;

public class ComplaintConverter {
	public ComplaintConverter() {}
	
	public static List<ComplaintDTO> convertToDTOsList(Iterable<Complaint> complaints) {
		if (complaints == null) {
			return null;
		}
		
		List<ComplaintDTO> dtosList = new ArrayList<ComplaintDTO>();
		for (Complaint c: complaints) {
			dtosList.add(convertToDTO(c));
		}
		
		return dtosList;
	}
	
	public static ComplaintDTO convertToDTO(Complaint complaint) {
		if (complaint == null) {
			return null;
		}
		
		long id = complaint.getId();
		Timestamp submittedAt = complaint.getSubmittedAt();
		String content = complaint.getContent();
		ComplaintSubject subject = complaint.getSubject();
		ComplaintStatus status = complaint.getStatus();
		String answer = complaint.getAnswer();
		Timestamp answeredAt = complaint.getAnsweredAt();
		long procurementManagerId = complaint.getProcurementManager().getId();
		long companyId = complaint.getCompany().getId();
		long companyAdministratorId = complaint.getCompanyAdministrator().getId();
		
		ComplaintDTO dto = new ComplaintDTO(id, submittedAt, content, subject, status, answer, 
				answeredAt, procurementManagerId, companyId, companyAdministratorId);
		
		return dto;
	}
}
