package br.com.paulopinheiro.onlinestore.core.facades.impl;

import br.com.paulopinheiro.onlinestore.core.facades.CategoryFacade;
import br.com.paulopinheiro.onlinestore.persistence.dao.CategoryDao;
import br.com.paulopinheiro.onlinestore.persistence.dto.converter.CategoryDtoToCategoryConverter;
import br.com.paulopinheiro.onlinestore.persistence.entities.Category;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultCategoryFacade implements CategoryFacade {
    @Autowired private CategoryDao categoryDao;
    @Autowired private CategoryDtoToCategoryConverter categoryConverter;

    @Override
    public List<Category> getCategories() {
        return categoryConverter.convertCategoryDtosToCategories(categoryDao.getCategories());
    }
}
