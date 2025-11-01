package br.com.paulopinheiro.onlinestore.web.controllers;

import br.com.paulopinheiro.onlinestore.core.facades.PurchaseFacade;
import br.com.paulopinheiro.onlinestore.persistence.entities.Purchase;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PurchaseController {
    @Autowired private PurchaseFacade purchaseFacade;

    @GetMapping("/management-orders")
    public String doGet(Model model) {
        List<Purchase> purchases = purchaseFacade.getNotCompletedPurchases();
        model.addAttribute("purchases", purchases);
        return "orders";
    }
}
