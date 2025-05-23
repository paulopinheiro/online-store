package br.com.paulopinheiro.persistence.dao;

import br.com.paulopinheiro.persistence.dto.CategoryDto;

public interface CategoryDao {
    CategoryDto getCategoryByCategoryId(int id);
}
