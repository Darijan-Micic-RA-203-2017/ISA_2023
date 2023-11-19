package ftn.project.ISAMedicalEquipmentBackend.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.project.ISAMedicalEquipmentBackend.domain.user.ProcurementManagerOfHospital;
import ftn.project.ISAMedicalEquipmentBackend.domain.user.User;
import ftn.project.ISAMedicalEquipmentBackend.dto.ProcurementManagerRegistrationReqDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.SimpleTextResponseDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.UserCodeWrapperDTO;
import ftn.project.ISAMedicalEquipmentBackend.service.user.UserService;
import ftn.project.ISAMedicalEquipmentBackend.validation.ValidationPerformer;

@RestController
@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping(path = "/register-as-a-procurement-manager", 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SimpleTextResponseDTO> registerAsAProcurementManager(
			@RequestBody ProcurementManagerRegistrationReqDTO procurementManagerRegistrationReqDTO) {
		String validationMessages = 
				ValidationPerformer.getValidationMessages(procurementManagerRegistrationReqDTO);
		if (validationMessages != null) {
			return new ResponseEntity<SimpleTextResponseDTO>(
					new SimpleTextResponseDTO(validationMessages), 
					HttpStatus.BAD_REQUEST);
		}
		
		User existingUserWithSameUsername = userService.findByUsername(
				procurementManagerRegistrationReqDTO.getUsername());
		
		if (existingUserWithSameUsername != null) {
			return new ResponseEntity<SimpleTextResponseDTO>(
					new SimpleTextResponseDTO("User with the same username already exists!"), 
					HttpStatus.BAD_REQUEST);
		}
		
		User existingUserWithSameEmailAddress = userService.findByEmailAddress(
				procurementManagerRegistrationReqDTO.getEmailAddress());
		
		if (existingUserWithSameEmailAddress != null) {
			return new ResponseEntity<SimpleTextResponseDTO>(
					new SimpleTextResponseDTO("Email address is associated with existing user!"), 
					HttpStatus.BAD_REQUEST);
		}
		
		ProcurementManagerOfHospital newProcurementManager = userService
				.getProcurementManagerService().save(procurementManagerRegistrationReqDTO);
		try {
			userService.getProcurementManagerService().sendActivationEmail(newProcurementManager);
		} catch (MailException mE) {
			System.out.println("\nEmail message was not sent!\n");
			mE.printStackTrace();
			
			return new ResponseEntity<SimpleTextResponseDTO>(
					new SimpleTextResponseDTO("Email message with activation link was not sent!"), 
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		System.out.println("\nEmail message was successfully sent to new procurement manager " + 
				"with username \"" + newProcurementManager.getUsername() + "\".\n");
		
		return new ResponseEntity<SimpleTextResponseDTO>(
				new SimpleTextResponseDTO("New unenabled user has been successfully created."), 
				HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/activate-account", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SimpleTextResponseDTO> activateAccount(
			@RequestBody UserCodeWrapperDTO codeOfNewRegisteredUserWrapper) {
		String validationMessages = 
				ValidationPerformer.getValidationMessages(codeOfNewRegisteredUserWrapper);
		if (validationMessages != null) {
			return new ResponseEntity<SimpleTextResponseDTO>(
					new SimpleTextResponseDTO(validationMessages), 
					HttpStatus.BAD_REQUEST);
		}
		
		ProcurementManagerOfHospital procurementManager = userService
				.getProcurementManagerService().findByUserCode(
						codeOfNewRegisteredUserWrapper.getUserCode());
		
		if (procurementManager == null) {
			return new ResponseEntity<SimpleTextResponseDTO>(
					new SimpleTextResponseDTO("No procurement manager with such user code has been found!"), 
					HttpStatus.BAD_REQUEST);
		}
		
		if (procurementManager.isEnabled()) {
			return new ResponseEntity<SimpleTextResponseDTO>(
					new SimpleTextResponseDTO("User's account is already activated!"), 
					HttpStatus.BAD_REQUEST);
		}
		
		userService.getProcurementManagerService().activateAccount(
				codeOfNewRegisteredUserWrapper.getUserCode());
		
		System.out.println("\nAccount of user with username \"" + procurementManager.getUsername() + 
				"\" has been successfully activated.\n");
		
		return new ResponseEntity<SimpleTextResponseDTO>(
				new SimpleTextResponseDTO("Account of user with username \"" + 
						procurementManager.getUsername() + "\" has been successfully activated."), 
				HttpStatus.OK);
	}
}
