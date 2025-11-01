package br.com.paulopinheiro.onlinestore.persistence.dao.impl;

import br.com.paulopinheiro.onlinestore.persistence.dao.RoleDao;
import br.com.paulopinheiro.onlinestore.persistence.dto.RoleDto;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
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
        try (
            var emf = Persistence.createEntityManagerFactory("persistence-unit");
            var em = emf.createEntityManager();
        ) {
            em.getTransaction().begin();

            TypedQuery<RoleDto> query = em.createQuery("SELECT r FROM role r WHERE r.roleName = :roleName", RoleDto.class);

            query.setParameter("roleName", roleName);
            RoleDto role = query.getResultList().stream().findFirst().orElse(null);

            em.getTransaction().commit();

            return role;
        }
    }

    @Override
    public void save(RoleDto role) {
        try (
            var emf = Persistence.createEntityManagerFactory("persistence-unit");
            var em = emf.createEntityManager();
        ) {
            em.getTransaction().begin();

            em.persist(role);

            em.getTransaction().commit();
        }
    }
}
