package br.com.paulopinheiro.onlinestore.core.menu.impl;

import br.com.paulopinheiro.onlinestore.core.configs.ApplicationContext;
import br.com.paulopinheiro.onlinestore.core.menu.Menu;
import br.com.paulopinheiro.onlinestore.core.services.UserManagementService;
import br.com.paulopinheiro.onlinestore.core.services.impl.MySqlUserManagementService;
import br.com.paulopinheiro.onlinestore.persistence.entities.User;
import java.util.ResourceBundle;
import java.util.Scanner;

public class SignInMenu implements Menu {
    private final ApplicationContext context;
    private final UserManagementService userManagementService;
    private final ResourceBundle rb;

    {
        context = ApplicationContext.getInstance();
        userManagementService = new MySqlUserManagementService();
        rb = ResourceBundle.getBundle(RESOURCE_BUNDLE_BASE_NAME);
    }

    @Override
    public void start() {
        printMenuHeader();
        Scanner sc = new Scanner(System.in);

        System.out.print(rb.getString("please.enter.email"));
        String userEmail = sc.next();

        System.out.print(rb.getString("please.enter.pass"));
        String userPassword = sc.next();

        User user = userManagementService.getUserByEmail(userEmail);
        if (user != null && user.getPassword().equals(userPassword)) {
            System.out.printf(rb.getString("glad.to.see.you.back"), user.getFirstName(),
                    user.getLastName() + System.lineSeparator());
            context.setLoggedInUser(user);
        } else {
            System.out.println(rb.getString("login.and.password.not.exist"));
        }
        context.getMainMenu().start();
    }

    @Override
    public void printMenuHeader() {
        System.out.println(rb.getString("sign.in.header"));
    }
}
