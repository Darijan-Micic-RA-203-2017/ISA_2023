package ftn.project.ISAMedicalEquipmentBackend.controller.user;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.project.ISAMedicalEquipmentBackend.domain.user.User;
import ftn.project.ISAMedicalEquipmentBackend.dto.AccessTokenDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.CredentialsDTO;
import ftn.project.ISAMedicalEquipmentBackend.util.TokenUtils;

@RestController
@RequestMapping(path = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {
	private final TokenUtils tokenUtils;
	
	private final AuthenticationManager authenticationManager;
	
	@Autowired
	public AuthController(TokenUtils tokenUtils, AuthenticationManager authenticationManager) {
		this.tokenUtils = tokenUtils;
		this.authenticationManager = authenticationManager;
	}
	
	@PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AccessTokenDTO> login(@RequestBody CredentialsDTO credentials, 
			HttpServletResponse response) {
		Authentication authentication = null;
		String accessToken = "";
		long expiresIn = 0;
		
		try {
			authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(credentials.getUsername(), 
							credentials.getPassword()));
		} catch (DisabledException dE) {
			System.out.println("\nUser with sent credentials is disabled.\n");
			
			return new ResponseEntity<AccessTokenDTO>(new AccessTokenDTO(accessToken, expiresIn), 
					HttpStatus.CONFLICT);
		} catch (LockedException lE) {
			System.out.println("\nUser with sent credentials is locked.\n");
			
			return new ResponseEntity<AccessTokenDTO>(new AccessTokenDTO(accessToken, expiresIn), 
					HttpStatus.LOCKED);
		} catch (BadCredentialsException bCE) {
			System.out.println("\nBad credentials were sent to the server.\n");
			
			return new ResponseEntity<AccessTokenDTO>(new AccessTokenDTO(accessToken, expiresIn), 
					HttpStatus.BAD_REQUEST);
		}
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		User user = (User) authentication.getPrincipal();
		accessToken = tokenUtils.generateToken(user);
		expiresIn = tokenUtils.getExpiresIn();
		
		System.out.println("\nUser with username \"" + credentials.getUsername() + "\" was " + 
			"successfully logged in.\n");
		
		return new ResponseEntity<AccessTokenDTO>(new AccessTokenDTO(accessToken, expiresIn), 
				HttpStatus.OK);
	}
}
