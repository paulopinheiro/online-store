package br.com.paulopinheiro.onlinestore.core.facades.impl;

import br.com.paulopinheiro.onlinestore.core.facades.RoleFacade;
import br.com.paulopinheiro.onlinestore.persistence.dao.RoleDao;
import br.com.paulopinheiro.onlinestore.persistence.dto.converter.RoleDtoToRoleConverter;
import br.com.paulopinheiro.onlinestore.persistence.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultRoleFacade implements RoleFacade {
    @Autowired private RoleDao roleDao;
    @Autowired private RoleDtoToRoleConverter roleConverter;

    @Override
    public Role getRoleByName(String roleName) {
        return roleConverter.convertRoleDtoToRole(roleDao.getRoleByRoleName(roleName));
    }
}