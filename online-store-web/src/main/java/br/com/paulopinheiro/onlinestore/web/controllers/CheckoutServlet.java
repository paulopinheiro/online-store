package br.com.paulopinheiro.onlinestore.web.controllers;

import br.com.paulopinheiro.onlinestore.core.facades.ProductFacade;
import br.com.paulopinheiro.onlinestore.core.facades.PurchaseFacade;
import br.com.paulopinheiro.onlinestore.core.facades.impl.DefaultProductFacade;
import br.com.paulopinheiro.onlinestore.core.facades.impl.DefaultPurchaseFacade;
import br.com.paulopinheiro.onlinestore.persistence.entities.User;
import br.com.paulopinheiro.onlinestore.web.Configurations;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ResourceBundle;

@WebServlet(name = "CheckoutServlet", urlPatterns = {"/checkout"})
public class CheckoutServlet extends HttpServlet {
    private final PurchaseFacade purchaseFacade = DefaultPurchaseFacade.getInstance();
    private ProductFacade productFacade = DefaultProductFacade.getInstance();
    private ResourceBundle rb = ResourceBundle.getBundle(Configurations.RESOURCE_BUNDLE_BASE_NAME);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productId = request.getParameter("id");
        purchaseFacade.createPurchase((User) request.getSession().getAttribute(SignInServlet.LOGGED_IN_USER_ATTR), productFacade.getProductById(Integer.valueOf(productId)));

        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getServletContext().getContextPath();

        request.getSession().setAttribute("orderStatus", rb.getString("order.created.msg"));
        response.sendRedirect(baseUrl + "/product?id=" + productId);
    }
}
