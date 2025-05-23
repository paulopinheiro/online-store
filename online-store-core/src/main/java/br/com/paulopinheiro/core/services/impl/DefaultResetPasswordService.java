package br.com.paulopinheiro.core.services.impl;

import br.com.paulopinheiro.core.services.ResetPasswordService;
import br.com.paulopinheiro.persistence.entities.User;
import br.com.paulopinheiro.persistence.utils.mail.MailSender;
import br.com.paulopinheiro.persistence.utils.mail.impl.DefaultMailSender;

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
