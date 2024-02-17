package ftn.project.ISAMedicalEquipmentBackend.service.order;

import java.util.List;

import org.springframework.security.access.AccessDeniedException;

import ftn.project.ISAMedicalEquipmentBackend.domain.order.DetailsOfEquipmentOrder;

public interface DetailsOfEquipmentOrderService {
	DetailsOfEquipmentOrder findById(long id) throws AccessDeniedException;
	List<DetailsOfEquipmentOrder> findAll();
}
