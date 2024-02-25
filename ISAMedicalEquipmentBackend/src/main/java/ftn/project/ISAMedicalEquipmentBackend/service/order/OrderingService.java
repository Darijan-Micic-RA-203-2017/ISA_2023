package ftn.project.ISAMedicalEquipmentBackend.service.order;

import ftn.project.ISAMedicalEquipmentBackend.domain.order.EquipmentOrder;
import ftn.project.ISAMedicalEquipmentBackend.dto.order.OrderCreationDTO;
import ftn.project.ISAMedicalEquipmentBackend.exception.NotEnoughEquipmentForOrderException;

public interface OrderingService {
	EquipmentOrder createOrder(OrderCreationDTO orderCreationDTO) throws NotEnoughEquipmentForOrderException;
}
