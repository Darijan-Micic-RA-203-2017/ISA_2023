package ftn.project.ISAMedicalEquipmentBackend.service.impl.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import ftn.project.ISAMedicalEquipmentBackend.domain.user.LoyaltyProgram;
import ftn.project.ISAMedicalEquipmentBackend.repository.user.LoyaltyProgramRepository;
import ftn.project.ISAMedicalEquipmentBackend.service.user.LoyaltyProgramService;

@Service
public class LoyaltyProgramServiceImpl implements LoyaltyProgramService {
	private final LoyaltyProgramRepository loyaltyProgramRepository;
	
	@Autowired
	public LoyaltyProgramServiceImpl(LoyaltyProgramRepository companyAdministratorRepository) {
		this.loyaltyProgramRepository = companyAdministratorRepository;
	}
	
	@Override
	public LoyaltyProgram findById(long id) throws AccessDeniedException {
		return loyaltyProgramRepository.findById(id).orElse(null);
	}
	
	@Override
	public LoyaltyProgram findByName(String name) {
		return loyaltyProgramRepository.findByName(name);
	}
	
	@Override
	public LoyaltyProgram findByNecessaryPoints(int necessaryPoints) {
		return loyaltyProgramRepository.findByNecessaryPoints(necessaryPoints);
	}
	
	@Override
	public LoyaltyProgram findByPointsGainedForEachSuccessfulExchange(
			int pointsGainedForEachSuccessfulExchange) {
		return loyaltyProgramRepository.findByPointsGainedForEachSuccessfulExchange(
				pointsGainedForEachSuccessfulExchange);
	}
	
	@Override
	public List<LoyaltyProgram> findAll() throws AccessDeniedException {
		return loyaltyProgramRepository.findAll();
	}
}
