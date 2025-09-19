package br.com.paulopinheiro.onlinestore.web.controllers;

import br.com.paulopinheiro.onlinestore.core.facades.ProductFacade;
import br.com.paulopinheiro.onlinestore.core.facades.impl.DefaultProductFacade;
import br.com.paulopinheiro.onlinestore.persistence.entities.Product;
import br.com.paulopinheiro.onlinestore.web.Configurations;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ProductServlet", urlPatterns = {"/product"})
public class ProductServlet extends HttpServlet {
    private final ProductFacade productFacade = DefaultProductFacade.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Product p = productFacade.getProductById(Integer.valueOf(request.getParameter("id")));
        request.setAttribute("product", p);

        request.getRequestDispatcher(Configurations.VIEWS_PATH_RESOLVER + "pdp.jsp").forward(request, response);
    }
}
