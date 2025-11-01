package br.com.paulopinheiro.onlinestore.persistence.dao.impl;

import br.com.paulopinheiro.onlinestore.persistence.dao.PrivilegeDao;
import br.com.paulopinheiro.onlinestore.persistence.dto.PrivilegeDto;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class JpaPrivilegeDao implements PrivilegeDao {
    @Override
    public void save(PrivilegeDto privilege) {
        try (
            var emf = Persistence.createEntityManagerFactory("persistence-unit");
            var em = emf.createEntityManager()
        ) {
            em.getTransaction().begin();

            em.persist(privilege);

            em.getTransaction().commit();
        }
    }

    @Override
    public PrivilegeDto getPrivilegeByName(String privilegeName) {
        try (
            var emf = Persistence.createEntityManagerFactory("persistence-unit");
            var em = emf.createEntityManager()
        ) {
            em.getTransaction().begin();

            TypedQuery<PrivilegeDto> query = em.createQuery("SELECT p FROM privilege p WHERE p.name = :privilegeName", PrivilegeDto.class);
            query.setParameter("privilegeName", privilegeName);
            PrivilegeDto privilege = query.getResultList().stream().findFirst().orElse(null);

            em.getTransaction().commit();

            return privilege;
        }
    }
}