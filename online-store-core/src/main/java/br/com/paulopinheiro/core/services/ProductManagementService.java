package br.com.paulopinheiro.core.services;

import br.com.paulopinheiro.persistence.entities.Product;
import java.util.List;

public interface ProductManagementService {
    List<Product> getProducts();
    Product getProductById(int productIdToAddToCart);
}
