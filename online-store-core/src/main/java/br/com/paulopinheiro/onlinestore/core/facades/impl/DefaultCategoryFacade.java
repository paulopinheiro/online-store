package br.com.paulopinheiro.onlinestore.core.facades.impl;

import br.com.paulopinheiro.onlinestore.core.facades.CategoryFacade;
import br.com.paulopinheiro.onlinestore.persistence.dao.CategoryDao;
import br.com.paulopinheiro.onlinestore.persistence.dao.impl.JpaCategoryDao;
import br.com.paulopinheiro.onlinestore.persistence.dto.converter.CategoryDtoToCategoryConverter;
import br.com.paulopinheiro.onlinestore.persistence.entities.Category;
import java.util.List;

public class DefaultCategoryFacade implements CategoryFacade {
    private static DefaultCategoryFacade instance;
    private final CategoryDao categoryDao = new JpaCategoryDao();
    private final CategoryDtoToCategoryConverter categoryConverter = new CategoryDtoToCategoryConverter();

    public static synchronized DefaultCategoryFacade getInstance() {
        if (instance==null) {
            instance = new DefaultCategoryFacade();
        }
        return instance;
    }

    @Override
    public List<Category> getCategories() {
        return categoryConverter.convertCategoryDtosToCategories(categoryDao.getCategories());
    }
}
