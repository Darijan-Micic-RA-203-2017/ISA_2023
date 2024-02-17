package ftn.project.ISAMedicalEquipmentBackend.service.impl.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import ftn.project.ISAMedicalEquipmentBackend.domain.order.EquipmentOrder;
import ftn.project.ISAMedicalEquipmentBackend.repository.order.EquipmentOrderRepository;
import ftn.project.ISAMedicalEquipmentBackend.service.order.EquipmentOrderService;

@Service
public class EquipmentOrderServiceImpl implements EquipmentOrderService {
	private final EquipmentOrderRepository equipmentOrderRepository;
	
	@Autowired
	public EquipmentOrderServiceImpl(EquipmentOrderRepository equipmentOrderRepository) {
		this.equipmentOrderRepository = equipmentOrderRepository;
	}
	
	@Override
	public EquipmentOrder findById(long id) throws AccessDeniedException {
		return equipmentOrderRepository.findById(id).orElse(null);
	}
	
	@Override
	public List<EquipmentOrder> findAll() {
		return equipmentOrderRepository.findAll();
	}
}
