package br.com.paulopinheiro.onlinestore.core.services.impl;

import br.com.paulopinheiro.onlinestore.core.services.ProductManagementService;
import br.com.paulopinheiro.onlinestore.persistence.dao.ProductDao;
import br.com.paulopinheiro.onlinestore.persistence.dao.impl.MySqlJdbcProductDao;
import br.com.paulopinheiro.onlinestore.persistence.dto.ProductDto;
import br.com.paulopinheiro.onlinestore.persistence.dto.converter.ProductDtoToProductConverter;
import br.com.paulopinheiro.onlinestore.persistence.entities.Product;
import java.util.List;

public class MySqlProductManagementService implements ProductManagementService {
    private final ProductDao productDao;
    private final ProductDtoToProductConverter productConverter;

    {
        productDao = new MySqlJdbcProductDao();
        productConverter = new ProductDtoToProductConverter();
    }

    @Override
    public List<Product> getProducts() {
        List<ProductDto> productsDtos = productDao.getProducts();
        return productConverter.convertProductDtosToProducts(productsDtos);
    }

    @Override
    public Product getProductById(int id) {
        ProductDto productDto = productDao.getProductById(id);
        return productConverter.convertProductDtoToProduct(productDto);
    }
}
