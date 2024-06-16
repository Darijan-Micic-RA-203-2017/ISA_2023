package ftn.project.ISAMedicalEquipmentBackend.dto.kafka;

public class LocationDTO {
	private double latitude;
	private double longitude;
	
	public LocationDTO() {}
	
	public LocationDTO(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
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
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("LocationDTO [latitude=").append(latitude);
		builder.append(", longitude=").append(longitude).append("]");
		
		return builder.toString();
	}
}
