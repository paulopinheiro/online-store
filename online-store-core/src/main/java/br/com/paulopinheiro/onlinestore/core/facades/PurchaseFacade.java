package br.com.paulopinheiro.onlinestore.core.facades;

import br.com.paulopinheiro.onlinestore.persistence.entities.Product;
import br.com.paulopinheiro.onlinestore.persistence.entities.Purchase;
import br.com.paulopinheiro.onlinestore.persistence.entities.User;
import java.util.List;

public interface PurchaseFacade {
    Integer LAST_STATUS_OF_ORDER_FULFILMENT_ID = 6;
    void createPurchase(User user, Product product);
    List<Purchase> getNotCompletedPurchases();
    void markFulfilmentStageForPurchaseIdAsCompleted(Integer purchaseId);
    Purchase getPurchaseById(Integer purchaseId);
    void updatePurchase(Purchase purchase);
}
