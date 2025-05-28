package com.project.Blackbelt.Controller;

import com.project.Blackbelt.Model.Users;
import com.project.Blackbelt.Repository.UserRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controlador responsável pela gestão de usuários, incluindo listagem, cadastro, edição e exclusão.
 * 
 * <p>
 * Data de criação: 07-04-2025
 * </p>
 * 
 * @author Poopstoop1 - Paulo Daniel
 * @version 1.0
 * @since Java 21 (JDK 21)
 */

@Controller
public class UsuarioController {

	@Autowired
	private UserRepository userRepository;


    /**
     * Exibe a página de gestão de usuários com a lista completa e o nome do usuário autenticado.
     * 
     * @param model Objeto Model usado para passar atributos à view.
     * @return Caminho da view "/paginas/gestaodeusuarios".
     */
	@RequestMapping("/usuarios")
	public String user(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Users usua = userRepository.findByUsername(username);

		//Coloca nome do usuario no header
		if(username != null){
			model.addAttribute("message", usua.getNome());
		}
		
		// Coloca nome de usuario na tabela
		 model.addAttribute("usuarios", userRepository.findAll());
		 
		 // Instância para criar um usuario
		model.addAttribute("usuarioobj", new Users());
		
		return "/paginas/gestaodeusuarios";
	}

	
	 /**
     * Salva um novo usuário no banco de dados.
     * 
     * @param usuario Objeto do tipo Users preenchido a partir do formulário.
     * @return Redireciona para a página de gestão de usuários.
     */
    @RequestMapping(method = RequestMethod.POST, value = "/salvarusuarios")
    public String salvar(@ModelAttribute Users usuario) {
    	
        userRepository.save(usuario);
        return "redirect:/usuarios";
    }
    /**
     * Edita os dados de um usuário já existente no banco de dados.
     * 
     * @param user Objeto Users com os novos dados a serem atualizados.
     * @return Redireciona para a página de gestão de usuários.
     */
    @RequestMapping(method = RequestMethod.POST, value = "/editarusuarios")
	 public ModelAndView editarFilial(@ModelAttribute Users user) {
	     // Busca a filial pelo CNPJ
	     Optional<Users> userExistente = userRepository.findById(user.getId());
	     
	     if (userExistente.isPresent()) {
	         // Atualiza os dados da filial existente
	         Users existente = userExistente.get();
	         existente.setLogin(user.getLogin());
	         existente.setPassword(user.getPassword());
	         existente.setCargo(user.getCargo());
	         existente.setNome(user.getNome());
	         existente.setPermissao(user.getPermissao());
	         existente.setEmpresa(user.getEmpresa());
	         
	         // Salva as alterações no banco
	         userRepository.save(existente);
	     } else {
	         System.out.println("User não encontrado: " + user.getId());
	     }
	     
	     // Redireciona para a página de gestão de filiais
	     return new ModelAndView("redirect:/usuarios");
	 }
		/**
     * Remove um usuário do banco de dados com base no ID fornecido.
     * 
     * @param idusuario ID do usuário a ser excluído.
     * @return Redireciona para a página de gestão de usuários.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/removerusuario/{idusuario}")
    public String excluir(@PathVariable("idusuario") Long idusuario) {
        userRepository.deleteById(idusuario);
        return "redirect:/usuarios";
    }
    
}
