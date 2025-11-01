package br.com.paulopinheiro.onlinestore.persistence.dto.converter;

import br.com.paulopinheiro.onlinestore.persistence.dto.PrivilegeDto;
import br.com.paulopinheiro.onlinestore.persistence.entities.Privilege;
import br.com.paulopinheiro.onlinestore.persistence.entities.impl.DefaultPrivilege;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PrivilegeDtoToPrivilegeConverter {
    public List<Privilege> convertPrivilegeDtosToPrivileges(List<PrivilegeDto> privilegeDtos) {
        List<Privilege> privileges = new ArrayList<>();

        for (PrivilegeDto privilegeDto: privilegeDtos)
            privileges.add(convertPrivilegeDtoToPrivilege(privilegeDto));

        return privileges;
    }

    private Privilege convertPrivilegeDtoToPrivilege(PrivilegeDto privilegeDto) {
        Privilege p = new DefaultPrivilege();

        p.setId(privilegeDto.getId());
        p.setName(privilegeDto.getName());

        return p;
    }
}
