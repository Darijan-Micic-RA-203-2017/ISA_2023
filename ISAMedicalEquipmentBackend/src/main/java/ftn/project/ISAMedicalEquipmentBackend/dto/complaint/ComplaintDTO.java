package ftn.project.ISAMedicalEquipmentBackend.dto.complaint;

import java.sql.Timestamp;

import ftn.project.ISAMedicalEquipmentBackend.domain.complaint.ComplaintStatus;
import ftn.project.ISAMedicalEquipmentBackend.domain.complaint.ComplaintSubject;

public class ComplaintDTO {
	private long id;
	private Timestamp submittedAt;
	private String content;
	private ComplaintSubject subject;
	private ComplaintStatus status;
	private String answer;
	private Timestamp answeredAt;
	private long procurementManagerId;
	private long companyId;
	private long companyAdministratorId;
	
	public ComplaintDTO() {}
	
	public ComplaintDTO(long id, Timestamp submittedAt, String content, ComplaintSubject subject, 
			ComplaintStatus status, String answer, Timestamp answeredAt, long procurementManagerId, 
			long companyId, long companyAdministratorId) {
		this.id = id;
		this.submittedAt = submittedAt;
		this.content = content;
		this.subject = subject;
		this.status = status;
		this.answer = answer;
		this.answeredAt = answeredAt;
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
	
	public Timestamp getSubmittedAt() {
		return submittedAt;
	}
	
	public void setSubmittedAt(Timestamp submittedAt) {
		this.submittedAt = submittedAt;
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
	
	public Timestamp getAnsweredAt() {
		return answeredAt;
	}
	
	public void setAnsweredAt(Timestamp answeredAt) {
		this.answeredAt = answeredAt;
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
