package ftn.project.ISAMedicalEquipmentBackend.util;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import ftn.project.ISAMedicalEquipmentBackend.domain.user.User;
import ftn.project.ISAMedicalEquipmentBackend.domain.user.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenUtils {
	@Value(value = "ISAMedicalEquipmentBackend")
	private String APP_NAME;
	
	@Value(value = "Equipment_type")
	public String SECRET;
	
	@Value(value = "3600000")
	private long EXPIRES_IN;
	
	@Value(value = "Authorization")
	private String AUTH_HEADER;
	
	private static final String AUDIENCE_WEB = "web";
	
	private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;
	
	public TokenUtils() {}
	
	public String generateToken(User user) {
		String roleName = "";
		for (UserRole role: user.getRoles()) {
			roleName = role.getName();
		}
		
		return Jwts.builder()
				.claim("userId", user.getId())
				.claim("role", roleName)
				.claim("gender", user.getGender())
				.setIssuer(APP_NAME)
				.setSubject(user.getUsername())
				.setAudience(generateAudience())
				.setIssuedAt(new Date())
				.setExpiration(generateExpirationDate())
				.signWith(SIGNATURE_ALGORITHM, SECRET).compact();
	}
	
	private String generateAudience() {
		return AUDIENCE_WEB;
	}
	
	private Date generateExpirationDate() {
		return new Date(new Date().getTime() + EXPIRES_IN);
	}
	
	public long getExpiresIn() {
		return EXPIRES_IN;
	}
	
	public String getToken(HttpServletRequest request) {
		String authHeader = getAuthHeaderFromRequest(request);
		
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			return authHeader.substring(7);
		}
		
		return null;
	}
	
	public String getAuthHeaderFromRequest(HttpServletRequest request) {
		return request.getHeader(AUTH_HEADER);
	}
	
	public boolean validateToken(String token, UserDetails userDetails) {
		User user = (User) userDetails;
		final String username = getUsernameFromToken(token);
		final Date issuedAt = getIssuedAtFromToken(token);
		
		return (username != null && username.equals(userDetails.getUsername()) && 
				!isIssuedBeforeLastPasswordReset(issuedAt, user.getLastPasswordResetDate()));
	}
	
	private boolean isIssuedBeforeLastPasswordReset(Date issuedAt, 
			Timestamp lastPasswordResetDate) {
		return (lastPasswordResetDate != null && issuedAt.before(lastPasswordResetDate));
	}
	
	private Claims getAllClaimsFromToken(String token) {
		Claims claims;
		
		try {
			claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
		} catch (ExpiredJwtException eJwtE) {
			throw eJwtE;
		} catch (Exception e) {
			claims = null;
		}
		
		return claims;
	}
	
	public String getUsernameFromToken(String token) {
		String username;
		
		try {
			final Claims claims = getAllClaimsFromToken(token);
			username = claims.getSubject();
		} catch (ExpiredJwtException eJwtE) {
			throw eJwtE;
		} catch (Exception e) {
			username = null;
		}
		
		return username;
	}
	
	public Date getIssuedAtFromToken(String token) {
		Date issuedAt;
		
		try {
			final Claims claims = getAllClaimsFromToken(token);
			issuedAt = claims.getIssuedAt();
		} catch (ExpiredJwtException eJwtE) {
			throw eJwtE;
		} catch (Exception e) {
			issuedAt = null;
		}
		
		return issuedAt;
	}
	
	public String getAudienceFromToken(String token) {
		String audience;
		
		try {
			final Claims claims = getAllClaimsFromToken(token);
			audience = claims.getAudience();
		} catch (ExpiredJwtException eJwtE) {
			throw eJwtE;
		} catch (Exception e) {
			audience = null;
		}
		
		return audience;
	}
	
	public Date getExpirationFromToken(String token) {
		Date expiration;
		
		try {
			final Claims claims = getAllClaimsFromToken(token);
			expiration = claims.getExpiration();
		} catch (ExpiredJwtException eJwtE) {
			throw eJwtE;
		} catch (Exception e) {
			expiration = null;
		}
		
		return expiration;
	}
}
