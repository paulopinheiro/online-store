package br.com.paulopinheiro.onlinestore.core.facades.impl;

import br.com.paulopinheiro.onlinestore.core.facades.UserFacade;
import br.com.paulopinheiro.onlinestore.core.services.AffiliateMarketingService;
import br.com.paulopinheiro.onlinestore.persistence.SetupDataLoader;
import br.com.paulopinheiro.onlinestore.persistence.dao.RoleDao;
import br.com.paulopinheiro.onlinestore.persistence.dao.UserDao;
import br.com.paulopinheiro.onlinestore.persistence.dto.converter.RoleDtoToRoleConverter;
import br.com.paulopinheiro.onlinestore.persistence.dto.converter.UserDtoToUserConverter;
import br.com.paulopinheiro.onlinestore.persistence.entities.User;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserFacade implements UserFacade {
    @Autowired private UserDao userDao;
    @Autowired private RoleDao roleDao;
    @Autowired private UserDtoToUserConverter userConverter;
    @Autowired private RoleDtoToRoleConverter roleConverter;
    @Autowired private AffiliateMarketingService marketingService;

    @Override
    public void registerUser(User user, String referrerCode) {
        user.setPartnerCode(marketingService.generateUniquePartnerCode());
        user.setReferrerUser(userConverter.convertUserDtoToUser(userDao.getUserByPartnerCode(referrerCode)));
        System.out.println(roleDao.getRoleByRoleName(SetupDataLoader.ROLE_CUSTOMER));
        user.setRoles(roleConverter.convertRoleDtosToRoles(Arrays.asList(roleDao.getRoleByRoleName(SetupDataLoader.ROLE_CUSTOMER))));
        userDao.saveUser(userConverter.convertUserToUserDto(user));
    }

    @Override
    public User getUserByEmail(String email) {
        return userConverter.convertUserDtoToUser(userDao.getUserByEmail(email));
    }

    @Override
    public List<User> getUsers() {
        return userConverter.convertUserDtosToUsers(userDao.getUsers());
    }

    @Override
    public User getUserById(Integer userId) {
        return userConverter.convertUserDtoToUser(userDao.getUserById(userId));
    }

    @Override
    public void updateUser(User referrerUser) {
        userDao.updateUser(userConverter.convertUserToUserDto(referrerUser));
    }

    @Override
    public List<User> getReferralsForUser(User loggedInUser) {
        return userConverter.convertUserDtosToUsers(userDao.getReferralsByUserId(loggedInUser.getId()));
    }
}
