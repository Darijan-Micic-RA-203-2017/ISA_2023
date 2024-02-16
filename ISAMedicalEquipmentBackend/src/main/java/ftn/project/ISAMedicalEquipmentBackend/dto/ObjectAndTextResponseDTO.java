package ftn.project.ISAMedicalEquipmentBackend.dto;

public class ObjectAndTextResponseDTO {
	private Object object;
	private String textMessage;
	
	public ObjectAndTextResponseDTO() {}
	
	public ObjectAndTextResponseDTO(Object object, String textMessage) {
		this.object = object;
		this.textMessage = textMessage;
	}
	
	public Object getObject() {
		return object;
	}
	
	public void setObject(Object object) {
		this.object = object;
	}
	
	public String getTextMessage() {
		return textMessage;
	}
	
	public void setTextMessage(String textMessage) {
		this.textMessage = textMessage;
	}
}
