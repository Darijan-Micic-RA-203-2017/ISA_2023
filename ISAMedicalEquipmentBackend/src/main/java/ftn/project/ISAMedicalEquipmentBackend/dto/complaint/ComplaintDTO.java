package ftn.project.ISAMedicalEquipmentBackend.dto.complaint;

import ftn.project.ISAMedicalEquipmentBackend.domain.complaint.ComplaintStatus;
import ftn.project.ISAMedicalEquipmentBackend.domain.complaint.ComplaintSubject;

public class ComplaintDTO {
	private long id;
	private String content;
	private ComplaintSubject subject;
	private ComplaintStatus status;
	private String answer;
	private long procurementManagerId;
	private long companyId;
	private long companyAdministratorId;
	
	public ComplaintDTO() {}
	
	public ComplaintDTO(long id, String content, ComplaintSubject subject, ComplaintStatus status, 
			String answer, long procurementManagerId, long companyId, long companyAdministratorId) {
		this.id = id;
		this.content = content;
		this.subject = subject;
		this.status = status;
		this.answer = answer;
		this.procurementManagerId = procurementManagerId;
		this.companyId = companyId;
		this.companyAdministratorId = companyAdministratorId;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public ComplaintSubject getSubject() {
		return subject;
	}
	
	public void setSubject(ComplaintSubject subject) {
		this.subject = subject;
	}
	
	public ComplaintStatus getStatus() {
		return status;
	}
	
	public void setStatus(ComplaintStatus status) {
		this.status = status;
	}
	
	public String getAnswer() {
		return answer;
	}
	
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public long getProcurementManagerId() {
		return procurementManagerId;
	}
	
	public void setProcurementManagerId(long procurementManagerId) {
		this.procurementManagerId = procurementManagerId;
	}
	
	public long getCompanyId() {
		return companyId;
	}
	
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	
	public long getCompanyAdministratorId() {
		return companyAdministratorId;
	}
	
	public void setCompanyAdministratorId(long companyAdministratorId) {
		this.companyAdministratorId = companyAdministratorId;
	}
}
