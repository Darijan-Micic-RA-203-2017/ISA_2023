package ftn.project.ISAMedicalEquipmentBackend.domain.complaint;

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

import ftn.project.ISAMedicalEquipmentBackend.domain.user.ProcurementManagerOfHospital;

@Entity
@Table(name = "complaints")
public class Complaint {
	@Id
	@SequenceGenerator(name = "complaint_id_generator", sequenceName = "complaint_ids_sequence", 
		initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "complaint_id_generator")
	@Column(name = "id", nullable = false, updatable = false, columnDefinition = "bigserial")
	private long id;
	
	@Column(name = "content", nullable = false, columnDefinition = "text")
	private String content;
	
	@Column(name = "status", nullable = false)
	@Enumerated(value = EnumType.STRING)
	private ComplaintStatus status;
	
	@Column(name = "answer", columnDefinition = "text")
	private String answer;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "procurement_manager_id")
	private ProcurementManagerOfHospital procurementManager;
	
	@Column(name = "company_id", nullable = false)
	private long companyId;
	
	@Column(name = "company_administrator_id", nullable = false)
	private long companyAdministratorId;
	
	public Complaint() {}
	
	public Complaint(long id, String content, ComplaintStatus status, String answer, 
			ProcurementManagerOfHospital procurementManager, long companyId, 
			long companyAdministratorId) {
		this.id = id;
		this.content = content;
		this.status = status;
		this.answer = answer;
		this.procurementManager = procurementManager;
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
	
	public ProcurementManagerOfHospital getProcurementManager() {
		return procurementManager;
	}
	
	public void setProcurementManager(ProcurementManagerOfHospital procurementManager) {
		this.procurementManager = procurementManager;
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
	
	@Override
	public int hashCode() {
		final int prime = 47;
		int result = 1;
		
		result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
		result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
		result = prime * result + ((getAnswer() == null) ? 0 : getAnswer().hashCode());
		
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
		
		if (getContent() == null) {
			if (other.getContent() != null) {
				return false;
			}
		} else if (!getContent().equals(other.getContent())) {
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
		
		return true;
	}
}
