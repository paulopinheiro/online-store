package br.com.paulopinheiro.onlinestore.core.facades;

import br.com.paulopinheiro.onlinestore.persistence.entities.User;
import java.util.List;

public interface UserFacade {
    void registerUser(User user, String partnerCode);
    User getUserByEmail(String email);
    List<User> getUsers();
    User getUserById(Integer userId);
    void updateUser(User referrerUser);
    List<User> getReferralsForUser(User loggedInUser);
}
