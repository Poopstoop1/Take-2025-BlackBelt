package com.project.Blackbelt.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Blackbelt.Model.Users;
import com.project.Blackbelt.Repository.UserRepository;

/**
 * Serviço responsável por lidar com o processo de recuperação de senha.
 * 
 * Inclui funcionalidades como geração de token, envio de e-mail e redefinição de senha.
 * 
 * @author Poopstoop1
 */

@Service
public class RecuperacaosenhaService {

	@Autowired
    private UserRepository usuarioRepository;

    @Autowired
    private EmailService emailService;

    /**
     * Inicia o processo de recuperação de senha para o e-mail fornecido.
     *
     * @param email O e-mail do usuário que deseja redefinir a senha.
     */
    public void processarSolicitacaoRecuperacao(String email) {
        Users usuario = usuarioRepository.findByUsername(email);
        if (usuario != null) {
            String token = UUID.randomUUID().toString();
            usuario.setTokenRecuperacaoSenha(token);
            usuario.setTokenExpiracao(LocalDateTime.now().plusHours(1));
            usuarioRepository.save(usuario);

            String link = "http://localhost:8080/redefinirsenha?token=" + token;
            String corpo = "Clique no link para redefinir sua senha: " + link;

            emailService.enviarEmail(usuario.getLogin(), "Redefinição de Senha", corpo);
        }
    }

    /**
     * Valida o token de recuperação de senha.
     *
     * @param token Token recebido pelo usuário.
     * @return Usuário correspondente se o token for válido e não estiver expirado, senão null.
     */
    public Users validarToken(String token) {
        Users usuario = usuarioRepository.findByTokenRecuperacaoSenha(token);
        if (usuario != null && usuario.getTokenExpiracao().isAfter(LocalDateTime.now())) {
            return usuario;
        }
        return null;
    }
    /**
     * Redefine a senha do usuário.
     *
     * @param usuario   O usuário cuja senha será redefinida.
     * @param novaSenha A nova senha em texto plano.
     */
    public void redefinirSenha(Users usuario, String novaSenha) {
        usuario.setPassword(novaSenha); // você deve codificar a senha
        usuario.setTokenRecuperacaoSenha(null);
        usuario.setTokenExpiracao(null);
        usuarioRepository.save(usuario);
    }
}
