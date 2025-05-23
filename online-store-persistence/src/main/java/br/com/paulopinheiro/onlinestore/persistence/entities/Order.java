package br.com.paulopinheiro.onlinestore.persistence.entities;

import java.io.Serializable;
import java.util.List;

public interface Order extends Serializable {
    boolean isCreditCardNumberValid(String userInput);
    void setCreditCardNumber(String userInput);
    void setProducts(List<Product> products);
    void setCustomerId(int customerId);
    int getCustomerId();
}
