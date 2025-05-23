package br.com.paulopinheiro.onlinestore.persistence.dto.converter;

import br.com.paulopinheiro.onlinestore.persistence.dto.CategoryDto;

public class CategoryDtoToCategoryConverter {
    public CategoryDto convertCategoryNameToCategoryDtoWithOnlyName(String categoryName) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryName(categoryName);
        return categoryDto;
    }
}
