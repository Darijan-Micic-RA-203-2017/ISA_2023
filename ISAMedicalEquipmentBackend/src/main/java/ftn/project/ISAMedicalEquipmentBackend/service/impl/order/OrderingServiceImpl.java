package ftn.project.ISAMedicalEquipmentBackend.service.impl.order;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.project.ISAMedicalEquipmentBackend.converter.equipment.MedicalEquipmentConverter;
import ftn.project.ISAMedicalEquipmentBackend.domain.equipment.MedicalEquipment;
import ftn.project.ISAMedicalEquipmentBackend.domain.order.EquipmentOrder;
import ftn.project.ISAMedicalEquipmentBackend.domain.term.ExchangeTerm;
import ftn.project.ISAMedicalEquipmentBackend.domain.user.CompanyAdministrator;
import ftn.project.ISAMedicalEquipmentBackend.dto.order.DetailsOfEquipmentOrderDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.order.OrderCreationDTO;
import ftn.project.ISAMedicalEquipmentBackend.exception.NotEnoughEquipmentForOrderException;
import ftn.project.ISAMedicalEquipmentBackend.service.company.MedicalEquipmentCompanyService;
import ftn.project.ISAMedicalEquipmentBackend.service.equipment.MedicalEquipmentService;
import ftn.project.ISAMedicalEquipmentBackend.service.order.EquipmentOrderService;
import ftn.project.ISAMedicalEquipmentBackend.service.order.OrderingService;
import ftn.project.ISAMedicalEquipmentBackend.service.term.ExchangeTermService;

@Service
public class OrderingServiceImpl implements OrderingService {
	private final MedicalEquipmentService medicalEquipmentService;
	private final EquipmentOrderService equipmentOrderService;
	private final ExchangeTermService exchangeTermService;
	private final MedicalEquipmentCompanyService medicalEquipmentCompanyService;
	
	@Autowired
	public OrderingServiceImpl(MedicalEquipmentService medicalEquipmentService, 
			EquipmentOrderService equipmentOrderService, ExchangeTermService exchangeTermService, 
			MedicalEquipmentCompanyService medicalEquipmentCompanyService) {
		this.medicalEquipmentService = medicalEquipmentService;
		this.equipmentOrderService = equipmentOrderService;
		this.exchangeTermService = exchangeTermService;
		this.medicalEquipmentCompanyService = medicalEquipmentCompanyService;
	}

	@Override
	public EquipmentOrder createOrder(OrderCreationDTO orderCreationDTO) 
			throws NotEnoughEquipmentForOrderException {
		List<MedicalEquipment> equipmentInOrder = new ArrayList<MedicalEquipment>();
		for (DetailsOfEquipmentOrderDTO dDTO: orderCreationDTO.getOrder().getDetails()) {
			MedicalEquipment equipment = medicalEquipmentService.findById(dDTO.getEquipmentId());
			int amount = dDTO.getAmount();
			
			if (!medicalEquipmentService.isThereEnoughEquipmentForOrder(equipment, amount)) {
				throw new NotEnoughEquipmentForOrderException();
			} else {
				equipment.setAmount(equipment.getAmount() - amount);
				medicalEquipmentService.save(MedicalEquipmentConverter.convertToDTO(equipment));
			}
			
			equipmentInOrder.add(equipment);
		}
		
		CompanyAdministrator availableCompanyAdministrator = 
				medicalEquipmentCompanyService.findAvailableCompanyAdministrator(
						orderCreationDTO.getExchangeTerm().getCompanyId());
		orderCreationDTO.getExchangeTerm().setAdministratorId(availableCompanyAdministrator.getId());
		
		ExchangeTerm exchangeTerm = 
				exchangeTermService.reserveTerm(orderCreationDTO.getExchangeTerm());
		orderCreationDTO.getOrder().setExchangeTermId(exchangeTerm.getId());
		
		EquipmentOrder order = 
				equipmentOrderService.create(orderCreationDTO.getOrder(), equipmentInOrder);
		
		return order;
	}
}
