package br.com.paulopinheiro.persistence.entities.impl;

import br.com.paulopinheiro.persistence.annotations.Validate;
import br.com.paulopinheiro.persistence.entities.User;

public class DefaultUser implements User {
    private static int userCounter = 0;

    private int id;
    @Validate(pattern="[a-zA-Z]+")
    private String firstName;
    @Validate(pattern="[a-zA-Z]+")
    private String lastName;
    private String password;
    @Validate(pattern=".+@.+")
    private String email;
    private String roleName;
    private double money;
    private String creditCard;
    {
        id= ++userCounter;
    }

    public DefaultUser() {}

    public DefaultUser(String firstName, String lastName, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
    }

    public DefaultUser(int id, String firstName, String lastName, String password, String email) {
        this.id = id;
        userCounter--; // to keep sequantial id
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
    }

    public DefaultUser(String firstName, String lastName, String password, String email, String creditCard) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.creditCard = creditCard;
    }

    public void clearState() {
        userCounter=0;
    }

    public static void setCounter(int updatedCount) {
        userCounter = updatedCount;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        if (password==null) return;
        this.password = password;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        if (email==null) return;
        this.email = email;
    }

    @Override
    public String getRoleName() {
        return roleName;
    }

    @Override
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public double getMoney() {
        return money;
    }

    @Override
    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String getCreditCard() {
        return creditCard;
    }

    @Override
    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    @Override
    public String toString() {
        return "ID: " + this.getId() + "\t\t"
                + "First Name: " + this.getFirstName() + "\t\t"
                + "Last Name: " + this.getLastName() + "\t\t"
                + "Email: " + this.getEmail();
    }
}
