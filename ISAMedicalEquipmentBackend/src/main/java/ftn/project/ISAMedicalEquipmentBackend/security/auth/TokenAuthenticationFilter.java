package ftn.project.ISAMedicalEquipmentBackend.security.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import ftn.project.ISAMedicalEquipmentBackend.util.TokenUtils;
import io.jsonwebtoken.ExpiredJwtException;

public class TokenAuthenticationFilter extends OncePerRequestFilter {
	private TokenUtils tokenUtils;
	private UserDetailsService userDetailsService;
	
	private final Log LOGGER = LogFactory.getLog(getClass());
	
	public TokenAuthenticationFilter(TokenUtils tokenUtils, 
			UserDetailsService userDetailsService) {
		this.tokenUtils = tokenUtils;
		this.userDetailsService = userDetailsService;
	}
	
	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
			FilterChain filterChain) throws ServletException, IOException {
		String authToken = tokenUtils.getToken(request);
		
		String username;
		try {
			if (authToken != null) {
				username = tokenUtils.getUsernameFromToken(authToken);
				
				if (username != null) {
					UserDetails userDetails = userDetailsService.loadUserByUsername(username);
					
					if (tokenUtils.validateToken(authToken, userDetails)) {
						TokenBasedAuthentication tokenBasedAuthentication = 
								new TokenBasedAuthentication(userDetails);
						
						tokenBasedAuthentication.setToken(authToken);
						
						SecurityContextHolder.getContext().setAuthentication(tokenBasedAuthentication);
					}
				}
			}
		} catch (ExpiredJwtException eJwtE) {
			LOGGER.debug("Token has expired!");
		}
		
		filterChain.doFilter(request, response);
	}
}
