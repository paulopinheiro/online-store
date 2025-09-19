package br.com.paulopinheiro.onlinestore.core.facades.impl;

import br.com.paulopinheiro.onlinestore.core.CoreConfigurations;
import br.com.paulopinheiro.onlinestore.core.facades.PurchaseFacade;
import br.com.paulopinheiro.onlinestore.core.facades.UserFacade;
import br.com.paulopinheiro.onlinestore.persistence.dao.impl.MySqlJdbcPurchaseDao;
import br.com.paulopinheiro.onlinestore.persistence.entities.Product;
import br.com.paulopinheiro.onlinestore.persistence.entities.Purchase;
import br.com.paulopinheiro.onlinestore.persistence.entities.User;
import br.com.paulopinheiro.onlinestore.persistence.dao.PurchaseDao;
import br.com.paulopinheiro.onlinestore.persistence.dto.converter.PurchaseDtoToPurchaseConverter;
import br.com.paulopinheiro.onlinestore.persistence.entities.PurchaseStatus;
import br.com.paulopinheiro.onlinestore.persistence.entities.impl.DefaultPurchase;
import br.com.paulopinheiro.onlinestore.persistence.entities.impl.DefaultPurchaseStatus;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefaultPurchaseFacade implements PurchaseFacade {
    private static DefaultPurchaseFacade instance;

    private final PurchaseDao purchaseDao = new MySqlJdbcPurchaseDao();
    private final PurchaseDtoToPurchaseConverter purchaseConverter = new PurchaseDtoToPurchaseConverter();
    private final UserFacade userFacade = DefaultUserFacade.getInstance();

    public static synchronized DefaultPurchaseFacade getInstance() {
        if (instance==null) instance = new DefaultPurchaseFacade();
        return instance;
    }

    @Override
    public void createPurchase(User user, Product product) {
        Purchase purchase = new DefaultPurchase();
        purchase.setCustomer(user);
        purchase.setProducts(new ArrayList<>(Arrays.asList(product)));

        var purchaseStatus = new DefaultPurchaseStatus();
        purchaseStatus.setId(1);
        purchase.setPurchaseStatus(purchaseStatus);

        purchaseDao.savePurchase(purchaseConverter.convertPurchaseToPurchaseDto(purchase));
    }

    @Override
    public List<Purchase> getNotCompletePurchases() {
        return purchaseConverter.convertPurchaseDtosToPurchases(purchaseDao.getNotCompletedPurchases(LAST_STATUS_OF_ORDER_FULFILMENT_ID));
    }

    @Override
    public void markFulfilmentStageForPurchaseIdAsCompleted(Integer purchaseId) {
        Purchase purchase = purchaseConverter.convertPurchaseDtoToPurchase(purchaseDao.getPurchaseById(purchaseId));
        PurchaseStatus purchaseStatus = purchase.getPurchaseStatus();
        int newPurchaseStatusId = purchaseStatus.getId() + 1;
        purchaseStatus.setId(newPurchaseStatusId);
        purchase.setPurchaseStatus(purchaseStatus);

        purchaseDao.updatePurchaseStatus(purchaseConverter.convertPurchaseToPurchaseDto(purchase));

        if (LAST_STATUS_OF_ORDER_FULFILMENT_ID.equals(newPurchaseStatusId) && purchase.getCustomer().getReferrerUser() != null) {
            User referrerUser = purchase.getCustomer().getReferrerUser();
            double shareFromPurchase = purchase.getTotalPurchaseCost() * CoreConfigurations.REFERRER_REWARD_RATE;
            referrerUser.setMoney(referrerUser.getMoney() + shareFromPurchase);
            userFacade.updateUser(referrerUser);
        }
    }

    @Override
    public Purchase getPurchaseById(Integer purchaseId) {
        return purchaseConverter.convertPurchaseDtoToPurchase(purchaseDao.getPurchaseById(purchaseId));
    }

    @Override
    public void updatePurchase(Purchase purchase) {
        purchaseDao.updatePurchase(purchaseConverter.convertPurchaseToPurchaseDto(purchase));
    }
}
