package ftn.project.ISAMedicalEquipmentBackend.service.impl.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import ftn.project.ISAMedicalEquipmentBackend.domain.user.ProcurementManagerOfHospital;
import ftn.project.ISAMedicalEquipmentBackend.repository.user.ProcurementManagerRepository;
import ftn.project.ISAMedicalEquipmentBackend.service.user.ProcurementManagerService;

@Service
public class ProcurementManagerServiceImpl implements ProcurementManagerService {
	private final ProcurementManagerRepository procurementManagerRepository;
	
	@Autowired
	public ProcurementManagerServiceImpl(
			ProcurementManagerRepository procurementManagerRepository) {
		this.procurementManagerRepository = procurementManagerRepository;
	}
	
	@Override
	public ProcurementManagerOfHospital findById(long id) throws AccessDeniedException {
		return procurementManagerRepository.findById(id).orElse(null);
	}
	
	@Override
	public ProcurementManagerOfHospital findByUserCode(String userCode) {
		return procurementManagerRepository.findByUserCode(userCode);
	}
	
	@Override
	public ProcurementManagerOfHospital findByEmailAddress(String emailAddress) {
		return procurementManagerRepository.findByEmailAddress(emailAddress);
	}
	
	@Override
	public ProcurementManagerOfHospital findByUsername(String username) {
		return procurementManagerRepository.findByUsername(username);
	}
	
	@Override
	public List<ProcurementManagerOfHospital> findAll() throws AccessDeniedException {
		return procurementManagerRepository.findAll();
	}
}
