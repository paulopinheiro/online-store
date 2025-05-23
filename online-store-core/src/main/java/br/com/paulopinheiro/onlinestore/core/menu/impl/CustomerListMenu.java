package br.com.paulopinheiro.onlinestore.core.menu.impl;

import br.com.paulopinheiro.onlinestore.core.configs.ApplicationContext;
import br.com.paulopinheiro.onlinestore.core.menu.Menu;
import br.com.paulopinheiro.onlinestore.core.services.UserManagementService;
import br.com.paulopinheiro.onlinestore.core.services.impl.DefaultUserManagementService;
import br.com.paulopinheiro.onlinestore.persistence.entities.User;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerListMenu implements Menu {

    private ApplicationContext context;
    private UserManagementService userManagementService;
    private ResourceBundle rb;

    {
        userManagementService = DefaultUserManagementService.getInstance();
        context = ApplicationContext.getInstance();
        rb = ResourceBundle.getBundle(RESOURCE_BUNDLE_BASE_NAME);
    }

    @Override
    public void start() {
        printMenuHeader();
        List<User> users = userManagementService.getUsers();

        if (users == null || users.size() == 0) {
            System.out.println(rb.getString("no.users.msg"));
        } else {
            for (User user : users) {
                System.out.println(user);
            }
        }
        context.getMainMenu().start();
    }

    @Override
    public void printMenuHeader() {
        System.out.println(rb.getString("customer.list.header"));
    }
}
