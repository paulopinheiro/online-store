package br.com.paulopinheiro.persistence.dao;

import br.com.paulopinheiro.persistence.dto.UserDto;
import java.util.List;

public interface UserDao {
    boolean saveUser(UserDto user);
    List<UserDto> getUsers();
    UserDto getUserByEmail(String userEmail);
    UserDto getUserById(int id);
}
