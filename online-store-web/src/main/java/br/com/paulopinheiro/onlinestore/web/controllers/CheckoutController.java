package br.com.paulopinheiro.onlinestore.web.controllers;

import br.com.paulopinheiro.onlinestore.core.facades.ProductFacade;
import br.com.paulopinheiro.onlinestore.core.facades.PurchaseFacade;
import br.com.paulopinheiro.onlinestore.persistence.entities.User;
import jakarta.servlet.http.HttpSession;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CheckoutController {
    @Autowired private PurchaseFacade purchaseFacade;
    @Autowired private ProductFacade productFacade;
    @Autowired private MessageSource messageSource;

    @GetMapping("/checkout")
    public String doGet(@RequestParam("guid") String productGuid, HttpSession session) {
        purchaseFacade.createPurchase(
                (User) session.getAttribute(SignInController.LOGGED_IN_USER_ATTR), productFacade.getProductByGuid(productGuid));

        session.setAttribute("orderStatus", messageSource.getMessage("order.created.msg", null, Locale.getDefault()));
        return "redirect:/product?guid=" + productGuid;
    }
}
