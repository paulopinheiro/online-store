package br.com.paulopinheiro.onlinestore.core.menu.impl;

import br.com.paulopinheiro.onlinestore.core.configs.ApplicationContext;
import br.com.paulopinheiro.onlinestore.core.menu.Menu;
import br.com.paulopinheiro.onlinestore.core.services.ProductManagementService;
import br.com.paulopinheiro.onlinestore.core.services.impl.DefaultProductManagementService;
import br.com.paulopinheiro.onlinestore.persistence.entities.Cart;
import br.com.paulopinheiro.onlinestore.persistence.entities.Product;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ProductCatalogMenu implements Menu {

    private static final String CHECKOUT_COMMAND = "checkout";
    private ApplicationContext context;
    private ProductManagementService productManagementService;
    private ResourceBundle rb;

    {
        context = ApplicationContext.getInstance();
        productManagementService = DefaultProductManagementService.getInstance();
        rb = ResourceBundle.getBundle(RESOURCE_BUNDLE_BASE_NAME);
    }

    @Override
    public void start() {
        Menu menuToNavigate = null;
        while (true) {
            printMenuHeader();
            printProductsToConsole();

            String userInput = readUserInput();

            if (context.getLoggedInUser() == null) {
                menuToNavigate = new MainMenu();
                System.out.println(rb.getString("not.logged.in.msg"));
                break;
            }

            if (userInput.equalsIgnoreCase(MainMenu.MENU_COMMAND)) {
                menuToNavigate = new MainMenu();
                break;
            }

            if (userInput.equalsIgnoreCase(CHECKOUT_COMMAND)) {
                Cart sessionCart = context.getSessionCart();
                if (sessionCart == null || sessionCart.isEmpty()) {
                    System.out.println(rb.getString("empty.cart.err.msg"));
                } else {
                    menuToNavigate = new CheckoutMenu();
                    break;
                }
            } else {
                Product productToAddToCart = fetchProduct(userInput);

                if (productToAddToCart == null) {
                    System.out.println(rb.getString("enter.product.id"));
                    continue;
                }

                processAddToCart(productToAddToCart);
            }
        }

        menuToNavigate.start();
    }

    private String readUserInput() {
        System.out.print(rb.getString("proceed.to.checkout"));
        Scanner sc = new Scanner(System.in);
        String userInput = sc.next();
        return userInput;
    }

    private void printProductsToConsole() {
        List<Product> products = productManagementService.getProducts();
        if (products != null) {
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    private Product fetchProduct(String userInput) {
        int productIdToAddToCart = Integer.parseInt(userInput);
        Product productToAddToCart = productManagementService.getProductById(productIdToAddToCart);
        return productToAddToCart;
    }

    private void processAddToCart(Product productToAddToCart) {
        context.getSessionCart().addProduct(productToAddToCart);
        System.out.printf(rb.getString("product.added.to.cart"), productToAddToCart.getProductName());
    }

    @Override
    public void printMenuHeader() {
        System.out.println(rb.getString("product.catalog.header"));
        System.out.println(rb.getString("catalog.cta"));
    }
}
