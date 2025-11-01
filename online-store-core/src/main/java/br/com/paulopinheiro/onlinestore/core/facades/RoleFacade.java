package br.com.paulopinheiro.onlinestore.core.facades;

import br.com.paulopinheiro.onlinestore.persistence.entities.Role;

public interface RoleFacade {
    Role getRoleByName(String roleName);
}
