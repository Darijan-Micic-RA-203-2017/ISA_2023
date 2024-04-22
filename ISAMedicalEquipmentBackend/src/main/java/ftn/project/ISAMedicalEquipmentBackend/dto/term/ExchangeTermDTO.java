package ftn.project.ISAMedicalEquipmentBackend.dto.term;

import java.sql.Timestamp;

public class ExchangeTermDTO {
	private long id;
	private Timestamp startingTime;
	private Timestamp endingTime;
	private long procurementManagerId;
	private long companyId;
	private long companyAdministratorId;
	
	public ExchangeTermDTO() {}
	
	public ExchangeTermDTO(long id, Timestamp startingTime, Timestamp endingTime, 
			long procurementManagerId, long companyId, long companyAdministratorId) {
		this.id = id;
		this.startingTime = startingTime;
		this.endingTime = endingTime;
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
	
	public Timestamp getStartingTime() {
		return startingTime;
	}
	
	public void setStartingTime(Timestamp startingTime) {
		this.startingTime = startingTime;
	}
	
	public Timestamp getEndingTime() {
		return endingTime;
	}
	
	public void setEndingTime(Timestamp endingTime) {
		this.endingTime = endingTime;
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
