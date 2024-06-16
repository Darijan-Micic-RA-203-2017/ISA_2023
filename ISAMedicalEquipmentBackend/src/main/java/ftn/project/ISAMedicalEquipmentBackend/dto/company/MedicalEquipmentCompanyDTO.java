package ftn.project.ISAMedicalEquipmentBackend.dto.company;

public class MedicalEquipmentCompanyDTO {
	private long id;
	private String name;
	private double latitude;
	private double longitude;
	private String streetAndNumber;
	private String populatedPlace;
	private String country;
	private String description;
	private double averageGrade;
	private WorkTimeDTO workTime;
	
	public MedicalEquipmentCompanyDTO() {}
	
	public MedicalEquipmentCompanyDTO(long id, String name, double latitude, double longitude, 
			String streetAndNumber, String populatedPlace, String country, String description, 
			double averageGrade, WorkTimeDTO workTime) {
		this.id = id;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.streetAndNumber = streetAndNumber;
		this.populatedPlace = populatedPlace;
		this.country = country;
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
	
	public double getLatitude() {
		return latitude;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public String getStreetAndNumber() {
		return streetAndNumber;
	}
	
	public void setStreetAndNumber(String streetAndNumber) {
		this.streetAndNumber = streetAndNumber;
	}
	
	public String getPopulatedPlace() {
		return populatedPlace;
	}
	
	public void setPopulatedPlace(String populatedPlace) {
		this.populatedPlace = populatedPlace;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
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
	
	public WorkTimeDTO getWorkTime() {
		return workTime;
	}
	
	public void setWorkTime(WorkTimeDTO workTime) {
		this.workTime = workTime;
	}
}
