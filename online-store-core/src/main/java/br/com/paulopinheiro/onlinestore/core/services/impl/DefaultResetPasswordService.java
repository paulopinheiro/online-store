package br.com.paulopinheiro.onlinestore.core.services.impl;

import br.com.paulopinheiro.onlinestore.core.services.ResetPasswordService;
import br.com.paulopinheiro.onlinestore.persistence.entities.User;
import br.com.paulopinheiro.onlinestore.persistence.utils.mail.MailSender;
import br.com.paulopinheiro.onlinestore.persistence.utils.mail.impl.DefaultMailSender;

public class DefaultResetPasswordService implements ResetPasswordService {
    private MailSender mailSender;

    {
        mailSender = DefaultMailSender.getInstance();
    }

    @Override
    public void resetPasswordForUser(User user) {
        mailSender.sendEmail(user.getEmail(), "Please, use this password to login: " + user.getPassword());
    }
}
