package br.com.paulopinheiro.onlinestore.persistence.utils.mail.impl;

import br.com.paulopinheiro.onlinestore.persistence.utils.mail.MailSender;

public class DefaultMailSender implements MailSender {

    private static DefaultMailSender instance;

    private DefaultMailSender() {
    }

    ;
	
	
	public static DefaultMailSender getInstance() {
        if (instance == null) {
            instance = new DefaultMailSender();
        }
        return instance;
    }

    @Override
    public void sendEmail(String sendTo, String messageToSend) {
        // sending email here
    }
}
