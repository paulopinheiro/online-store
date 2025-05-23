package br.com.paulopinheiro.core.menu.impl;

import br.com.paulopinheiro.core.menu.Menu;
import br.com.paulopinheiro.core.services.ResetPasswordService;
import br.com.paulopinheiro.core.services.UserManagementService;
import br.com.paulopinheiro.core.services.impl.DefaultResetPasswordService;
import br.com.paulopinheiro.core.services.impl.DefaultUserManagementService;
import br.com.paulopinheiro.persistence.entities.User;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

public class ResetPasswordMenu implements Menu {

    private ResetPasswordService resetPasswordService;
    private UserManagementService userManagementService;
    private ResourceBundle rb;

    {
        resetPasswordService = new DefaultResetPasswordService();
        userManagementService = DefaultUserManagementService.getInstance();
        rb = ResourceBundle.getBundle(RESOURCE_BUNDLE_BASE_NAME);
    }

    @Override
    public void start() {
        printMenuHeader();
        Scanner sc = new Scanner(System.in);
        String userInput = sc.next();
        System.out.println(rb.getString("pass.sent.to.email"));
        CompletableFuture.runAsync(() -> {
            User user = userManagementService.getUserByEmail(userInput);
            resetPasswordService.resetPasswordForUser(user);
        });
        new MainMenu().start();
    }

    @Override
    public void printMenuHeader() {
        System.out.println(rb.getString("reset.pass.header"));
        System.out.print(rb.getString("enter.your.email.msg"));
    }
}
