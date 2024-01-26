package ftn.project.ISAMedicalEquipmentBackend.dto;

import java.sql.Timestamp;

public class DateTimeWrapperDTO {
	private Timestamp dateTime;
	
	public DateTimeWrapperDTO() {}
	
	public DateTimeWrapperDTO(Timestamp dateTime) {
		this.dateTime = dateTime;
	}
	
	public Timestamp getDateTime() {
		return dateTime;
	}
	
	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}
}
