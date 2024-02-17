package ftn.project.ISAMedicalEquipmentBackend.service.impl.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import ftn.project.ISAMedicalEquipmentBackend.domain.order.DetailsOfEquipmentOrder;
import ftn.project.ISAMedicalEquipmentBackend.repository.order.DetailsOfEquipmentOrderRepository;
import ftn.project.ISAMedicalEquipmentBackend.service.order.DetailsOfEquipmentOrderService;

@Service
public class DetailsOfEquipmentOrderServiceImpl implements DetailsOfEquipmentOrderService {
	private final DetailsOfEquipmentOrderRepository detailsOfEquipmentOrderRepository;
	
	@Autowired
	public DetailsOfEquipmentOrderServiceImpl(
			DetailsOfEquipmentOrderRepository detailsOfEquipmentOrderRepository) {
		this.detailsOfEquipmentOrderRepository = detailsOfEquipmentOrderRepository;
	}
	
	@Override
	public DetailsOfEquipmentOrder findById(long id) throws AccessDeniedException {
		return detailsOfEquipmentOrderRepository.findById(id).orElse(null);
	}
	
	@Override
	public List<DetailsOfEquipmentOrder> findAll() {
		return detailsOfEquipmentOrderRepository.findAll();
	}
}
