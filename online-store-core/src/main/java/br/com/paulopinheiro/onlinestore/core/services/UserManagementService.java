package br.com.paulopinheiro.onlinestore.core.services;

import br.com.paulopinheiro.onlinestore.persistence.entities.User;
import java.util.List;

public interface UserManagementService {
    String registerUser(User user);
    List<User> getUsers();
    User getUserByEmail(String userEmail);
}
