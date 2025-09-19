package br.com.paulopinheiro.onlinestore.web.controllers;

import br.com.paulopinheiro.onlinestore.core.facades.PurchaseFacade;
import br.com.paulopinheiro.onlinestore.core.facades.impl.DefaultPurchaseFacade;
import br.com.paulopinheiro.onlinestore.persistence.entities.Purchase;
import br.com.paulopinheiro.onlinestore.web.Configurations;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "purchase", urlPatterns = {"/purchase"})
public class PurchaseServlet extends HttpServlet {
    private final PurchaseFacade purchaseFacade = DefaultPurchaseFacade.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Purchase> purchases = purchaseFacade.getNotCompletePurchases();
        request.setAttribute("purchases", purchases);
        request.getRequestDispatcher(Configurations.VIEWS_PATH_RESOLVER + "orders.jsp").forward(request, response);
    }
}
