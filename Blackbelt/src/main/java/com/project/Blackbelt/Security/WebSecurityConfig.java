package com.project.Blackbelt.Security;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;




@Configuration
@EnableWebMvc
public class WebSecurityConfig implements WebMvcConfigurer {

	@Autowired
	private final SecurityDatabaseService securityDatabaseService;

	public WebSecurityConfig(SecurityDatabaseService securityDatabaseService) {
		this.securityDatabaseService = securityDatabaseService;
	}

	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(securityDatabaseService).passwordEncoder(NoOpPasswordEncoder.getInstance());
	}
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((authz) -> authz
				// Rotas acessíveis apenas pelo admin
				.requestMatchers("/login.css","/img/**","/js/**").permitAll()
				.requestMatchers("/salvarusuarios","/listarusuarios","/editarusuarios/{idusuario}",
						"/filial","/upload","/usuarios","/update-sheets").hasRole("MANAGER")
				
				// Qualquer outra rota requer autenticação
				.anyRequest().authenticated())
				// Usa o form da pagina tela.html do Spring Security
				.formLogin((form) -> form
						.loginPage("/login")
						.failureUrl("/login?error=true")
						.defaultSuccessUrl("/login?success", true) 
						.permitAll())
				 .logout(logout -> logout.logoutUrl("/logout")
			             .permitAll());  
		       
		
		http.csrf(csrf -> csrf.disable());

		return http.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return securityDatabaseService;
	}
	/*
	 * @Bean public InMemoryUserDetailsManager userDetailsService() {
	 * 
	 * @SuppressWarnings("deprecation") UserDetails user =
	 * User.withDefaultPasswordEncoder().username("user").password("password").roles
	 * ("USER") .build();
	 * 
	 * @SuppressWarnings("deprecation") UserDetails user2 =
	 * User.withDefaultPasswordEncoder().username("user2").password("1234567").roles
	 * ("USER") .build();
	 * 
	 * @SuppressWarnings("deprecation") UserDetails admin =
	 * User.withDefaultPasswordEncoder().username("admin").password("12345678").
	 * roles("MANAGER") .build(); return new InMemoryUserDetailsManager(user, user2,
	 * admin); }
	 */

}
