//package com.crm.config;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import com.crm.filter.JwtAuthenticationFilter;
//import com.crm.security.JwtAuthenticationEntrypoint;
//import com.crm.security.*;
//import jakarta.servlet.Filter;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//
//public class SecurityConfig  
//{
//
//	@Autowired
//    private JwtAuthenticationFilter jwtAuthenticationFilter;
//	
//	@Autowired 
//	private JwtAuthenticationEntrypoint jwtAuthenticationEntrypoint;
//
//    @Autowired
//    private UserDetailsServiceImpl userDetailsServiceImpl;
//	 
//    @Bean
//    //authentication
//    public UserDetailsService userDetailsService() {
//
//        return new UserDetailsServiceImpl();
//    }
//    @SuppressWarnings("removal")
//	@Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//    	
//    	
//    	return http.csrf()
//    			.disable()
//                .authorizeHttpRequests()
//                .requestMatchers("/login", "/refreshToken").permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .exceptionHandling()
//                .authenticationEntryPoint(this.jwtAuthenticationEntrypoint)
//                .and()
//                .addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
//                .build();
// }
//    
//    @Bean
//    public AuthenticationProvider authenticationProvider(){
//        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsServiceImpl);
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        return authenticationProvider;
//    } 
//    
//    
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }
//}

package com.crm.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.crm.filter.JwtAuthenticationFilter;
import com.crm.security.JwtAuthenticationEntrypoint;
import com.crm.security.*;
import jakarta.servlet.Filter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity

public class SecurityConfig  
{

	@Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Autowired 
	private JwtAuthenticationEntrypoint jwtAuthenticationEntrypoint;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;
	 
    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/api/public/**",
            "/api/public/authenticate",
            "/actuator/*",
            "/swagger-ui/**",
            "/login","/refreshToken", "/download"
    };
    
    @Bean
    //authentication
    public UserDetailsService userDetailsService() {

        return new UserDetailsServiceImpl();
    }
    
    @SuppressWarnings("removal")
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	
    	
    	return http.csrf()
    			.disable()
                .authorizeHttpRequests()
                .requestMatchers(AUTH_WHITELIST).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(this.jwtAuthenticationEntrypoint)
                .and()
                .addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
 }
    
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsServiceImpl);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    } 
    
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
