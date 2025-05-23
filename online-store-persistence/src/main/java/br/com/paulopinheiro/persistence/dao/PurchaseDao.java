package br.com.paulopinheiro.persistence.dao;

import br.com.paulopinheiro.persistence.dto.PurchaseDto;
import java.util.List;

public interface PurchaseDao {
    void savePurchase(PurchaseDto order);
    List<PurchaseDto> getPurchasesByUserId(int userId);
    List<PurchaseDto> getPurchases();
}
