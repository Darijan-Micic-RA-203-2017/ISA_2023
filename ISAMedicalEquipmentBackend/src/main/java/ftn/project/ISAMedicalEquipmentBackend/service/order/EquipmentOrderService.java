package ftn.project.ISAMedicalEquipmentBackend.service.order;

import java.util.List;

import org.springframework.security.access.AccessDeniedException;

import ftn.project.ISAMedicalEquipmentBackend.domain.equipment.MedicalEquipment;
import ftn.project.ISAMedicalEquipmentBackend.domain.order.EquipmentOrder;
import ftn.project.ISAMedicalEquipmentBackend.domain.term.ExchangeTerm;
import ftn.project.ISAMedicalEquipmentBackend.dto.order.EquipmentOrderDTO;
import ftn.project.ISAMedicalEquipmentBackend.exception.PastTermDeletionException;

public interface EquipmentOrderService {
	EquipmentOrder findById(long id) throws AccessDeniedException;
	EquipmentOrder findByExchangeTermId(long exchangeTermId);
	List<EquipmentOrder> findAll();
	EquipmentOrder create(EquipmentOrderDTO equipmentOrderDTO, 
			List<MedicalEquipment> equipmentInOrder);
	EquipmentOrder deleteByExchangeTermId(long exchangeTermId) throws PastTermDeletionException;
	boolean didTermAlreadyStart(ExchangeTerm term);
}
