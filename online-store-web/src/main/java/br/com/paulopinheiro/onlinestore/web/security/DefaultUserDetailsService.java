package br.com.paulopinheiro.onlinestore.web.security;

import br.com.paulopinheiro.onlinestore.core.facades.RoleFacade;
import br.com.paulopinheiro.onlinestore.core.facades.UserFacade;
import br.com.paulopinheiro.onlinestore.persistence.SetupDataLoader;
import br.com.paulopinheiro.onlinestore.persistence.entities.Privilege;
import br.com.paulopinheiro.onlinestore.persistence.entities.Role;
import br.com.paulopinheiro.onlinestore.persistence.entities.User;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Transactional
public class DefaultUserDetailsService implements UserDetailsService {
    @Autowired private UserFacade userRepository;
    @Autowired private RoleFacade roleRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(email);
        if (Optional.ofNullable(user).isEmpty()) {
            return new org.springframework.security.core.userdetails.User(
                "","",true,true,true,true,
                getAuthorities(Arrays.asList(roleRepository.getRoleByName(SetupDataLoader.ROLE_CUSTOMER))));
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                                                                      user.isEnabled(), true, true, true,
                                                                      getAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(List<Role> roles) {
        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<String> getPrivileges(List<Role> roles) {
        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();

        for (Role role: roles) {
            privileges.add(role.getRoleName());
            collection.addAll(role.getPrivileges());
        }

        for (Privilege item: collection) privileges.add(item.getName());

        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (String privilege: privileges) authorities.add(new SimpleGrantedAuthority(privilege));

        return authorities;
    }
}
