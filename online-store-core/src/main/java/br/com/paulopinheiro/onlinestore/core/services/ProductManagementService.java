package br.com.paulopinheiro.onlinestore.core.services;

import br.com.paulopinheiro.onlinestore.persistence.entities.Product;
import java.util.List;

public interface ProductManagementService {
    List<Product> getProducts();
    Product getProductById(int productIdToAddToCart);
}
