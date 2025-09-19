package br.com.paulopinheiro.onlinestore.core.facades;

import br.com.paulopinheiro.onlinestore.persistence.entities.Product;
import java.util.List;

public interface ProductFacade {
    List<Product> getProductsLikeName(String searchQuery);
    List<Product> getProductsByCategoryId(Integer id);
    List<Product> getProductsByCategoryIdForPageWithLimit(Integer categoryId, Integer page, Integer paginationLimit);
    Integer getNumberOfPagesForCategory(Integer categoryId, Integer paginationLimit);
    Integer getNumberOfPagesForSearch(String searchQuery, Integer paginationLimit);
    List<Product> getProductsLikeNameForPageWithLimit(String searchQuery, Integer page, Integer paginationLimit);
    Product getProductById(Integer parameter);
    Product getProductByGuid(String guid);
}
