package br.com.paulopinheiro.onlinestore.persistence.dto;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="role")
public class RoleDto implements Serializable {
    public static final String ADMIN_ROLE_NAME = "ROLE_ADMIN";
    public static final String MANAGER_ROLE_NAME = "ROLE_MANAGER";
    public static final String CUSTOMER_ROLE_NAME = "ROLE_CUSTOMER";

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @Column(name="role_name")
    private String roleName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
