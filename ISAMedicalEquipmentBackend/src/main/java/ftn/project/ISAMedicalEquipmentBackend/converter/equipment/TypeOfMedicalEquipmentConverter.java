package ftn.project.ISAMedicalEquipmentBackend.converter.equipment;

import java.util.ArrayList;
import java.util.List;

import ftn.project.ISAMedicalEquipmentBackend.domain.equipment.TypeOfMedicalEquipment;
import ftn.project.ISAMedicalEquipmentBackend.dto.equipment.TypeOfMedicalEquipmentDTO;

public class TypeOfMedicalEquipmentConverter {
	public TypeOfMedicalEquipmentConverter() {}
	
	public static List<TypeOfMedicalEquipmentDTO> convertToDTOsList(
			Iterable<TypeOfMedicalEquipment> typesOfMedicalEquipment) {
		if (typesOfMedicalEquipment == null) {
			return null;
		}
		
		List<TypeOfMedicalEquipmentDTO> dtosList = new ArrayList<TypeOfMedicalEquipmentDTO>();
		for (TypeOfMedicalEquipment tME: typesOfMedicalEquipment) {
			dtosList.add(convertToDTO(tME));
		}
		
		return dtosList;
	}
	
	public static TypeOfMedicalEquipmentDTO convertToDTO(
			TypeOfMedicalEquipment typeOfMedicalEquipment) {
		if (typeOfMedicalEquipment == null) {
			return null;
		}
		
		long id = typeOfMedicalEquipment.getId();
		String name = typeOfMedicalEquipment.getName();
		
		TypeOfMedicalEquipmentDTO dto = new TypeOfMedicalEquipmentDTO(id, name);
		
		return dto;
	}
}
