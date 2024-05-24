package ftn.project.ISAMedicalEquipmentBackend.controller.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.project.ISAMedicalEquipmentBackend.converter.user.CompanyAdministratorConverter;
import ftn.project.ISAMedicalEquipmentBackend.converter.user.ProcurementManagerConverter;
import ftn.project.ISAMedicalEquipmentBackend.converter.user.SystemAdministratorConverter;
import ftn.project.ISAMedicalEquipmentBackend.converter.user.UserConverter;
import ftn.project.ISAMedicalEquipmentBackend.domain.user.CompanyAdministrator;
import ftn.project.ISAMedicalEquipmentBackend.domain.user.ProcurementManager;
import ftn.project.ISAMedicalEquipmentBackend.domain.user.SystemAdministrator;
import ftn.project.ISAMedicalEquipmentBackend.domain.user.User;
import ftn.project.ISAMedicalEquipmentBackend.dto.ObjectAndTextResponseDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.ProcurementManagerRegistrationReqDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.SimpleTextResponseDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.UserCodeWrapperDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.user.CompanyAdministratorDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.user.ProcurementManagerDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.user.SystemAdministratorDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.user.UserDTO;
import ftn.project.ISAMedicalEquipmentBackend.exception.ChangeOfEmailAddressException;
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
	
	@GetMapping(path = "")
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> allUsers = userService.findAll();
		System.out.println("\nTotal number of users: " + allUsers.size());
		List<User> aU = new ArrayList<User>();
		for (User u: allUsers) {
			aU.add(u);
		}
		
		return new ResponseEntity<List<UserDTO>>(UserConverter.convertToDTOsList(aU), 
				HttpStatus.OK);
	}
	
	@GetMapping(path = "/procurement-managers")
	public ResponseEntity<List<ProcurementManagerDTO>> findAllProcurementManagers() {
		List<ProcurementManager> allProcurementManagers = 
				userService.getProcurementManagerService().findAll();
		System.out.println("\nTotal number of procurement managers: " 
				+ allProcurementManagers.size());
		
		return new ResponseEntity<List<ProcurementManagerDTO>>(
				ProcurementManagerConverter.convertToDTOsList(allProcurementManagers), 
				HttpStatus.OK);
	}
	
	@GetMapping(path = "/company-administrators")
	public ResponseEntity<List<CompanyAdministratorDTO>> findAllCompanyAdministrators() {
		List<CompanyAdministrator> allCompanyAdministrators = 
				userService.getCompanyAdministratorService().findAll();
		System.out.println("\nTotal number of company administrators: " 
				+ allCompanyAdministrators.size());
		
		return new ResponseEntity<List<CompanyAdministratorDTO>>(
				CompanyAdministratorConverter.convertToDTOsList(allCompanyAdministrators), 
				HttpStatus.OK);
	}
	
	@GetMapping(path = "/system-administrators")
	public ResponseEntity<List<SystemAdministratorDTO>> findAllSystemAdministrators() {
		List<SystemAdministrator> allSystemAdministrators = 
				userService.getSystemAdministratorService().findAll();
		System.out.println("\nTotal number of system administrators: " 
				+ allSystemAdministrators.size());
		
		return new ResponseEntity<List<SystemAdministratorDTO>>(
				SystemAdministratorConverter.convertToDTOsList(allSystemAdministrators), 
				HttpStatus.OK);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable(name = "id") String id) {
		UserDTO user = null;
		
		long idAsLong = 0;
		try {
			idAsLong = Long.parseLong(id);
		} catch (NumberFormatException nFE) {
			return new ResponseEntity<UserDTO>(user, HttpStatus.BAD_REQUEST);
		}
		
		user = UserConverter.convertToDTO(userService.findById(idAsLong));
		
		return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
	}
	
	@GetMapping(path = "/find-by-username/{username}")
	public ResponseEntity<UserDTO> findByUsername(@PathVariable(name = "username") String username) {
		UserDTO user = null;
		if (username.isEmpty()) {
			return new ResponseEntity<UserDTO>(user, HttpStatus.BAD_REQUEST);
		}
		
		user = UserConverter.convertToDTO(userService.findByUsername(username));
		
		return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
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
		
		ProcurementManager newProcurementManager = userService
				.getProcurementManagerService().register(procurementManagerRegistrationReqDTO);
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
		
		ProcurementManager procurementManager = userService.getProcurementManagerService()
				.findByUserCode(codeOfNewRegisteredUserWrapper.getUserCode());
		
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
		
		System.out.println("\nAccount of user with username \"" + procurementManager.getUsername() 
				+ "\" has been successfully activated.\n");
		
		return new ResponseEntity<SimpleTextResponseDTO>(
				new SimpleTextResponseDTO("Account of user with username \"" + 
						procurementManager.getUsername() + "\" has been successfully activated."), 
				HttpStatus.OK);
	}
	
	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ObjectAndTextResponseDTO> edit(
			@PathVariable(name = "id") String id, @RequestBody UserDTO userToBeEdited) {
		UserDTO editedUser = null;
		
		long idAsLong = 0;
		try {
			idAsLong = Long.parseLong(id);
		} catch (NumberFormatException nFE) {
			return new ResponseEntity<ObjectAndTextResponseDTO>(
					new ObjectAndTextResponseDTO(editedUser, "Id sent to the server is not an integer!"), 
					HttpStatus.BAD_REQUEST);
		}
		
		String validationMessages = ValidationPerformer.getValidationMessages(userToBeEdited);
		if (validationMessages != null) {
			return new ResponseEntity<ObjectAndTextResponseDTO>(
					new ObjectAndTextResponseDTO(editedUser, validationMessages), 
					HttpStatus.BAD_REQUEST);
		}
		
		try {
			editedUser = UserConverter.convertToDTO(userService.edit(userToBeEdited));
		} catch (ChangeOfEmailAddressException cOEAE) {
			System.out.println("\n" + cOEAE.getMessage());
			
			return new ResponseEntity<ObjectAndTextResponseDTO>(
					new ObjectAndTextResponseDTO(editedUser, cOEAE.getMessage()), 
					HttpStatus.BAD_REQUEST);
		}
		
		System.out.println("\nPersonal data of user with id = " + idAsLong + " was successfully edited.\n");
		
		return new ResponseEntity<ObjectAndTextResponseDTO>(new 
				ObjectAndTextResponseDTO(editedUser, 
						"Personal data of user with id = " + idAsLong + " was successfully edited!"), 
				HttpStatus.OK);
	}
}
