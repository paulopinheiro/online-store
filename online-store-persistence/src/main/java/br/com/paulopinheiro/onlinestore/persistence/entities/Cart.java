package br.com.paulopinheiro.onlinestore.persistence.entities;

import java.util.List;

public interface Cart {
    boolean isEmpty();
    void addProduct(Product productById);
    List<Product> getProducts();
    void clear();
}
