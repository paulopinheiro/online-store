package br.com.paulopinheiro.onlinestore.persistence.dao;

import br.com.paulopinheiro.onlinestore.persistence.dto.CategoryDto;
import java.util.List;

public interface CategoryDao {
    CategoryDto getCategoryByCategoryId(int id);

    List<CategoryDto> getCategories();
}
