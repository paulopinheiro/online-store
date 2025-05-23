package br.com.paulopinheiro.onlinestore.persistence.dao;

import br.com.paulopinheiro.onlinestore.persistence.dto.CategoryDto;

public interface CategoryDao {
    CategoryDto getCategoryByCategoryId(int id);
}
