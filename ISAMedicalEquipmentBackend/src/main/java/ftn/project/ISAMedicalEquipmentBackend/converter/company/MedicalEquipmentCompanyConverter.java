package ftn.project.ISAMedicalEquipmentBackend.converter.company;

import java.util.ArrayList;
import java.util.List;

import ftn.project.ISAMedicalEquipmentBackend.domain.company.MedicalEquipmentCompany;
import ftn.project.ISAMedicalEquipmentBackend.dto.company.MedicalEquipmentCompanyDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.company.WorkTimeDTO;

public class MedicalEquipmentCompanyConverter {
	public MedicalEquipmentCompanyConverter() {}
	
	public static List<MedicalEquipmentCompanyDTO> convertToDTOsList(
			Iterable<MedicalEquipmentCompany> companies) {
		if (companies == null) {
			return null;
		}
		
		List<MedicalEquipmentCompanyDTO> dtosList = new ArrayList<MedicalEquipmentCompanyDTO>();
		for (MedicalEquipmentCompany c: companies) {
			dtosList.add(convertToDTO(c));
		}
		
		return dtosList;
	}
	
	public static MedicalEquipmentCompanyDTO convertToDTO(MedicalEquipmentCompany company) {
		if (company == null) {
			return null;
		}
		
		long id = company.getId();
		String name = company.getName();
		double latitude = company.getLatitude();
		double longitude = company.getLongitude();
		String streetAndNumber = company.getStreetAndNumber();
		String populatedPlace = company.getPopulatedPlace();
		String country = company.getCountry();
		String description = company.getDescription();
		double averageGrade = company.getAverageGrade();
		WorkTimeDTO workTime = WorkTimeConverter.convertToDTO(company.getWorkTime());
		
		MedicalEquipmentCompanyDTO dto = new MedicalEquipmentCompanyDTO(id, name, latitude, 
				longitude, streetAndNumber, populatedPlace, country, description, averageGrade, 
				workTime);
		
		return dto;
	}
}
