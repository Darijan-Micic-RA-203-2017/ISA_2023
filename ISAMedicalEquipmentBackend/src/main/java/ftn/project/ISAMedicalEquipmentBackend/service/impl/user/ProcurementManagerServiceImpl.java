package ftn.project.ISAMedicalEquipmentBackend.service.impl.user;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ftn.project.ISAMedicalEquipmentBackend.domain.complaint.Complaint;
import ftn.project.ISAMedicalEquipmentBackend.domain.term.ExchangeTerm;
import ftn.project.ISAMedicalEquipmentBackend.domain.user.Gender;
import ftn.project.ISAMedicalEquipmentBackend.domain.user.LoyaltyProgram;
import ftn.project.ISAMedicalEquipmentBackend.domain.user.ProcurementManagerOfHospital;
import ftn.project.ISAMedicalEquipmentBackend.domain.user.UserRole;
import ftn.project.ISAMedicalEquipmentBackend.dto.ProcurementManagerRegistrationReqDTO;
import ftn.project.ISAMedicalEquipmentBackend.repository.user.ProcurementManagerRepository;
import ftn.project.ISAMedicalEquipmentBackend.service.user.LoyaltyProgramService;
import ftn.project.ISAMedicalEquipmentBackend.service.user.ProcurementManagerService;
import ftn.project.ISAMedicalEquipmentBackend.service.user.UserRoleService;

@Service
public class ProcurementManagerServiceImpl implements ProcurementManagerService {
	private final ProcurementManagerRepository procurementManagerRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	private final UserRoleService userRoleService;
	
	private final LoyaltyProgramService loyaltyProgramService;
	
	private final JavaMailSender javaMailSender;
	
	private final Environment environment;
	
	@Autowired
	public ProcurementManagerServiceImpl(ProcurementManagerRepository procurementManagerRepository, 
			PasswordEncoder passwordEncoder, UserRoleService userRoleService, 
			LoyaltyProgramService loyaltyProgramService, JavaMailSender javaMailSender, 
			Environment environment) {
		this.procurementManagerRepository = procurementManagerRepository;
		this.passwordEncoder = passwordEncoder;
		this.userRoleService = userRoleService;
		this.loyaltyProgramService = loyaltyProgramService;
		this.javaMailSender = javaMailSender;
		this.environment = environment;
	}
	
	@Override
	public ProcurementManagerOfHospital findById(long id) throws AccessDeniedException {
		return procurementManagerRepository.getById(id);
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
		return procurementManagerRepository.getAll();
	}
	
	@Override
	public ProcurementManagerOfHospital save(
			ProcurementManagerRegistrationReqDTO procurementManagerRegistrationReqDTO) {
		Set<UserRole> roles = new HashSet<UserRole>();
		roles.add(userRoleService.findByName("ROLE_PROCUREMENT_MANAGER"));
		
		boolean isEnabled = false;
		String userCode = generateUserCodeFrom(procurementManagerRegistrationReqDTO.getPassword());
		String emailAddress = procurementManagerRegistrationReqDTO.getEmailAddress();
		String username = procurementManagerRegistrationReqDTO.getUsername();
		String password = 
				passwordEncoder.encode(procurementManagerRegistrationReqDTO.getPassword());
		Timestamp lastPasswordResetDate = null;
		String firstName = procurementManagerRegistrationReqDTO.getFirstName();
		String lastName = procurementManagerRegistrationReqDTO.getLastName();
		String residence = procurementManagerRegistrationReqDTO.getResidence();
		String populatedPlace = procurementManagerRegistrationReqDTO.getPopulatedPlace();
		String country = procurementManagerRegistrationReqDTO.getCountry();
		String phoneNumber = procurementManagerRegistrationReqDTO.getPhoneNumber();
		String personalIdentityNumber =
				procurementManagerRegistrationReqDTO.getPersonalIdentityNumber();
		Gender gender = procurementManagerRegistrationReqDTO.getGender();
		String profession = procurementManagerRegistrationReqDTO.getProfession();
		String companyName = procurementManagerRegistrationReqDTO.getCompanyName();
		
		int penaltyPoints = 0;
		int loyaltyPoints = 0;
		LoyaltyProgram loyaltyProgram = loyaltyProgramService.findById(1);
		Set<ExchangeTerm> exchangeTerms = null;
		Set<Complaint> complaints = null;
		
		ProcurementManagerOfHospital newProcurementManager = new ProcurementManagerOfHospital(0, 
				roles, isEnabled, userCode, emailAddress, username, password, 
				lastPasswordResetDate, firstName, lastName, residence, populatedPlace, country, 
				phoneNumber, personalIdentityNumber, gender, profession, companyName, 
				penaltyPoints, loyaltyPoints, loyaltyProgram, exchangeTerms, complaints);
		
		return procurementManagerRepository.save(newProcurementManager);
	}
	
	@Override
	public String generateUserCodeFrom(String password) {
		MessageDigest messageDigest = null;
		
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException nSAE) {
			nSAE.printStackTrace();
		}
		
		messageDigest.update(password.getBytes(StandardCharsets.UTF_8));
		byte[] digestion = messageDigest.digest();
		
		String generatedUserCode = String.format("%064x", new BigInteger(1, digestion));
		
		return generatedUserCode;
	}
	
	@Async
	@Override
	public void sendActivationEmail(ProcurementManagerOfHospital newProcurementManagerOfHospital) 
			throws MailException {
		SimpleMailMessage emailMessageWithAccountActivationLink = new SimpleMailMessage();
		
		emailMessageWithAccountActivationLink.setTo(
				newProcurementManagerOfHospital.getEmailAddress());
		emailMessageWithAccountActivationLink.setFrom(
				environment.getProperty("spring.mail.username"));
		emailMessageWithAccountActivationLink.setSubject(
				"ISAMedicalEquipment - poveznica za aktivaciju naloga");
		
		StringBuilder emailMessageTextBuilder = new StringBuilder("Poštovani/a ");
		emailMessageTextBuilder.append(newProcurementManagerOfHospital.getFirstName()).append(",\n\n");
		emailMessageTextBuilder.append("Hvala Vam za registraciju na našoj aplikaciji. ");
		emailMessageTextBuilder.append("Da bi aktivirali svoj nalog, molimo Vas da kliknete ");
		emailMessageTextBuilder.append("na sledeću poveznicu:\n");
		emailMessageTextBuilder.append("http://localhost:4200/activate-account/");
		emailMessageTextBuilder.append(newProcurementManagerOfHospital.getUserCode()).append("\n\n");
		emailMessageTextBuilder.append("Srdačan pozdrav!").append("\n");
		
		emailMessageWithAccountActivationLink.setText(emailMessageTextBuilder.toString());
		
		javaMailSender.send(emailMessageWithAccountActivationLink);
	}
	
	@Override
	public void activateAccount(String userCodeOfNewRegisteredUser) {
		ProcurementManagerOfHospital procurementManager = 
				procurementManagerRepository.findByUserCode(userCodeOfNewRegisteredUser);
		if (procurementManager == null) {
			return;
		}
		
		procurementManager.setEnabled(true);
		
		procurementManagerRepository.save(procurementManager);
	}
}
