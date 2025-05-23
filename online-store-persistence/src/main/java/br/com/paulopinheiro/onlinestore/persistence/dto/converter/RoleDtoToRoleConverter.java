package br.com.paulopinheiro.onlinestore.persistence.dto.converter;

import br.com.paulopinheiro.onlinestore.persistence.dto.RoleDto;

public class RoleDtoToRoleConverter {

    public RoleDto convertRoleNameToRoleDtoWithOnlyRoleName(String roleName) {
        RoleDto roleDto = new RoleDto();
        roleDto.setRoleName(roleName);
        return roleDto;
    }
}
