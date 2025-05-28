package com.project.Blackbelt.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.Blackbelt.Model.Users;
import com.project.Blackbelt.Service.RecuperacaosenhaService;

/**
 * Controlador responsável pelas funcionalidades de recuperação e redefinição de senha.
 * 
 * <p>
 * Data de criação: 27-05-2025
 * </p>
 * 
 * @author Poopstoop1 - Paulo Daniel
 * @version 1.0
 * @since Java 21 (JDK 21)
 */

@Controller
public class RecuperacaoSenhaController {
	
	  @Autowired
	    private RecuperacaosenhaService recuperacaoSenhaService;

     /**
     * Exibe o formulário de solicitação de recuperação de senha.
     * 
     * @param model Objeto Model utilizado para enviar atributos à view.
     * @return Caminho da view "/paginas/recuperar-senha".
     */
    @RequestMapping("/esquecersenha")
    public String RecuperarSenha(Model model) {
    	
        return "/paginas/recuperar-senha";
    }

    /**
     * Processa a solicitação de recuperação de senha com base no e-mail fornecido.
     * 
     * @param email E-mail do usuário que solicitou a recuperação de senha.
     * @return View "/paginas/recuperar-senha" com mensagem de confirmação.
     */
    @PostMapping("/recuperar-senha")
    public ModelAndView processarRecuperacao(@RequestParam("email") String email) {
        recuperacaoSenhaService.processarSolicitacaoRecuperacao(email);
        ModelAndView mv = new ModelAndView("/paginas/recuperar-senha");
        mv.addObject("mensagem", "E-mail de recuperação enviado.");
        return mv;
    }

    /**
     * Exibe o formulário para redefinição de senha se o token for válido.
     * 
     * @param token Token de recuperação enviado por e-mail.
     * @return View "/paginas/redefinir-senha" ou redirecionamento em caso de token inválido.
     */

    @GetMapping("/redefinirsenha")
    public ModelAndView mostrarFormularioRedefinirSenha(@RequestParam("token") String token) {
        Users usuario = recuperacaoSenhaService.validarToken(token);
        if (usuario == null) {
            return new ModelAndView("redirect:/esquecersenha?tokenInvalido");
        }
        ModelAndView mv = new ModelAndView("/paginas/redefinir-senha");
        mv.addObject("token", token);
        return mv;
    }

    /**
     * Processa a redefinição da senha do usuário com base no token e nova senha fornecidos.
     * 
     * @param token Token de recuperação válido.
     * @param senha Nova senha fornecida pelo usuário.
     * @return Redireciona para a página inicial com mensagem de senha atualizada.
     */
    @PostMapping("/redefinirsenha")
    public ModelAndView processarRedefinicaoSenha(@RequestParam("token") String token,
                                                  @RequestParam("senha") String senha) {
        Users usuario = recuperacaoSenhaService.validarToken(token);
        if (usuario == null) {
            return new ModelAndView("redirect:/esquecersenha?tokenInvalido");
        }
        recuperacaoSenhaService.redefinirSenha(usuario, senha);
        return new ModelAndView("redirect:/?senhaAtualizada");
    }

 
}
