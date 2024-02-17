package ftn.project.ISAMedicalEquipmentBackend.dto.equipment;

public class MedicalEquipmentDTO {
	private long id;
	private TypeOfMedicalEquipmentDTO type;
	private String name;
	private double price;
	private int amount;
	private String companyName;
	
	public MedicalEquipmentDTO() {}
	
	public MedicalEquipmentDTO(long id, TypeOfMedicalEquipmentDTO type, String name, double price, 
			int amount, String companyName) {
		this.id = id;
		this.type = type;
		this.name = name;
		this.price = price;
		this.amount = amount;
		this.companyName = companyName;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public TypeOfMedicalEquipmentDTO getType() {
		return type;
	}
	
	public void setType(TypeOfMedicalEquipmentDTO type) {
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}
