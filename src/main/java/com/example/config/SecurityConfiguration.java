package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.service.CustomUserDetailService;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

	@Autowired
	private CustomAuthSuccessHandler sucessHandler;
	
//  In memory authentication	
//	@Bean
//	UserDetailsService userDetailService() {
//		UserDetails user = User.withUsername("user").password(passwordEncoder().encode("alok")).roles("USER").build();
//		UserDetails admin = User.withUsername("admin").password(passwordEncoder().encode("alok")).roles("ADMIN").build();
//		return new InMemoryUserDetailsManager(user,admin);
//	}
//	
//	@Bean
//	PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
//	@Bean
//	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.csrf().disable()
//		.authorizeHttpRequests()
//		.anyRequest()
//		.authenticated()
//		.and()
//		.formLogin();
//		return http.build();
//	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	UserDetailsService getDetailsService() {
		return new CustomUserDetailService();
	}
	
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(getDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http.csrf().disable().authorizeHttpRequests()
//		.requestMatchers("/").permitAll()
//		.anyRequest().authenticated().and()
//		.formLogin().loginPage("/login")
//		.loginProcessingUrl("/employee-login")
//		.defaultSuccessUrl("/about").permitAll()
////		.failureUrl("/index")
//		.and()
//		.logout().logoutSuccessUrl("/user-logout")
//		.permitAll();
		
		http.csrf().disable()
		.authorizeHttpRequests().requestMatchers("/user/**").hasRole("USER")
		.requestMatchers("/dev/**").hasRole("DEV")
		.requestMatchers("/**").permitAll().and()
		.formLogin().loginPage("/login").loginProcessingUrl("/employee-login")
		.successHandler(sucessHandler)
		.permitAll();
		return http.build();
	}
}
