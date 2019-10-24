/**
 * 
 */
package com.moi.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

import com.moi.project.Userdservice;

/**
 * @author moi
 *
 */
@Configuration
@EnableWebSecurity
@EnableAuthorizationServer

@EnableGlobalMethodSecurity(securedEnabled=true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private Userdservice userDetailsService;
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
		
		
		
//      .inMemoryAuthentication()
//      .withUser("moi")
//        .password("{noop}moi")
//        .roles("USER")
//        .and()
//      .withUser("admin")
//        .password("{noop}admin")
//        .roles("USER", "ADMIN");
	}
	/**
	 * gestion des autorisations
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		.csrf()
		.disable()
        .authorizeRequests()
        .antMatchers("/a.html").permitAll()
        .antMatchers(HttpMethod.GET, "/api2/categories/**").hasRole("USER")
        .antMatchers(HttpMethod.POST, "/api2/categories/**").hasRole("ADMIN")
        .antMatchers(HttpMethod.PUT, "/api2/categories/**").hasRole("ADMIN")
        .antMatchers(HttpMethod.PATCH, "/api2/categories/**").hasRole("ADMIN")
        .antMatchers(HttpMethod.DELETE, "/api2/categories/**").hasRole("ADMIN")
        
       
        .anyRequest()
        .authenticated()
        .and()
        .formLogin();
        
               
        
        
	}
	
	private PasswordEncoder getPasswordEncoder() {
        return new PasswordEncoder() {
           
            @Override public String encode(CharSequence rawPassword) 
            { return BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt(4)); 
            } 
            @Override public boolean matches(CharSequence rawPassword, String encodedPassword) { 
            	return BCrypt.checkpw(rawPassword.toString(), encodedPassword); 
            	}
        };
    }
	
}
