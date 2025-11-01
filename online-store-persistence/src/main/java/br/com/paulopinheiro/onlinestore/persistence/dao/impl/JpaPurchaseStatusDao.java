package br.com.paulopinheiro.onlinestore.persistence.dao.impl;

import br.com.paulopinheiro.onlinestore.persistence.dao.PurchaseStatusDao;
import br.com.paulopinheiro.onlinestore.persistence.dto.PurchaseStatusDto;
import jakarta.persistence.Persistence;
import org.springframework.stereotype.Repository;

@Repository
public class JpaPurchaseStatusDao implements PurchaseStatusDao {
    @Override
    public PurchaseStatusDto getPurchaseStatusById(Integer id) {
        try(
            var emf = Persistence.createEntityManagerFactory("persistence-unit");
            var em = emf.createEntityManager();
        ) {
            em.getTransaction().begin();

            PurchaseStatusDto purchaseStatus = em.find(PurchaseStatusDto.class, id);

            em.getTransaction().commit();

            return purchaseStatus;
        }
    }
}
