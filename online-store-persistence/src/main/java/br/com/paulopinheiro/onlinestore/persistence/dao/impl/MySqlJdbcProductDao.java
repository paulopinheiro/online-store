package br.com.paulopinheiro.onlinestore.persistence.dao.impl;

import br.com.paulopinheiro.onlinestore.persistence.dao.CategoryDao;
import br.com.paulopinheiro.onlinestore.persistence.dao.ProductDao;
import br.com.paulopinheiro.onlinestore.persistence.dto.ProductDto;
import br.com.paulopinheiro.onlinestore.persistence.utils.db.DBUtils;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlJdbcProductDao implements ProductDao {

    private final CategoryDao categoryDao;

    {
        categoryDao = new MySqlJdbcCategoryDao();
    }

    @Override
    public List<ProductDto> getProducts() {
        try (var conn = DBUtils.getConnection(); var ps = conn.prepareStatement("SELECT * FROM product"); var rs = ps.executeQuery()) {
            List<ProductDto> products = new ArrayList<>();

            while (rs.next()) {
                ProductDto product = new ProductDto();
                product.setId(rs.getInt("id"));
                product.setProductName(rs.getString("product_name"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setCategoryDto(categoryDao.getCategoryByCategoryId(rs.getInt("category_id")));
                products.add(product);
            }

            return products;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ProductDto getProductById(int productId) {
        try (var conn = DBUtils.getConnection(); var ps = conn.prepareStatement("SELECT * FROM product WHERE id = ?")) {

            ps.setInt(1, productId);
            try (var rs = ps.executeQuery()) {

                if (rs.next()) {
                    ProductDto product = new ProductDto();
                    product.setId(rs.getInt("id"));
                    product.setProductName(rs.getString("product_name"));
                    product.setPrice(rs.getBigDecimal("price"));
                    product.setCategoryDto(categoryDao.getCategoryByCategoryId(rs.getInt("category_id")));
                    return product;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
