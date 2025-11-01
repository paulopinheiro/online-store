package br.com.paulopinheiro.onlinestore.persistence.entities.impl;

import br.com.paulopinheiro.onlinestore.persistence.entities.Role;
import br.com.paulopinheiro.onlinestore.persistence.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.List;

public class DefaultUser implements User {
    private static int userCounter = 0;

    private int id;
    @NotEmpty(message = "First Name should not be empty")
    @Size(min = 3, max = 20, message = "First Name should be between 3 and 20 characters" )
    private String firstName;
    @NotEmpty(message = "Last Name should not be empty")
    @Size(min = 3, max = 20, message = "Last Name should be between 3 and 20 characters")
    private String lastName;
    private String password;
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Please, use real email")
    private String email;
    private String repeatPassword;
    private double money;
    private String creditCard;
    private String partnerCode;
    private User referrerUser;
    private List<Role> roles;
    private boolean enabled;

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
    public String getPartnerCode() {
        return this.partnerCode;
    }

    @Override
    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    @Override
    public void setReferrerUser(User referrerUser) {
        this.referrerUser = referrerUser;
    }

    @Override
    public User getReferrerUser() {
        return this.referrerUser;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    @Override
    public List<Role> getRoles() {
        return roles;
    }

    @Override
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean isEnabled) {
        this.enabled = isEnabled;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.id;
        return hash;
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
        final DefaultUser other = (DefaultUser) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "ID: " + this.getId() + "\t\t"
                + "First Name: " + this.getFirstName() + "\t\t"
                + "Last Name: " + this.getLastName() + "\t\t"
                + "Email: " + this.getEmail();
    }
}