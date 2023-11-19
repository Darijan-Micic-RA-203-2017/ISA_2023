package ftn.project.ISAMedicalEquipmentBackend.dto;

public class SearchCriterionDTO {
	private String criterion;
	
	public SearchCriterionDTO() {}
	
	public SearchCriterionDTO(String criterion) {
		this.criterion = criterion;
	}
	
	public String getCriterion() {
		return criterion;
	}
	
	public void setCriterion(String criterion) {
		this.criterion = criterion;
	}
}
