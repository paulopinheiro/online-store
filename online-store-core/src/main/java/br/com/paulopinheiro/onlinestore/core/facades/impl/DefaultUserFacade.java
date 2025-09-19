package br.com.paulopinheiro.onlinestore.core.facades.impl;

import br.com.paulopinheiro.onlinestore.core.facades.UserFacade;
import br.com.paulopinheiro.onlinestore.core.services.AffiliateMarketingService;
import br.com.paulopinheiro.onlinestore.core.services.impl.DefaultAffiliateMarketingService;
import br.com.paulopinheiro.onlinestore.persistence.dao.UserDao;
import br.com.paulopinheiro.onlinestore.persistence.dao.impl.MySqlJdbcUserDao;
import static br.com.paulopinheiro.onlinestore.persistence.dto.RoleDto.CUSTOMER_ROLE_NAME;
import br.com.paulopinheiro.onlinestore.persistence.dto.converter.UserDtoToUserConverter;
import br.com.paulopinheiro.onlinestore.persistence.entities.User;
import java.util.List;

public class DefaultUserFacade implements UserFacade {
    private static DefaultUserFacade instance;

    private final UserDao userDao = new MySqlJdbcUserDao();
    private final UserDtoToUserConverter userConverter = new UserDtoToUserConverter();
    private final AffiliateMarketingService marketingService = new DefaultAffiliateMarketingService();

    public static synchronized DefaultUserFacade getInstance() {
        if (instance == null) {
            instance = new DefaultUserFacade();
        }

        return instance;
    }

    @Override
    public void registerUser(User user, String referrerCode) {
        user.setRoleName(CUSTOMER_ROLE_NAME);
        user.setPartnerCode(marketingService.generateUniquePartnerCode());
        user.setReferrerUser(userConverter.convertUserDtoToUser(userDao.getUserByPartnerCode(referrerCode)));
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
