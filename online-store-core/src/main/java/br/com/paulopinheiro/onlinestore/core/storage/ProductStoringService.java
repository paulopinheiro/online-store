package br.com.paulopinheiro.onlinestore.core.storage;

import br.com.paulopinheiro.onlinestore.persistence.entities.Product;
import java.util.List;

public interface ProductStoringService {
    List<Product> loadProducts();
}
