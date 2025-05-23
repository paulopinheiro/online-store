package br.com.paulopinheiro.core.storage;

import br.com.paulopinheiro.persistence.entities.User;
import java.util.List;

public interface UserStoringService {
    void saveUser(User user);
    List<User> loadUsers();
}
