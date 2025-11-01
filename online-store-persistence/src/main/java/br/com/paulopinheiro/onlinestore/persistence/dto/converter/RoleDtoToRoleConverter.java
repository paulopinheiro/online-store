package br.com.paulopinheiro.onlinestore.persistence.dto.converter;

import br.com.paulopinheiro.onlinestore.persistence.dto.RoleDto;
import br.com.paulopinheiro.onlinestore.persistence.entities.Role;
import br.com.paulopinheiro.onlinestore.persistence.entities.impl.DefaultRole;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleDtoToRoleConverter {
    @Autowired PrivilegeDtoToPrivilegeConverter privilegeConverter;

    public RoleDto convertRoleNameToRoleDtoWithOnlyRoleName(String roleName) {
        RoleDto roleDto = new RoleDto();
        roleDto.setRoleName(roleName);
        return roleDto;
    }

    public Role convertRoleDtoToRole(RoleDto roleDto) {
        Role r = new DefaultRole();

        r.setRoleName(roleDto.getRoleName());
        r.setId(roleDto.getId());
        r.setPrivileges(privilegeConverter.convertPrivilegeDtosToPrivileges(roleDto.getPrivileges()));

        return r;
    }

    public List<Role> convertRoleDtosToRoles(List<RoleDto> roleDtos) {
        List<Role> roles = new ArrayList<>();

        for (RoleDto roleDto:roleDtos) {
            if(Optional.ofNullable(roleDto).isPresent())
                roles.add(convertRoleDtoToRole(roleDto));
        }

        return roles;
    }

    public List<RoleDto> convertRolesToRoleDtos(List<Role> roles) {
        List<RoleDto> roleDtos = new ArrayList<>();

        for (Role role: roles) {
            if (Optional.ofNullable(role).isPresent())
                roleDtos.add(convertRoleToRoleDto(role));
        }

        return roleDtos;
    }

    private RoleDto convertRoleToRoleDto(Role role) {
        RoleDto roleDto = new RoleDto();

        roleDto.setId(role.getId());
        roleDto.setRoleName(role.getRoleName());

        return roleDto;
    }    
}
