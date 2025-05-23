package br.com.paulopinheiro.core.services;

import br.com.paulopinheiro.persistence.entities.User;

public interface ResetPasswordService {
    void resetPasswordForUser(User user);
}
