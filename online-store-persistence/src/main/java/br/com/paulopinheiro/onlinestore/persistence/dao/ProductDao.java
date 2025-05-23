package br.com.paulopinheiro.onlinestore.persistence.dao;

import br.com.paulopinheiro.onlinestore.persistence.dto.ProductDto;
import java.util.List;

public interface ProductDao {
    List<ProductDto> getProducts();
    ProductDto getProductById(int productId);
}
