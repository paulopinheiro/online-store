package br.com.paulopinheiro.onlinestore.core.storage;

import br.com.paulopinheiro.onlinestore.persistence.entities.User;
import java.util.List;

public interface UserStoringService {
    void saveUser(User user);
    List<User> loadUsers();
}
