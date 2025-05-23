package br.com.paulopinheiro.persistence.dto.converter;

import br.com.paulopinheiro.persistence.dto.RoleDto;

public class RoleDtoToRoleConverter {

    public RoleDto convertRoleNameToRoleDtoWithOnlyRoleName(String roleName) {
        RoleDto roleDto = new RoleDto();
        roleDto.setRoleName(roleName);
        return roleDto;
    }
}
