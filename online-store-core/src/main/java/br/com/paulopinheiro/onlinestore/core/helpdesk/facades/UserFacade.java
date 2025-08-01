package br.com.paulopinheiro.onlinestore.core.helpdesk.facades;

import br.com.paulopinheiro.onlinestore.persistence.entities.User;

public interface UserFacade {
    void registerUser(User user, String partnerCode);
    User getUserByEmail(String email);
}
