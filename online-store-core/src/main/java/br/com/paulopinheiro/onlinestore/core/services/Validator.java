package br.com.paulopinheiro.onlinestore.core.services;

import java.util.List;

public interface Validator {
    boolean isValid(String stringValue);
    List<String> validate(String password);
}
