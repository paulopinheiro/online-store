package br.com.paulopinheiro.core;

import br.com.paulopinheiro.core.menu.Menu;
import br.com.paulopinheiro.core.menu.impl.MainMenu;

public class Main {

    public static final String EXIT_COMMAND = "exit";

    public static void main(String[] args) {
        Menu mainMenu = new MainMenu();
        mainMenu.start();
    }
}
