package br.com.paulopinheiro.onlinestore.core.facades;

import br.com.paulopinheiro.onlinestore.persistence.entities.Category;
import java.util.List;

public interface CategoryFacade {
    List<Category> getCategories();
}
