package ftn.project.ISAMedicalEquipmentBackend.dto.kafka;

public class LocationWithUserDTO {
	private double latitude;
	private double longitude;
	private String user;
	
	public LocationWithUserDTO() {}
	
	public LocationWithUserDTO(double latitude, double longitude, String user) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.user = user;
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
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("LocationWithUserDTO [latitude=").append(latitude);
		builder.append(", longitude=").append(longitude).append(", user=").append(user).append("]");
		
		return builder.toString();
	}
}
