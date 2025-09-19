package br.com.paulopinheiro.onlinestore.persistence.entities.impl;

import br.com.paulopinheiro.onlinestore.persistence.entities.Product;
import br.com.paulopinheiro.onlinestore.persistence.entities.Purchase;
import br.com.paulopinheiro.onlinestore.persistence.entities.PurchaseStatus;
import br.com.paulopinheiro.onlinestore.persistence.entities.User;
import java.util.ArrayList;
import java.util.List;

public class DefaultPurchase implements Purchase {
    private static final int AMOUNT_OF_DIGITS_IN_CREDIT_CARD_NUMBER = 16;

    private Integer id;
    private String creditCardNumber;
    private List<Product> products;
    private User customer;
    private PurchaseStatus purchaseStatus;

    @Override
    public boolean isCreditCardNumberValid(String creditCardNumber) {
        return creditCardNumber.toCharArray().length==AMOUNT_OF_DIGITS_IN_CREDIT_CARD_NUMBER &&
               !creditCardNumber.contains(" ") && Long.parseLong(creditCardNumber) > 0;
    }

    @Override
    public void setCreditCardNumber(String creditCardNumber) {
        if (creditCardNumber == null) {
            return;
        }
        this.creditCardNumber = creditCardNumber;
    }

    @Override
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public List<Product> getProducts() {
        ArrayList<Product> copy = new ArrayList<>(this.products);
        return copy;
    }

    @Override
    public void setCustomer(User customer) {
        this.customer = customer;
    }

    @Override
    public User getCustomer() {
        return this.customer;
    }

    @Override
    public String toString() {
        return "DefaultPurchase [creditCardNumber=" + creditCardNumber + ", products=" + products + ", customer="
				+ customer + ", purchaseStatus=" + purchaseStatus + "]";
    }

    @Override
    public PurchaseStatus getPurchaseStatus() {
        return purchaseStatus;
    }

    @Override
    public void setPurchaseStatus(PurchaseStatus purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public double getTotalPurchaseCost() {
        return products.stream().mapToDouble(product -> product.getPrice()).sum();
    }
}
