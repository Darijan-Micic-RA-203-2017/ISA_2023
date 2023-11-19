package ftn.project.ISAMedicalEquipmentBackend.dto.company;

public class MedicalEquipmentCompanyDTO {
	private long id;
	private String name;
	private String address;
	private String description;
	private double averageGrade;
	private String workTime;
	
	public MedicalEquipmentCompanyDTO() {}
	
	public MedicalEquipmentCompanyDTO(long id, String name, String address, String description, 
			double averageGrade, String workTime) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.description = description;
		this.averageGrade = averageGrade;
		this.workTime = workTime;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public double getAverageGrade() {
		return averageGrade;
	}
	
	public void setAverageGrade(double averageGrade) {
		this.averageGrade = averageGrade;
	}
	
	public String getWorkTime() {
		return workTime;
	}
	
	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}
}
