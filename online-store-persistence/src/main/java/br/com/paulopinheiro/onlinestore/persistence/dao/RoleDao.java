package br.com.paulopinheiro.onlinestore.persistence.dao;

import br.com.paulopinheiro.onlinestore.persistence.dto.RoleDto;

public interface RoleDao {
    RoleDto getRoleById(int id);
    RoleDto getRoleByRoleName(String roleName);
}
