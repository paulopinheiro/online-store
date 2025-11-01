package br.com.paulopinheiro.onlinestore.persistence.dto;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.util.List;

@Entity(name="role")
public class RoleDto implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name="role_name")
    private String roleName;

    @ManyToMany(mappedBy="roles")
    private List<UserDto> users;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="roles_privileges",
               joinColumns= @JoinColumn(name="role_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="privilege_id", referencedColumnName="id")
              )
    private List<PrivilegeDto> privileges;

    public RoleDto() {}

    public RoleDto(String roleName) {
        this.roleName = roleName;
    }

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

    public List<UserDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }

    public List<PrivilegeDto> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<PrivilegeDto> privileges) {
        this.privileges = privileges;
    }
}
