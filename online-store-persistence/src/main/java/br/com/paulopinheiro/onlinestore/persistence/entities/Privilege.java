package br.com.paulopinheiro.onlinestore.persistence.entities;

import java.util.List;

public interface Privilege {
    Integer getId();
    void setId(Integer id);

    String getName();
    void setName(String privilegeName);

    List<Role> getRoles();
    void setRoles(List<Role> roles);
}
