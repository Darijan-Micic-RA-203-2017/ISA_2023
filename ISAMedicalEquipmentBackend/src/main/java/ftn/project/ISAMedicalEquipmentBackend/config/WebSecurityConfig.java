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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import ftn.project.ISAMedicalEquipmentBackend.security.auth.RestAuthenticationEntryPoint;
import ftn.project.ISAMedicalEquipmentBackend.security.auth.TokenAuthenticationFilter;
import ftn.project.ISAMedicalEquipmentBackend.service.impl.user.CustomUserDetailsService;
import ftn.project.ISAMedicalEquipmentBackend.util.TokenUtils;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	private CustomUserDetailsService customUserDetailsService;
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	private TokenUtils tokenUtils;
	
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
		super();
		
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
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
				.authorizeRequests()
				.antMatchers("/auth/**").permitAll()
				.antMatchers("/users/register-as-a-procurement-manager").permitAll()
				.antMatchers("/users/activate-account").permitAll()
				.antMatchers("/medical-equipment-companies").permitAll()
				.antMatchers("/medical-equipment-companies/{id}").permitAll()
				.antMatchers("/medical-equipment-companies/search-by-name-or-populated-place").permitAll()
				.antMatchers("/medical-equipment").permitAll()
				.antMatchers("/medical-equipment/{id}").permitAll()
				.antMatchers("/medical-equipment/of-company/{companyName}").authenticated()
				.antMatchers("/medical-equipment/search-by-name").permitAll()
				.antMatchers("/medical-equipment/search-by-name/of-company/{companyName}").authenticated()
				.antMatchers("/types-of-medical-equipment").permitAll()
				.antMatchers("/types-of-medical-equipment/{id}").permitAll()
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
