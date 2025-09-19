package br.com.paulopinheiro.onlinestore.core.services;

import br.com.paulopinheiro.onlinestore.persistence.entities.Purchase;
import java.util.List;

public interface PurchaseManagementService {
    void addPurchase(Purchase purchase);
    List<Purchase> getPurchasesByUserId(int userId);
    List<Purchase> getPurchases();
}
