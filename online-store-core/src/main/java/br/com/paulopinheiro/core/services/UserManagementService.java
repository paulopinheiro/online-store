package br.com.paulopinheiro.core.services;

import br.com.paulopinheiro.persistence.entities.User;
import java.util.List;

public interface UserManagementService {
    String registerUser(User user);
    List<User> getUsers();
    User getUserByEmail(String userEmail);
}
