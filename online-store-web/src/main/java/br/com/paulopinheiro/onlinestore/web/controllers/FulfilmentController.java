package br.com.paulopinheiro.onlinestore.web.controllers;

import br.com.paulopinheiro.onlinestore.core.facades.PurchaseFacade;
import java.io.IOException;
import jakarta.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FulfilmentController {
    @Autowired private PurchaseFacade purchaseFacade;

    @PostMapping("/management-fulfilment")
    public String doPost(@RequestParam Integer purchaseId) {
        purchaseFacade.markFulfilmentStageForPurchaseIdAsCompleted(purchaseId);

       return "redirect:/management-orders";
    }
}
