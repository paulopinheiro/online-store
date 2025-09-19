package br.com.paulopinheiro.onlinestore.persistence.dao;

import br.com.paulopinheiro.onlinestore.persistence.dto.ProductDto;
import java.util.List;

public interface ProductDao {
    List<ProductDto> getProducts();
    ProductDto getProductById(int productId);
    public List<ProductDto> getProductsLikeName(String searchQuery);
    public List<ProductDto> getProductsByCategoryId(Integer id);
    public List<ProductDto> getProductsByCategoryIdPaginationLimit(Integer categoryId, Integer page, Integer paginationLimit);
    public Integer getProductCountForCategory(Integer categoryId);
    public Integer getProductCountForSearch(String searchQuery);
    public List<ProductDto> getProductsLikeNameForPageWithLimit(String searchQuery, Integer page, Integer paginationLimit);
    public ProductDto getProductByGuid(String guid);
}
