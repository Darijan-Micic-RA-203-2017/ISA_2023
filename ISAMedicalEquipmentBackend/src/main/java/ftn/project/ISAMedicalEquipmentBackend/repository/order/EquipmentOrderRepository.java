package ftn.project.ISAMedicalEquipmentBackend.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.project.ISAMedicalEquipmentBackend.domain.order.EquipmentOrder;

@Repository
public interface EquipmentOrderRepository extends JpaRepository<EquipmentOrder, Long> {}
