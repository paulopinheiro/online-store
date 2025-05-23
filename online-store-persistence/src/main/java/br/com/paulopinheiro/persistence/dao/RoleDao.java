package br.com.paulopinheiro.persistence.dao;

import br.com.paulopinheiro.persistence.dto.RoleDto;

public interface RoleDao {
    RoleDto getRoleById(int id);
}
