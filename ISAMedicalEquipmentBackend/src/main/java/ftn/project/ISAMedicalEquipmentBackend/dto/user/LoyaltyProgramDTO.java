package ftn.project.ISAMedicalEquipmentBackend.dto.user;

public class LoyaltyProgramDTO {
	private long id;
	private String name;
	private int necessaryPoints;
	private int pointsGainedForEachSuccessfulExchange;
	
	public LoyaltyProgramDTO() {}
	
	public LoyaltyProgramDTO(long id, String name, int necessaryPoints, 
			int pointsGainedForEachSuccessfulExchange) {
		this.id = id;
		this.name = name;
		this.necessaryPoints = necessaryPoints;
		this.pointsGainedForEachSuccessfulExchange = pointsGainedForEachSuccessfulExchange;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getNecessaryPoints() {
		return necessaryPoints;
	}
	
	public void setNecessaryPoints(int necessaryPoints) {
		this.necessaryPoints = necessaryPoints;
	}
	
	public int getPointsGainedForEachSuccessfulExchange() {
		return pointsGainedForEachSuccessfulExchange;
	}
	
	public void setPointsGainedForEachSuccessfulExchange(int pointsGainedForEachSuccessfulExchange) {
		this.pointsGainedForEachSuccessfulExchange = pointsGainedForEachSuccessfulExchange;
	}
}
