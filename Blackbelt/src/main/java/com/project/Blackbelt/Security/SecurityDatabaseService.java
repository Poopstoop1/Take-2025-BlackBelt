package com.project.Blackbelt.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.Blackbelt.Model.Users;
import com.project.Blackbelt.Repository.UserRepository;

/**
 * Serviço responsável por carregar os detalhes do usuário a partir do banco de dados
 * para uso pelo Spring Security durante o processo de autenticação.
 * 
 * Implementa a interface {@link UserDetailsService} para fornecer dados do usuário com base no nome de login.
 * 
 * @author Poopstoop1
 * @version 1.0
 */
@Service
public class SecurityDatabaseService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;

/**
  * Carrega os detalhes de um usuário pelo seu nome de usuário (login).
  * 
  * @param username Nome de login fornecido no formulário de autenticação.
  * @return Um objeto {@link UserDetails} contendo os dados do usuário.
  * @throws UsernameNotFoundException Se o usuário não for encontrado no banco de dados.
  */

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// busca o usuario no banco de dados
		Users userEntity = userRepository.findByUsername(username);
		 // Lança exceção se o usuário não existir
		if(userEntity == null) {
			throw new UsernameNotFoundException("Usuário não foi encontrado");
		}
		  // Log de verificação para debug
		System.out.println("Usuário encontrado: " + userEntity.getUsername());
		
		// Retorna o usuário, que implementa a interface UserDetails
		return userEntity;
	}
	

}
