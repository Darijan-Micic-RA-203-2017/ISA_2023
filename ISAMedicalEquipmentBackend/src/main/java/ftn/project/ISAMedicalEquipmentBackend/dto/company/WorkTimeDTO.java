package ftn.project.ISAMedicalEquipmentBackend.dto.company;

public class WorkTimeDTO {
	private long id;
	private String onMondaysThroughFridays;
	private String onSaturdays;
	private String onSundays;
	
	public WorkTimeDTO() {}
	
	public WorkTimeDTO(long id, String onMondaysThroughFridays, String onSaturdays, 
			String onSundays) {
		this.id = id;
		this.onMondaysThroughFridays = onMondaysThroughFridays;
		this.onSaturdays = onSaturdays;
		this.onSundays = onSundays;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getOnMondaysThroughFridays() {
		return onMondaysThroughFridays;
	}
	
	public void setOnMondaysThroughFridays(String onMondaysThroughFridays) {
		this.onMondaysThroughFridays = onMondaysThroughFridays;
	}
	
	public String getOnSaturdays() {
		return onSaturdays;
	}
	
	public void setOnSaturdays(String onSaturdays) {
		this.onSaturdays = onSaturdays;
	}
	
	public String getOnSundays() {
		return onSundays;
	}
	
	public void setOnSundays(String onSundays) {
		this.onSundays = onSundays;
	}
}
