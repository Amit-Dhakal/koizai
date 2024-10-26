
package com.example.koizaiauthdemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import lombok.var;

@Configuration
@EnableWebSecurity
public class UserManageConfig extends WebSecurityConfigurerAdapter {

	@Bean
	protected UserDetailsService userDetailsService() {
		// TODO Auto-generated method stub		
		var memory=new InMemoryUserDetailsManager();
		memory.createUser(User.withUsername("amit").password("123").authorities("read").build());
		return memory;
	}
	
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {	
		return NoOpPasswordEncoder.getInstance();
	}
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManager();
		
	}



	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin();
		http.authorizeRequests().anyRequest().authenticated();
					
	}
	
	
	
	
	
}

