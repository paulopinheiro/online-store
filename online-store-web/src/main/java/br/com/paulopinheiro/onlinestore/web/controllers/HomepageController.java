package br.com.paulopinheiro.onlinestore.web.controllers;

import br.com.paulopinheiro.onlinestore.core.facades.CategoryFacade;
import br.com.paulopinheiro.onlinestore.persistence.entities.Category;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomepageController {
    @Autowired private CategoryFacade categoryFacade;

    @GetMapping(value = {"/homepage", "/"})
    public String doGet(Model model) {
        List<Category> categories = categoryFacade.getCategories();
        model.addAttribute("categories",categories);
        return "homepage";
    }
}
