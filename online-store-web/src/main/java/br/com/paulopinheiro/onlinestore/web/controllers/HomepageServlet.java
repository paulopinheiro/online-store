package br.com.paulopinheiro.onlinestore.web.controllers;

import br.com.paulopinheiro.onlinestore.core.facades.CategoryFacade;
import br.com.paulopinheiro.onlinestore.core.facades.impl.DefaultCategoryFacade;
import br.com.paulopinheiro.onlinestore.persistence.entities.Category;
import br.com.paulopinheiro.onlinestore.web.Configurations;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "HomepageServlet", urlPatterns = {"/homepage"})
public class HomepageServlet extends HttpServlet {
    private CategoryFacade categoryFacade = DefaultCategoryFacade.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Category> categories = categoryFacade.getCategories();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher(Configurations.VIEWS_PATH_RESOLVER + "homepage.jsp").forward(request, response);
    }
}
