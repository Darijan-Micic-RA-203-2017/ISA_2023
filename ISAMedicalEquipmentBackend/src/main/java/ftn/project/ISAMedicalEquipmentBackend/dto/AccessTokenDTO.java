package ftn.project.ISAMedicalEquipmentBackend.dto;

public class AccessTokenDTO {
	private String accessToken;
	private long expiresIn;
	
	public AccessTokenDTO() {}
	
	public AccessTokenDTO(String accessToken, long expiresIn) {
		this.accessToken = accessToken;
		this.expiresIn = expiresIn;
	}
	
	public String getAccessToken() {
		return accessToken;
	}
	
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public long getExpiresIn() {
		return expiresIn;
	}
	
	public void setExpiresIn(long expiresIn) {
		this.expiresIn = expiresIn;
	}
}
