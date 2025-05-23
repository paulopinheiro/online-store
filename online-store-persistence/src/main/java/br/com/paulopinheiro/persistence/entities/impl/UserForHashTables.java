package br.com.paulopinheiro.persistence.entities.impl;

import br.com.paulopinheiro.persistence.entities.User;
import java.util.Objects;

public class UserForHashTables implements User {
    private static int userCounter=0;

    private int id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String roleName;
    private double money;
    private String creditCard;
    
    {id = ++userCounter;}

    public UserForHashTables() {}

    public UserForHashTables(String firstName, String lastName, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
    }

    public UserForHashTables(int id, String firstName, String lastName, String password, String email) {
        this.id = id;
        userCounter--; // to keep sequantial id
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
    }
	
    @Override
    public String toString() {
        return "ID: " + this.getId() + "\t\t"
                + "First Name: " + this.getFirstName() + "\t\t"
                + "Last Name: " + this.getLastName() + "\t\t"
                + "Email: " + this.getEmail();
    }

    public void clearState() {
        userCounter = 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, firstName, id, lastName, password);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        UserForHashTables other = (UserForHashTables) obj;
        return Objects.equals(email, other.email)
                && Objects.equals(firstName, other.firstName) && Objects.equals(id, other.id)
                && Objects.equals(lastName, other.lastName)
                && Objects.equals(password, other.password);
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
}
