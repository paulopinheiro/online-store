package br.com.paulopinheiro.onlinestore.core.menu.impl;

import br.com.paulopinheiro.onlinestore.core.configs.ApplicationContext;
import br.com.paulopinheiro.onlinestore.core.menu.Menu;
import br.com.paulopinheiro.onlinestore.core.services.PurchaseManagementService;
import br.com.paulopinheiro.onlinestore.core.services.impl.MySqlPurchaseManagementService;
import br.com.paulopinheiro.onlinestore.persistence.entities.Purchase;
import java.util.List;

public class MyOrdersMenu implements Menu {
    private final ApplicationContext context;
    private final PurchaseManagementService purchaseManagementService;

    {
        context = ApplicationContext.getInstance();
        purchaseManagementService = new MySqlPurchaseManagementService();
    }

    @Override
    public void start() {
        printMenuHeader();
        if (context.getLoggedInUser() == null) {
            System.out.println(
                    "Please, log in or create new account to see list of your orders");
            new MainMenu().start();
            return;
        } else {
            printUserOrdersToConsole();
        }
        new MainMenu().start();
    }

    private void printUserOrdersToConsole() {
        List<Purchase> loggedInUserOrders = purchaseManagementService.getPurchasesByUserId(context.getLoggedInUser().getId());
        if (loggedInUserOrders == null || loggedInUserOrders.isEmpty()) {
            System.out.println(
                    "Unfortunately, you don't have any orders yet. "
                    + "Navigate back to main menu to place a new order");
        } else {
            for (Purchase order : loggedInUserOrders) {
                System.out.println(order);
            }
        }
    }

    @Override
    public void printMenuHeader() {
        System.out.println("***** MY ORDERS *****");
    }
}
