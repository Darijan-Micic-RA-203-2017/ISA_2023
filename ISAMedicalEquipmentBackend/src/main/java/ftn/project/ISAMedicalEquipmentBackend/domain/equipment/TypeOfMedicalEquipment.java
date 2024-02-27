package ftn.project.ISAMedicalEquipmentBackend.domain.equipment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "types_of_medical_equipment")
public class TypeOfMedicalEquipment {
	@Id
	@SequenceGenerator(name = "type_of_medical_equipment_id_generator", 
		sequenceName = "type_of_medical_equipment_ids_sequence", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, 
		generator = "type_of_medical_equipment_id_generator")
	@Column(name = "id", nullable = false, updatable = false, columnDefinition = "bigserial")
	private long id;
	
	@Column(name = "name", nullable = false, columnDefinition = "text")
	private String name;
	
	public TypeOfMedicalEquipment() {}
	
	public TypeOfMedicalEquipment(long id, String name) {
		this.id = id;
		this.name = name;
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
	
	@Override
	public int hashCode() {
		final int prime = 53;
		
		int result = 1;
		result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
		
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof TypeOfMedicalEquipment)) {
			return false;
		}
		
		TypeOfMedicalEquipment other = (TypeOfMedicalEquipment) obj;
		
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
