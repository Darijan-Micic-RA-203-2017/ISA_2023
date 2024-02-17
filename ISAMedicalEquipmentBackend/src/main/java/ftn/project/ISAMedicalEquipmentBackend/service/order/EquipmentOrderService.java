package ftn.project.ISAMedicalEquipmentBackend.service.order;

import java.util.List;

import org.springframework.security.access.AccessDeniedException;

import ftn.project.ISAMedicalEquipmentBackend.domain.order.EquipmentOrder;

public interface EquipmentOrderService {
	EquipmentOrder findById(long id) throws AccessDeniedException;
	List<EquipmentOrder> findAll();
}
