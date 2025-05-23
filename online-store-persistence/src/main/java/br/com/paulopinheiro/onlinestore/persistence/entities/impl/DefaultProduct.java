package br.com.paulopinheiro.onlinestore.persistence.entities.impl;

import br.com.paulopinheiro.onlinestore.persistence.entities.Product;

public class DefaultProduct implements Product {
    private int id;
    private String productName;
    private String categoryName;
    private double price;

    public DefaultProduct() {}

    public DefaultProduct(int id, String productName, String categoryName, double price) {
        this.id = id;
        this.productName = productName;
        this.categoryName = categoryName;
        this.price = price;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getProductName() {
        return productName;
    }

    @Override
    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String getCategoryName() {
        return categoryName;
    }

    @Override
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", productName=" + productName + ", categoryName=" + categoryName + ", price=" + price + '}';
    }
}
