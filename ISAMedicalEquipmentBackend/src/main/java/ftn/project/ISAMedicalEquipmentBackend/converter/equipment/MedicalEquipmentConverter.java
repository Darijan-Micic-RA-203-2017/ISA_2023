package ftn.project.ISAMedicalEquipmentBackend.converter.equipment;

import java.util.ArrayList;
import java.util.List;

import ftn.project.ISAMedicalEquipmentBackend.converter.company.MedicalEquipmentCompanyConverter;
import ftn.project.ISAMedicalEquipmentBackend.domain.equipment.MedicalEquipment;
import ftn.project.ISAMedicalEquipmentBackend.dto.company.MedicalEquipmentCompanyDTO;
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
		MedicalEquipmentCompanyDTO company = 
				MedicalEquipmentCompanyConverter.convertToDTO(equipment.getCompany());
		
		MedicalEquipmentDTO dto = new MedicalEquipmentDTO(id, type, name, price, amount, company);
		
		return dto;
	}
}
