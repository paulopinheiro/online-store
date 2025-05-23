package br.com.paulopinheiro.onlinestore.core.menu.impl;

import br.com.paulopinheiro.onlinestore.core.configs.ApplicationContext;
import br.com.paulopinheiro.onlinestore.core.menu.Menu;
import br.com.paulopinheiro.onlinestore.core.services.OrderManagementService;
import br.com.paulopinheiro.onlinestore.core.services.impl.DefaultOrderManagementService;
import br.com.paulopinheiro.onlinestore.persistence.entities.Order;
import java.util.List;

public class MyOrdersMenu implements Menu {

    private ApplicationContext context;
    private OrderManagementService orderManagementService;

    {
        context = ApplicationContext.getInstance();
        orderManagementService = DefaultOrderManagementService.getInstance();
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
        List<Order> loggedInUserOrders = orderManagementService
                .getOrdersByUserId(context.getLoggedInUser().getId());
        if (loggedInUserOrders == null || loggedInUserOrders.size() == 0) {
            System.out.println(
                    "Unfortunately, you don't have any orders yet. "
                    + "Navigate back to main menu to place a new order");
        } else {
            for (Order order : loggedInUserOrders) {
                System.out.println(order);
            }
        }
    }

    @Override
    public void printMenuHeader() {
        System.out.println("***** MY ORDERS *****");
    }
}
