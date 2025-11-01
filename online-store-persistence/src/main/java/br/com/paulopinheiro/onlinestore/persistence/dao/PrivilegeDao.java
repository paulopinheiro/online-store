package br.com.paulopinheiro.onlinestore.persistence.dao;

import br.com.paulopinheiro.onlinestore.persistence.dto.PrivilegeDto;

public interface PrivilegeDao {
    void save(PrivilegeDto privilege);
    PrivilegeDto getPrivilegeByName(String privilegeName);
}
