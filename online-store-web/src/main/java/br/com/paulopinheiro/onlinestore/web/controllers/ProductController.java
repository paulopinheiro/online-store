package br.com.paulopinheiro.onlinestore.web.controllers;

import br.com.paulopinheiro.onlinestore.core.facades.ProductFacade;
import br.com.paulopinheiro.onlinestore.persistence.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {
    @Autowired private ProductFacade productFacade;

    @GetMapping("/product")
    public String doGet(@RequestParam String guid, Model model) {
        Product p = productFacade.getProductByGuid(guid);
        model.addAttribute("product", p);

        return "pdp";
    }
}
