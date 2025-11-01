package br.com.paulopinheiro.onlinestore.persistence.entities.impl;

import br.com.paulopinheiro.onlinestore.persistence.entities.Privilege;
import br.com.paulopinheiro.onlinestore.persistence.entities.Role;
import br.com.paulopinheiro.onlinestore.persistence.entities.User;
import java.util.List;

public class DefaultRole implements Role {
    private Integer id;
    private String roleName;
    private List<User> users;
    private List<Privilege> privileges;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getRoleName() {
        return roleName;
    }

    @Override
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public List<Privilege> getPrivileges() {
        return privileges;
    }

    @Override
    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }

    @Override
    public String toString() {
        return roleName;
    }
}
