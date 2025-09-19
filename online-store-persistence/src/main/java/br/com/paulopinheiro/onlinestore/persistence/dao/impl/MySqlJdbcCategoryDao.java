package br.com.paulopinheiro.onlinestore.persistence.dao.impl;

import br.com.paulopinheiro.onlinestore.persistence.dao.CategoryDao;
import br.com.paulopinheiro.onlinestore.persistence.dto.CategoryDto;
import br.com.paulopinheiro.onlinestore.persistence.utils.DBUtils;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlJdbcCategoryDao implements CategoryDao {

    @Override
    public CategoryDto getCategoryByCategoryId(int id) {
        try (var conn = DBUtils.getConnection(); var ps = conn.prepareStatement("SELECT * FROM category WHERE id = ?")) {

            ps.setInt(1, id);

            try (var rs = ps.executeQuery()) {
                if (rs.next()) {
                    CategoryDto category = new CategoryDto();
                    category.setId(rs.getInt("id"));
                    category.setCategoryName(rs.getString("category_name"));
                    return category;
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CategoryDto> getCategories() {
        try (var conn = DBUtils.getConnection();
             var ps = conn.prepareStatement("SELECT * FROM category");
             var rs = ps.executeQuery()) {

            List<CategoryDto> categories = new ArrayList<>();

            while (rs.next()) {
                CategoryDto category = new CategoryDto();
                category.setId(rs.getInt("id"));
                category.setCategoryName(rs.getString("category_name"));
                category.setImgName(rs.getString("img_name"));
            }

            return categories;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
