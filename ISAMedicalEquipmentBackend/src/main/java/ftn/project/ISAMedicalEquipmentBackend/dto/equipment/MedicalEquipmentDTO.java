package ftn.project.ISAMedicalEquipmentBackend.dto.equipment;

import ftn.project.ISAMedicalEquipmentBackend.dto.company.MedicalEquipmentCompanyDTO;

public class MedicalEquipmentDTO {
	private long id;
	private TypeOfMedicalEquipmentDTO type;
	private String name;
	private double price;
	private int amount;
	private MedicalEquipmentCompanyDTO company;
	
	public MedicalEquipmentDTO() {}
	
	public MedicalEquipmentDTO(long id, TypeOfMedicalEquipmentDTO type, String name, double price, 
			int amount, MedicalEquipmentCompanyDTO company) {
		this.id = id;
		this.type = type;
		this.name = name;
		this.price = price;
		this.amount = amount;
		this.company = company;
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
	
	public MedicalEquipmentCompanyDTO getCompany() {
		return company;
	}
	
	public void setCompany(MedicalEquipmentCompanyDTO company) {
		this.company = company;
	}
}
