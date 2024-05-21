package ftn.project.ISAMedicalEquipmentBackend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import ftn.project.ISAMedicalEquipmentBackend.domain.user.User;
import ftn.project.ISAMedicalEquipmentBackend.security.auth.RestAuthenticationEntryPoint;
import ftn.project.ISAMedicalEquipmentBackend.security.auth.TokenAuthenticationFilter;
import ftn.project.ISAMedicalEquipmentBackend.service.impl.user.CustomUserDetailsService;
import ftn.project.ISAMedicalEquipmentBackend.util.TokenUtils;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	private final CustomUserDetailsService customUserDetailsService;
	private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	private final TokenUtils tokenUtils;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Autowired
	public WebSecurityConfig(CustomUserDetailsService customUserDetailsService, 
			RestAuthenticationEntryPoint restAuthenticationEntryPoint, TokenUtils tokenUtils) {
		this.customUserDetailsService = customUserDetailsService;
		this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
		this.tokenUtils = tokenUtils;
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) 
			throws Exception {
		authenticationManagerBuilder.userDetailsService(customUserDetailsService)
				.passwordEncoder(passwordEncoder());
	}
	
	// REFERENCE: https://stackoverflow.com/questions/51712724/how-to-allow-a-user-only-access-their-own-data-in-spring-boot-spring-security/51713982#51713982
	public boolean hasId(long userId) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (user.getId() == userId) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
				.authorizeRequests()
				.antMatchers("/auth/**").permitAll()
				.antMatchers("/users").hasRole("SYSTEM_ADMINISTRATOR")
				.antMatchers("/users/procurement-managers").hasRole("SYSTEM_ADMINISTRATOR")
				.antMatchers("/users/company-administrators").hasRole("SYSTEM_ADMINISTRATOR")
				.antMatchers("/users/system-administrators").hasRole("SYSTEM_ADMINISTRATOR")
				// REFERENCE: https://stackoverflow.com/questions/51712724/how-to-allow-a-user-only-access-their-own-data-in-spring-boot-spring-security/51713982#51713982
				.antMatchers("/users/{id}").access("hasRole(\"SYSTEM_ADMINISTRATOR\") OR @webSecurityConfig.hasId(#id)")
				.antMatchers("/users/find-by-username/{username}").hasRole("SYSTEM_ADMINISTRATOR")
				.antMatchers("/users/register-as-a-procurement-manager").permitAll()
				.antMatchers("/users/activate-account").permitAll()
				.antMatchers("/medical-equipment-companies").permitAll()
				.antMatchers("/medical-equipment-companies/{id}").authenticated()
				.antMatchers("/medical-equipment-companies/search-by-name-or-populated-place").permitAll()
				.antMatchers("/medical-equipment").permitAll()
				.antMatchers("/medical-equipment/{id}").authenticated()
				.antMatchers("/medical-equipment/of-company/{companyName}").authenticated()
				.antMatchers("/medical-equipment/search-by-name").permitAll()
				.antMatchers("/medical-equipment/search-by-name/of-company/{companyName}").authenticated()
				.antMatchers("/types-of-medical-equipment").authenticated()
				.antMatchers("/types-of-medical-equipment/{id}").authenticated()
				.antMatchers("/exchange-terms").authenticated()
				.antMatchers("/exchange-terms/{id}").authenticated()
				.antMatchers("/exchange-terms/on-specific-date").authenticated()
				.antMatchers("/exchange-terms/on-specific-date-of-company/{companyId}").authenticated()
				.antMatchers("/exchange-terms/of-company/{companyId}").authenticated()
				.antMatchers("/exchange-terms/reserve").authenticated()
				.antMatchers("/equipment-orders").authenticated()
				.antMatchers("/equipment-orders/{id}").authenticated()
				.antMatchers("/details-of-equipment-orders").authenticated()
				.antMatchers("/details-of-equipment-orders/{id}").authenticated()
				.antMatchers("/ordering/create-order").authenticated()
				.antMatchers("/ordering/generate-qr-code").authenticated()
				.anyRequest().authenticated().and()
				.cors().and()
				.addFilterBefore(new TokenAuthenticationFilter(tokenUtils, 
						customUserDetailsService), BasicAuthenticationFilter.class);
		
		http.csrf().disable();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.POST, "/auth/login");
		
		web.ignoring().antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "favicon.ico", 
				"/**/*.html", "/**/*.css", "/**/*.js");
	}
}
