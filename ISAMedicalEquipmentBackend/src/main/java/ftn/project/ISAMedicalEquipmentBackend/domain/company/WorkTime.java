package ftn.project.ISAMedicalEquipmentBackend.domain.company;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "work_times")
public class WorkTime {
	@Id
	@SequenceGenerator(name = "work_time_id_generator", sequenceName = "work_time_ids_sequence", 
		initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "work_time_id_generator")
	@Column(name = "id", nullable = false, updatable = false, columnDefinition = "bigserial")
	private long id;
	
	@Column(name = "on_mondays_through_fridays", nullable = false)
	private String onMondaysThroughFridays;
	
	@Column(name = "on_saturdays")
	private String onSaturdays;
	
	@Column(name = "on_sundays")
	private String onSundays;
	
	public WorkTime() {}
	
	public WorkTime(long id, String onMondaysThroughFridays, String onSaturdays, String onSundays) {
		this.id = id;
		this.onMondaysThroughFridays = onMondaysThroughFridays;
		this.onSaturdays = onSaturdays;
		this.onSundays = onSundays;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getOnMondaysThroughFridays() {
		return onMondaysThroughFridays;
	}
	
	public void setOnMondaysThroughFridays(String onMondaysThroughFridays) {
		this.onMondaysThroughFridays = onMondaysThroughFridays;
	}
	
	public String getOnSaturdays() {
		return onSaturdays;
	}
	
	public void setOnSaturdays(String onSaturdays) {
		this.onSaturdays = onSaturdays;
	}
	
	public String getOnSundays() {
		return onSundays;
	}
	
	public void setOnSundays(String onSundays) {
		this.onSundays = onSundays;
	}
	
	@Override
	public int hashCode() {
		final int prime = 73;
		int result = 1;
		
		result = prime * result + ((getOnMondaysThroughFridays() == null) ? 
				0 : getOnMondaysThroughFridays().hashCode());
		result = prime * result + ((getOnSaturdays() == null) ? 0 : getOnSaturdays().hashCode());
		result = prime * result + ((getOnSundays() == null) ? 0 : getOnSundays().hashCode());
		
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof WorkTime)) {
			return false;
		}
		
		WorkTime other = (WorkTime) obj;
		
		if (getId() != other.getId()) {
			return false;
		}
		
		if (getOnMondaysThroughFridays() == null) {
			if (other.getOnMondaysThroughFridays() != null) {
				return false;
			}
		} else if (!getOnMondaysThroughFridays().equals(other.getOnMondaysThroughFridays())) {
			return false;
		}
		
		if (getOnSaturdays() == null) {
			if (other.getOnSaturdays() != null) {
				return false;
			}
		} else if (!getOnSaturdays().equals(other.getOnSaturdays())) {
			return false;
		}
		
		if (getOnSundays() == null) {
			if (other.getOnSundays() != null) {
				return false;
			}
		} else if (!getOnSundays().equals(other.getOnSundays())) {
			return false;
		}
		
		return true;
	}
}
