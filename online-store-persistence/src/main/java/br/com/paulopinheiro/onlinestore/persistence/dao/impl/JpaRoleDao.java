package br.com.paulopinheiro.onlinestore.persistence.dao.impl;

import br.com.paulopinheiro.onlinestore.persistence.dao.RoleDao;
import br.com.paulopinheiro.onlinestore.persistence.dto.RoleDto;
import jakarta.persistence.Persistence;

public class JpaRoleDao implements RoleDao {
    @Override
    public RoleDto getRoleById(int id) {
        try (
            var emf = Persistence.createEntityManagerFactory("persistence-unit");
            var em = emf.createEntityManager();
        ) {
            em.getTransaction().begin();

            RoleDto role = em.find(RoleDto.class, id);

            em.getTransaction().commit();

            return role;
        }
    }

    @Override
    public RoleDto getRoleByRoleName(String roleName) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
