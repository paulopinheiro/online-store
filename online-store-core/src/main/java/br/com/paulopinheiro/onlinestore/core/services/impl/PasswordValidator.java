package br.com.paulopinheiro.onlinestore.core.services.impl;

import br.com.paulopinheiro.onlinestore.core.services.Validator;

public class PasswordValidator implements Validator {
    private static PasswordValidator instance;

    @Override
    public boolean isValid(String password) {
        if (password.length() < 8) {
            return false;
        }

        String specialCharactersString = " !\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (specialCharactersString.contains(Character.toString(ch))) {
                return true;
            }
        }

        return false;
    }

    public static synchronized PasswordValidator getInstance() {
        if (instance == null) {
            instance = new PasswordValidator();
        }
        return instance;
    }
}
