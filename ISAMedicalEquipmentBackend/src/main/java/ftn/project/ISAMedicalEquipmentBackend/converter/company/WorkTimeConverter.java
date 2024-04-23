package ftn.project.ISAMedicalEquipmentBackend.converter.company;

import java.util.ArrayList;
import java.util.List;

import ftn.project.ISAMedicalEquipmentBackend.domain.company.WorkTime;
import ftn.project.ISAMedicalEquipmentBackend.dto.company.WorkTimeDTO;

public class WorkTimeConverter {
	public WorkTimeConverter() {}
	
	public static List<WorkTimeDTO> convertToDTOsList(Iterable<WorkTime> workTimes) {
		if (workTimes == null) {
			return null;
		}
		
		List<WorkTimeDTO> dtosList = new ArrayList<WorkTimeDTO>();
		for (WorkTime wT: workTimes) {
			dtosList.add(convertToDTO(wT));
		}
		
		return dtosList;
	}
	
	public static WorkTimeDTO convertToDTO(WorkTime workTime) {
		if (workTime == null) {
			return null;
		}
		
		long id = workTime.getId();
		String onMondaysThroughFridays = workTime.getOnMondaysThroughFridays();
		String onSaturdays = workTime.getOnSaturdays();
		String onSundays = workTime.getOnSundays();
		
		WorkTimeDTO dto = new WorkTimeDTO(id, onMondaysThroughFridays, onSaturdays, onSundays);
		
		return dto;
	}
}
