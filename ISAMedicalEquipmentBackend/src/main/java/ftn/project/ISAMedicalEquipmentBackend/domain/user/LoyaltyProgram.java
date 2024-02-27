package ftn.project.ISAMedicalEquipmentBackend.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "loyalty_programs")
public class LoyaltyProgram {
	@Id
	@SequenceGenerator(name = "loyalty_program_id_generator", 
		sequenceName = "loyalty_program_ids_sequence", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loyalty_program_id_generator")
	@Column(name = "id", nullable = false, updatable = false, columnDefinition = "bigserial")
	private long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "necessary_points", nullable = false)
	private int necessaryPoints;
	
	@Column(name = "points_gained_for_each_successful_exchange", nullable = false)
	private int pointsGainedForEachSuccessfulExchange;
	
	public LoyaltyProgram() {}
	
	public LoyaltyProgram(long id, String name, int necessaryPoints, 
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
	
	@Override
	public int hashCode() {
		final int prime = 37;
		int result = 1;
		
		result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
		
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof LoyaltyProgram)) {
			return false;
		}
		
		LoyaltyProgram other = (LoyaltyProgram) obj;
		
		if (getId() != other.getId()) {
			return false;
		}
		
		if (getName() == null) {
			if (other.getName() != null) {
				return false;
			}
		} else if (!getName().equals(other.getName())) {
			return false;
		}
		
		return true;
	}
}
