package com.project.Blackbelt.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador responsável por exibir a página de login do sistema.
 * 
 * Data de criação: 08-04-2025
 * 
 * @author Poopstoop1 - Paulo Daniel
 * @version 1.0
 * @since Java 21 (JDK 21)
 */
@Controller
public class LoginController {
     /**
     * Mapeia a rota "/login" e retorna a view da página de login.
     * 
     * @return Caminho da view "resources/templates/paginas/login".
     */
	@RequestMapping("/login")
    public String login() {
        return "/paginas/login";
    }
}
