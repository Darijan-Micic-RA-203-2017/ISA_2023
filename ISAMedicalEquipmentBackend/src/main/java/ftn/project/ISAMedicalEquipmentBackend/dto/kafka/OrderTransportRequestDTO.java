package ftn.project.ISAMedicalEquipmentBackend.dto.kafka;

public class OrderTransportRequestDTO {
	private long orderId;
	private String hospitalName;
	private LocationDTO hospitalLocation;
	private LocationDTO companyLocation;
	private long procurementManagerId;
	private long companyAdministratorId;
	
	public OrderTransportRequestDTO() {}
	
	public OrderTransportRequestDTO(long orderId, String hospitalName, LocationDTO hospitalLocation, 
			LocationDTO companyLocation, long procurementManagerId, long companyAdministratorId) {
		this.orderId = orderId;
		this.hospitalName = hospitalName;
		this.hospitalLocation = hospitalLocation;
		this.companyLocation = companyLocation;
		this.procurementManagerId = procurementManagerId;
		this.companyAdministratorId = companyAdministratorId;
	}
	
	public long getOrderId() {
		return orderId;
	}
	
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	
	public String getHospitalName() {
		return hospitalName;
	}
	
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	
	public LocationDTO getHospitalLocation() {
		return hospitalLocation;
	}
	
	public void setHospitalLocation(LocationDTO hospitalLocation) {
		this.hospitalLocation = hospitalLocation;
	}
	
	public LocationDTO getCompanyLocation() {
		return companyLocation;
	}
	
	public void setCompanyLocation(LocationDTO companyLocation) {
		this.companyLocation = companyLocation;
	}
	
	public long getProcurementManagerId() {
		return procurementManagerId;
	}
	
	public void setProcurementManagerId(long procurementManagerId) {
		this.procurementManagerId = procurementManagerId;
	}
	
	public long getCompanyAdministratorId() {
		return companyAdministratorId;
	}
	
	public void setCompanyAdministratorId(long companyAdministratorId) {
		this.companyAdministratorId = companyAdministratorId;
	}
}
