package ftn.project.ISAMedicalEquipmentBackend.service.impl.order;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import ftn.project.ISAMedicalEquipmentBackend.domain.equipment.MedicalEquipment;
import ftn.project.ISAMedicalEquipmentBackend.domain.order.DetailsOfEquipmentOrder;
import ftn.project.ISAMedicalEquipmentBackend.domain.order.EquipmentOrder;
import ftn.project.ISAMedicalEquipmentBackend.domain.term.ExchangeTerm;
import ftn.project.ISAMedicalEquipmentBackend.domain.user.ProcurementManager;
import ftn.project.ISAMedicalEquipmentBackend.dto.order.EquipmentOrderDTO;
import ftn.project.ISAMedicalEquipmentBackend.repository.order.EquipmentOrderRepository;
import ftn.project.ISAMedicalEquipmentBackend.service.order.EquipmentOrderService;
import ftn.project.ISAMedicalEquipmentBackend.service.term.ExchangeTermService;
import ftn.project.ISAMedicalEquipmentBackend.service.user.ProcurementManagerService;

@Service
public class EquipmentOrderServiceImpl implements EquipmentOrderService {
	private final EquipmentOrderRepository equipmentOrderRepository;
	private final ExchangeTermService exchangeTermService;
	private final ProcurementManagerService procurementManagerService;
	
	@Autowired
	public EquipmentOrderServiceImpl(EquipmentOrderRepository equipmentOrderRepository, 
			ExchangeTermService exchangeTermService, 
			ProcurementManagerService procurementManagerService) {
		this.equipmentOrderRepository = equipmentOrderRepository;
		this.exchangeTermService = exchangeTermService;
		this.procurementManagerService = procurementManagerService;
	}
	
	@Override
	public EquipmentOrder findById(long id) throws AccessDeniedException {
		return equipmentOrderRepository.getById(id);
	}
	
	@Override
	public EquipmentOrder findByExchangeTermId(long exchangeTermId) {
		return equipmentOrderRepository.findByExchangeTermId(exchangeTermId);
	}
	
	@Override
	public List<EquipmentOrder> findAll() {
		return equipmentOrderRepository.getAll();
	}
	
	@Override
	public EquipmentOrder create(EquipmentOrderDTO equipmentOrderDTO, 
			List<MedicalEquipment> equipmentInOrder) {
		ExchangeTerm exchangeTerm = 
				exchangeTermService.findById(equipmentOrderDTO.getExchangeTermId());
		ProcurementManager procurementManager = 
				procurementManagerService.findById(equipmentOrderDTO.getProcurementManagerId());
		Set<DetailsOfEquipmentOrder> details = new TreeSet<DetailsOfEquipmentOrder>();
		for (int i = 0; i < equipmentInOrder.size(); i++) {
			int amount = equipmentOrderDTO.getDetails().get(i).getAmount();
			double subtotalPrice = equipmentOrderDTO.getDetails().get(i).getSubtotalPrice();
			
			DetailsOfEquipmentOrder d = new DetailsOfEquipmentOrder(0, null, equipmentInOrder.get(i), 
					amount, subtotalPrice);
			details.add(d);
		}
		double totalPrice = equipmentOrderDTO.getTotalPrice();
		
		EquipmentOrder equipmentOrder = new EquipmentOrder(0, exchangeTerm, procurementManager, 
				details, totalPrice);
		for (DetailsOfEquipmentOrder d: equipmentOrder.getDetails()) {
			d.setOrder(equipmentOrder);
		}
		
		return equipmentOrderRepository.saveAndFlush(equipmentOrder);
	}
	
	@Override
	public EquipmentOrder deleteByExchangeTermId(long exchangeTermId) {
		EquipmentOrder equipmentOrderToBeDeleted = findByExchangeTermId(exchangeTermId);
		if (equipmentOrderToBeDeleted != null) {
			equipmentOrderRepository.deleteById(equipmentOrderToBeDeleted.getId());
		}
		
		return equipmentOrderToBeDeleted;
	}
}
