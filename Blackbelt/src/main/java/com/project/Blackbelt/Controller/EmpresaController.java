package com.project.Blackbelt.Controller;

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

import com.project.Blackbelt.Model.Empresa;
import com.project.Blackbelt.Model.Users;
import com.project.Blackbelt.Repository.EmpresaRepository;
import com.project.Blackbelt.Repository.UserRepository;

/**
 * Controlador responsável pela gestão de empresas no sistema Blackbelt.
 *
 * Permite visualizar, cadastrar, editar e excluir filiais (empresas).
 * 
 * Data de criação: 09-04-2025
 * 
 * @author Poopstoop1 - Paulo Daniel
 * @version 1.0
 * @since Java 21 (JDK 21)
 */

@Controller
public class EmpresaController {
	
	@Autowired
	private EmpresaRepository filialrepository;
	
	@Autowired
	private UserRepository userRepository;

	  /**
     * Exibe a página de gestão de empresas com as filiais cadastradas.
     * 
     * @param model Objeto {@link Model} para adicionar atributos à página.
     * @return Caminho da view "/paginas/gestaodeempresas".
     */
	
	@RequestMapping("/empresa")
	public String filial(Model model) {	
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		String username = auth.getName();
		// Busca usuario pelo nome
		Users usua = userRepository.findByUsername(username);

		// atribui o nome do usuario ao Header
		if(username != null){
			model.addAttribute("message", usua.getNome());
		}

		model.addAttribute("filialobj", new Empresa());
		//Busco as empresas
		Iterable<Empresa> filiais = filialrepository.findAll();
			// Onde coloco todas as empresas e jogo para tabela de empresas no front.
     model.addAttribute("filiais", filiais);

		return "/paginas/gestaodeempresas";
	}
	
	 /**
     * Salva uma nova empresa no banco de dados.
     * 
     * @param filiais Objeto {@link Empresa} contendo os dados da nova empresa.
     * @return Redireciona para "/empresa" após o salvamento.
     */
	
	 @RequestMapping(method = RequestMethod.POST, value = "/salvarfiliais")
	    public ModelAndView salvar(@ModelAttribute Empresa filiais) {
	        ModelAndView modelview = new ModelAndView("redirect:/empresa");
	        filialrepository.save(filiais);
	        return modelview;
	    }

			 /**
     * Edita uma empresa existente no banco de dados.
     * 
     * @param filial Objeto {@link Empresa} com os dados atualizados.
     * @return Redireciona para "/empresa" após a atualização.
     */
	 @RequestMapping(method = RequestMethod.POST, value = "/editarfilial")
	 public ModelAndView editarFilial(@ModelAttribute Empresa filial) {
	     // Busca a filial pelo CNPJ
	     Optional<Empresa> filialExistente = filialrepository.findById(filial.getCnpj());
	     
	     if (filialExistente.isPresent()) {
	         // Atualiza os dados da filial existente
	         Empresa existente = filialExistente.get();
	         existente.setNome(filial.getNome());
	         existente.setRazaosocial(filial.getRazaosocial());
	         
	         // Salva as alterações no banco
	         filialrepository.save(existente);
	     } else {
	         System.out.println("Filial não encontrada com o CNPJ: " + filial.getCnpj());
	     }
	     
	     // Redireciona para a página de gestão de filiais
	     return new ModelAndView("redirect:/empresa");
	 }

	  /**
     * Exclui uma Empresa do banco de dados com base no CNPJ.
     * 
     * @param cnpj CNPJ da empresa a ser removida.
     * @return Redireciona para "/empresa" após a exclusão.
     */
	    @RequestMapping(method = RequestMethod.GET, value = "/removerfilial/{cnpjfilial}")
	    public ModelAndView excluir(@PathVariable("cnpjfilial") String cnpj) {
	        filialrepository.deleteById(cnpj);
	        return new ModelAndView("redirect:/empresa");
	    }
	

}
