package br.com.paulopinheiro.onlinestore.persistence.entities;

import java.io.Serializable;

public interface Product extends Serializable {
    int getId();
    void setId(int id);
    String getProductName();
    void setProductName(String productName);
    String getCategoryName();
    void setCategoryName(String categoryName);
    double getPrice();
    void setPrice(double price);
}
