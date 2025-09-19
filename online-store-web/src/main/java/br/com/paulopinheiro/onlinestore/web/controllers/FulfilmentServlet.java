package br.com.paulopinheiro.onlinestore.web.controllers;

import br.com.paulopinheiro.onlinestore.core.facades.PurchaseFacade;
import br.com.paulopinheiro.onlinestore.core.facades.impl.DefaultPurchaseFacade;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "fulfilment", urlPatterns = {"/management-fulfilment"})
public class FulfilmentServlet extends HttpServlet {
    private final PurchaseFacade purchaseFacade = DefaultPurchaseFacade.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        purchaseFacade.markFulfilmentStageForPurchaseIdAsCompleted(Integer.valueOf(request.getParameter("purchaseId")));

        String baseUrl = request.getScheme() + "://"
                       + request.getServerName() + ":"
                       + request.getServerPort()
                       + request.getServletContext().getContextPath();
        response.sendRedirect(baseUrl + "/management-orders");
    }
}
