package br.com.paulopinheiro.onlinestore.persistence.dao;

import br.com.paulopinheiro.onlinestore.persistence.dto.PurchaseStatusDto;

public interface PurchaseStatusDao {
    PurchaseStatusDto getPurchaseStatusById(Integer id);
}
