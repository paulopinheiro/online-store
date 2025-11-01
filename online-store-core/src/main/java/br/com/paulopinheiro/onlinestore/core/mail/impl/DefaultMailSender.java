package br.com.paulopinheiro.onlinestore.core.mail.impl;

import br.com.paulopinheiro.onlinestore.core.mail.MailSender;
import org.springframework.stereotype.Service;

@Service
public class DefaultMailSender implements MailSender {
    @Override
    public void sendEmail(String sendTo, String messageToSend) {
        // sending email here
    }
}
