package ftn.project.ISAMedicalEquipmentBackend.domain.complaint;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ftn.project.ISAMedicalEquipmentBackend.domain.company.MedicalEquipmentCompany;
import ftn.project.ISAMedicalEquipmentBackend.domain.user.CompanyAdministrator;
import ftn.project.ISAMedicalEquipmentBackend.domain.user.ProcurementManager;

@Entity
@Table(name = "complaints")
public class Complaint {
	@Id
	@SequenceGenerator(name = "complaint_id_generator", sequenceName = "complaint_ids_sequence", 
		initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "complaint_id_generator")
	@Column(name = "id", nullable = false, updatable = false, columnDefinition = "bigserial")
	private long id;
	
	@Column(name = "submitted_at", nullable = false)
	private Timestamp submittedAt;
	
	@Column(name = "content", nullable = false, columnDefinition = "text")
	private String content;
	
	@Column(name = "subject", nullable = false)
	@Enumerated(value = EnumType.STRING)
	private ComplaintSubject subject;
	
	@Column(name = "status", nullable = false)
	@Enumerated(value = EnumType.STRING)
	private ComplaintStatus status;
	
	@Column(name = "answer", columnDefinition = "text")
	private String answer;
	
	@Column(name = "answered_at")
	private Timestamp answeredAt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "procurement_manager_id")
	private ProcurementManager procurementManager;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	private MedicalEquipmentCompany company;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_administrator_id")
	private CompanyAdministrator companyAdministrator;
	
	public Complaint() {}
	
	public Complaint(long id, Timestamp submittedAt, String content, ComplaintSubject subject, 
			ComplaintStatus status, String answer, Timestamp answeredAt, 
			ProcurementManager procurementManager, MedicalEquipmentCompany company, 
			CompanyAdministrator companyAdministrator) {
		this.id = id;
		this.submittedAt = submittedAt;
		this.content = content;
		this.subject = subject;
		this.status = status;
		this.answer = answer;
		this.answeredAt = answeredAt;
		this.procurementManager = procurementManager;
		this.company = company;
		this.companyAdministrator = companyAdministrator;
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
	
	public ProcurementManager getProcurementManager() {
		return procurementManager;
	}
	
	public void setProcurementManager(ProcurementManager procurementManager) {
		this.procurementManager = procurementManager;
	}
	
	public MedicalEquipmentCompany getCompany() {
		return company;
	}
	
	public void setCompany(MedicalEquipmentCompany company) {
		this.company = company;
	}
	
	public CompanyAdministrator getCompanyAdministrator() {
		return companyAdministrator;
	}
	
	public void setCompanyAdministrator(CompanyAdministrator companyAdministrator) {
		this.companyAdministrator = companyAdministrator;
	}
	
	@Override
	public int hashCode() {
		final int prime = 47;
		int result = 1;
		
		result = prime * result + ((getSubmittedAt() == null) ? 0 : getSubmittedAt().hashCode());
		result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
		result = prime * result + ((getSubject() == null) ? 0 : getSubject().hashCode());
		result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
		result = prime * result + ((getAnswer() == null) ? 0 : getAnswer().hashCode());
		result = prime * result + ((getAnsweredAt() == null) ? 0 : getAnsweredAt().hashCode());
		
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof Complaint)) {
			return false;
		}
		
		Complaint other = (Complaint) obj;
		
		if (getId() != other.getId()) {
			return false;
		}
		
		if (getSubmittedAt() == null) {
			if (other.getSubmittedAt() != null) {
				return false;
			}
		} else if (!getSubmittedAt().equals(other.getSubmittedAt())) {
			return false;
		}
		
		if (getContent() == null) {
			if (other.getContent() != null) {
				return false;
			}
		} else if (!getContent().equals(other.getContent())) {
			return false;
		}
		
		if (getSubject() != other.getSubject()) {
			return false;
		}
		
		if (getStatus() != other.getStatus()) {
			return false;
		}
		
		if (getAnswer() == null) {
			if (other.getAnswer() != null) {
				return false;
			}
		} else if (!getAnswer().equals(other.getAnswer())) {
			return false;
		}
		
		if (getAnsweredAt() == null) {
			if (other.getAnsweredAt() != null) {
				return false;
			}
		} else if (!getAnsweredAt().equals(other.getAnsweredAt())) {
			return false;
		}
		
		return true;
	}
}
