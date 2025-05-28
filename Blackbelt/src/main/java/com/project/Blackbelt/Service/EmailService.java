package com.project.Blackbelt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Serviço responsável pelo envio de e-mails simples usando {@link JavaMailSender}.
 * 
 * Essa classe pode ser usada para enviar mensagens de texto plano como parte de notificações,
 * recuperação de senha, confirmações, etc.
 * 
 * 
 * @author Poopstoop1
 * @version 1.0
 */
@Service
public class EmailService {

	 @Autowired
	    private JavaMailSender mailSender;


    /**
     * Envia um e-mail com os dados fornecidos.
     *
     * @param destinatario endereço de e-mail do destinatário
     * @param assunto título do e-mail
     * @param corpo texto do corpo da mensagem
     */
	    public void enviarEmail(String destinatario, String assunto, String corpo) {
	        SimpleMailMessage mensagem = new SimpleMailMessage();
	        mensagem.setTo(destinatario);
	        mensagem.setSubject(assunto);
	        mensagem.setText(corpo);
	        mailSender.send(mensagem);
	    }
}
