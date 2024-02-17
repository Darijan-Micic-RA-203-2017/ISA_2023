package ftn.project.ISAMedicalEquipmentBackend.repository.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ftn.project.ISAMedicalEquipmentBackend.domain.order.EquipmentOrder;

@Repository
public interface EquipmentOrderRepository extends JpaRepository<EquipmentOrder, Long> {
	@Query(value = "SELECT o FROM EquipmentOrder o JOIN FETCH o.details d")
	List<EquipmentOrder> getAll();
	
	@Query(value = "SELECT o FROM EquipmentOrder o JOIN FETCH o.details d WHERE o.id = ?1")
	EquipmentOrder getById(long id);
}
