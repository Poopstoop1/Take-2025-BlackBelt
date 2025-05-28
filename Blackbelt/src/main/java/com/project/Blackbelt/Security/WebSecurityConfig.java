package com.project.Blackbelt.Security;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;


import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * Classe de configuração do Spring Security em Java 21 para controle de acesso às rotas da aplicação.
 * 
 * 
 * Define permissões por rota, configura página de login, logout, tratamento de erros
 * e uso do banco de dados como fonte de autenticação.
 * 
 * 
 * @author Poopstoop1
 * @version 1.0
 */


@Configuration
@EnableWebMvc
public class WebSecurityConfig implements WebMvcConfigurer {

	@Autowired
	private final SecurityDatabaseService securityDatabaseService;

	 /**
     * Construtor com injeção de dependência da classe de autenticação personalizada.
     * 
     * @param securityDatabaseService Serviço de autenticação com base no banco de dados.
     */
	public WebSecurityConfig(SecurityDatabaseService securityDatabaseService) {
		this.securityDatabaseService = securityDatabaseService;
	}

	/**
     * Configura o {@link AuthenticationManagerBuilder} para utilizar o serviço de autenticação
     * e um codificador de senha.
     * 
     * @param auth AuthenticationManagerBuilder
     * @throws Exception se houver erro na configuração
     */

		 
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(securityDatabaseService).passwordEncoder(NoOpPasswordEncoder.getInstance());
	}
	
	 /**
     * Define mapeamento de recursos estáticos para serem servidos diretamente pelo Spring.
     * 
     * @param registry registro de manipuladores de recursos
     */
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }

		 /**
     * Configura o filtro de segurança, rotas públicas, privadas, login e logout.
     * 
     * @param http objeto {@link HttpSecurity} para configurar a segurança da aplicação
     * @return {@link SecurityFilterChain}
     * @throws Exception se houver erro de configuração
     */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((authz) -> authz
		// Rotas acessiveis para todos
		.requestMatchers("/login.css","/img/**","/js/**","/esquecersenha","/redefinirsenha","/recuperacao.css"
		, "/recuperar-senha").permitAll()
		
						// Rotas acessíveis apenas pelo admin
				.requestMatchers("/salvarusuarios","/listarusuarios","/editarusuarios/{idusuario}",
						"/filial","/upload","/usuarios","/empresa","/update-sheets","/importar-csv").hasRole("MANAGER")
				
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
	
	 /**
     * Define o {@link UserDetailsService} utilizado na autenticação.
     * 
     * @return implementação personalizada {@link SecurityDatabaseService}
     */
	@Bean
	public UserDetailsService userDetailsService() {
		return securityDatabaseService;
	}

}
