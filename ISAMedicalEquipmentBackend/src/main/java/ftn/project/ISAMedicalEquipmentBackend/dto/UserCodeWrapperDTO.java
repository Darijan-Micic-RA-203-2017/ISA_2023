package ftn.project.ISAMedicalEquipmentBackend.dto;

public class UserCodeWrapperDTO {
	private String userCode;
	
	public UserCodeWrapperDTO() {}
	
	public UserCodeWrapperDTO(String userCode) {
		this.userCode = userCode;
	}
	
	public String getUserCode() {
		return userCode;
	}
	
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
}
