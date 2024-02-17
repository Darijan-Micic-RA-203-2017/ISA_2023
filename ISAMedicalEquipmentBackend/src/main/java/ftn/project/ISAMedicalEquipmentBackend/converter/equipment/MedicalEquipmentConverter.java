package ftn.project.ISAMedicalEquipmentBackend.converter.equipment;

import java.util.ArrayList;
import java.util.List;

import ftn.project.ISAMedicalEquipmentBackend.domain.equipment.MedicalEquipment;
import ftn.project.ISAMedicalEquipmentBackend.dto.equipment.MedicalEquipmentDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.equipment.TypeOfMedicalEquipmentDTO;

public class MedicalEquipmentConverter {
	public MedicalEquipmentConverter() {}
	
	public static List<MedicalEquipmentDTO> convertToDTOsList(List<MedicalEquipment> equipment) {
		if (equipment == null) {
			return null;
		}
		
		List<MedicalEquipmentDTO> dtosList = new ArrayList<MedicalEquipmentDTO>();
		for (MedicalEquipment equ: equipment) {
			dtosList.add(convertToDTO(equ));
		}
		
		return dtosList;
	}
	
	public static MedicalEquipmentDTO convertToDTO(MedicalEquipment equipment) {
		if (equipment == null) {
			return null;
		}
		
		long id = equipment.getId();
		TypeOfMedicalEquipmentDTO type = 
				TypeOfMedicalEquipmentConverter.convertToDTO(equipment.getType());
		String name = equipment.getName();
		double price = equipment.getPrice();
		int amount = equipment.getAmount();
		String companyName = equipment.getCompanyName();
		
		MedicalEquipmentDTO dto = new MedicalEquipmentDTO(id, type, name, price, amount, 
				companyName);
		
		return dto;
	}
}
