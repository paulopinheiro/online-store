package br.com.paulopinheiro.core.menu.impl;

import br.com.paulopinheiro.core.configs.ApplicationContext;
import br.com.paulopinheiro.core.menu.Menu;
import br.com.paulopinheiro.core.services.UserManagementService;
import br.com.paulopinheiro.core.services.impl.DefaultUserManagementService;
import br.com.paulopinheiro.persistence.entities.User;
import br.com.paulopinheiro.persistence.entities.impl.DefaultUser;
import java.util.ResourceBundle;
import java.util.Scanner;

public class SignUpMenu implements Menu {

    private UserManagementService userManagementService;
    private ApplicationContext context;
    private ResourceBundle rb;

    {
        userManagementService = DefaultUserManagementService.getInstance();
        context = ApplicationContext.getInstance();
        rb = ResourceBundle.getBundle(RESOURCE_BUNDLE_BASE_NAME);
    }

    @Override
    public void start() {
        printMenuHeader();

        Scanner sc = new Scanner(System.in);
        System.out.print(rb.getString("enter.your.first.name"));
        String firstName = sc.next();
        System.out.print(rb.getString("enter.your.last.name"));
        String lastName = sc.next();
        System.out.print(rb.getString("enter.your.pass"));
        String password = sc.next();
        System.out.print(rb.getString("enter.your.email"));

        sc = new Scanner(System.in);
        String email = sc.nextLine();

        userManagementService.getUsers(); // this is needed to load all users for proper ID generation
        User user = new DefaultUser(firstName, lastName, password, email);

        String errorMessage = userManagementService.registerUser(user);
        if (errorMessage == null || errorMessage.isEmpty()) {
            context.setLoggedInUser(user);
            System.out.println(rb.getString("user.created.msg"));
        } else {
            System.out.println(errorMessage);
        }

        context.getMainMenu().start();
    }

    @Override
    public void printMenuHeader() {
        System.out.println(rb.getString("sign.up.header"));
    }
}
