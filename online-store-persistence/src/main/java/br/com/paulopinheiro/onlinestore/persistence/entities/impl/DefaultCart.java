package br.com.paulopinheiro.onlinestore.persistence.entities.impl;

import br.com.paulopinheiro.onlinestore.persistence.entities.Cart;
import br.com.paulopinheiro.onlinestore.persistence.entities.Product;
import java.util.ArrayList;
import java.util.List;

public class DefaultCart implements Cart {
    private final List<Product> products;

    {
        products = new ArrayList<>();
    }

    @Override
    public boolean isEmpty() {
        return products.isEmpty();
    }

    @Override
    public void addProduct(Product product) {
        if (product==null) return;
        products.add(product);
    }

    @Override
    public List<Product> getProducts() {
        return this.products;
    }

    @Override
    public void clear() {
        products.clear();
    }
}
