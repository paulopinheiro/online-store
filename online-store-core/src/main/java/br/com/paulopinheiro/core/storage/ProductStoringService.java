package br.com.paulopinheiro.core.storage;

import br.com.paulopinheiro.persistence.entities.Product;
import java.util.List;

public interface ProductStoringService {
    List<Product> loadProducts();
}
