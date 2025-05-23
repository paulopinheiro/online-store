package br.com.paulopinheiro.onlinestore.core.services;

import br.com.paulopinheiro.onlinestore.persistence.entities.User;

public interface ResetPasswordService {
    void resetPasswordForUser(User user);
}
