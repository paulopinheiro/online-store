package br.com.paulopinheiro.onlinestore.persistence.dao;

import br.com.paulopinheiro.onlinestore.persistence.dto.PurchaseDto;
import java.util.List;

public interface PurchaseDao {
    void savePurchase(PurchaseDto order);
    List<PurchaseDto> getPurchasesByUserId(int userId);
    List<PurchaseDto> getPurchases();
}
