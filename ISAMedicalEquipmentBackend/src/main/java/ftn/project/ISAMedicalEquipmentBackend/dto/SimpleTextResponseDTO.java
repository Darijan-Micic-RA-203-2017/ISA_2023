package ftn.project.ISAMedicalEquipmentBackend.dto;

public class SimpleTextResponseDTO {
	private String textMessage;
	
	public SimpleTextResponseDTO() {}
	
	public SimpleTextResponseDTO(String textMessage) {
		this.textMessage = textMessage;
	}
	
	public String getTextMessage() {
		return textMessage;
	}
	
	public void setTextMessage(String textMessage) {
		this.textMessage = textMessage;
	}
}
