package br.com.paulopinheiro.onlinestore.core;

import br.com.paulopinheiro.onlinestore.core.menu.Menu;
import br.com.paulopinheiro.onlinestore.core.menu.impl.MainMenu;

public class Main {

    public static final String EXIT_COMMAND = "exit";

    public static void main(String[] args) {
        Menu mainMenu = new MainMenu();
        mainMenu.start();
    }
}
