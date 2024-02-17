package ftn.project.ISAMedicalEquipmentBackend.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.project.ISAMedicalEquipmentBackend.domain.order.DetailsOfEquipmentOrder;

@Repository
public interface DetailsOfEquipmentOrderRepository extends 
		JpaRepository<DetailsOfEquipmentOrder, Long> {}
