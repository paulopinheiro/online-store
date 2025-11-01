package br.com.paulopinheiro.onlinestore.core.services.impl;

import br.com.paulopinheiro.onlinestore.core.services.PurchaseManagementService;
import br.com.paulopinheiro.onlinestore.persistence.dao.PurchaseDao;
import br.com.paulopinheiro.onlinestore.persistence.dto.PurchaseDto;
import br.com.paulopinheiro.onlinestore.persistence.dto.converter.PurchaseDtoToPurchaseConverter;
import br.com.paulopinheiro.onlinestore.persistence.entities.Purchase;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MySqlPurchaseManagementService implements PurchaseManagementService {
    @Autowired private PurchaseDao purchaseDao;
    @Autowired private PurchaseDtoToPurchaseConverter purchaseConverter;

    @Override
    public void addPurchase(Purchase purchase) {
        purchaseDao.savePurchase(purchaseConverter.convertPurchaseToPurchaseDto(purchase));
    }

    @Override
    public List<Purchase> getPurchasesByUserId(int userId) {
        List<PurchaseDto> purchasesDtos = purchaseDao.getPurchasesByUserId(userId);
        return purchaseConverter.convertPurchaseDtosToPurchases(purchasesDtos);
    }

    @Override
    public List<Purchase> getPurchases() {
        List<PurchaseDto> purchasesDtos = purchaseDao.getPurchases();
        return purchaseConverter.convertPurchaseDtosToPurchases(purchasesDtos);
    }
}
