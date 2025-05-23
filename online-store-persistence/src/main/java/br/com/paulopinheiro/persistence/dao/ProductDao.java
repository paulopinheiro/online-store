package br.com.paulopinheiro.persistence.dao;

import br.com.paulopinheiro.persistence.dto.ProductDto;
import java.util.List;

public interface ProductDao {
    List<ProductDto> getProducts();
    ProductDto getProductById(int productId);
}
