package br.com.paulopinheiro.onlinestore.core.menu.impl;

import br.com.paulopinheiro.onlinestore.core.configs.ApplicationContext;
import br.com.paulopinheiro.onlinestore.core.menu.Menu;
import java.util.ResourceBundle;

public class SignOutMenu implements Menu {

    private ApplicationContext context;
    private ResourceBundle rb;

    {
        context = ApplicationContext.getInstance();
        rb = ResourceBundle.getBundle(RESOURCE_BUNDLE_BASE_NAME);
    }

    @Override
    public void start() {
        printMenuHeader();
        context.setLoggedInUser(null);
        context.getMainMenu().start();
    }

    @Override
    public void printMenuHeader() {
        System.out.println(rb.getString("sign.out.header"));
        System.out.println(rb.getString("bye.msg"));
    }
}
