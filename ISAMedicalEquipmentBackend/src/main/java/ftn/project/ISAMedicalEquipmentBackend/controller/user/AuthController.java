package ftn.project.ISAMedicalEquipmentBackend.controller.user;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(credentials.getUsername(), 
						credentials.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String accessToken = "";
		long expiresIn = 0;
		try {
			User user = (User) authentication.getPrincipal();
			
			accessToken = tokenUtils.generateToken(user);
			expiresIn = tokenUtils.getExpiresIn();
		} catch (BadCredentialsException bCE) {
			System.out.println("\nBad credentials have been sent to the server.\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("\nUser with username \"" + credentials.getUsername() + "\" has " + 
				"been successfully logged in.\n");
		
		return new ResponseEntity<AccessTokenDTO>(new AccessTokenDTO(accessToken, expiresIn), 
				HttpStatus.OK);
	}
}
