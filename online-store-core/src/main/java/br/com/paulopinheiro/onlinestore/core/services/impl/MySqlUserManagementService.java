package br.com.paulopinheiro.onlinestore.core.services.impl;

import br.com.paulopinheiro.onlinestore.core.services.UserManagementService;
import br.com.paulopinheiro.onlinestore.persistence.dao.UserDao;
import br.com.paulopinheiro.onlinestore.persistence.dao.impl.MySqlJdbcUserDao;
import br.com.paulopinheiro.onlinestore.persistence.dto.converter.UserDtoToUserConverter;
import br.com.paulopinheiro.onlinestore.core.mail.MailSender;
import br.com.paulopinheiro.onlinestore.core.mail.impl.DefaultMailSender;
import br.com.paulopinheiro.onlinestore.persistence.dto.UserDto;
import br.com.paulopinheiro.onlinestore.persistence.entities.User;
import java.util.List;

public class MySqlUserManagementService implements UserManagementService {
    public static final String SUCCESSFULL_REGISTRATION_MESSAGE = "User is registered!";
    private static final String REGISTRATION_ERROR_MESSAGE = "The email is already in use by other user.";

    private final UserDao userDao;
    private final UserDtoToUserConverter userConverter;
    private final MailSender mailSender;

    {
        userDao = new MySqlJdbcUserDao();
        userConverter = new UserDtoToUserConverter();
        mailSender = DefaultMailSender.getInstance();
    }

    @Override
    public String registerUser(User user) {
        boolean isCreated = userDao.saveUser(userConverter.convertUserToUserDto(user));

        if (isCreated) return SUCCESSFULL_REGISTRATION_MESSAGE;
        else return REGISTRATION_ERROR_MESSAGE;
    }

    @Override
    public List<User> getUsers() {
        List<UserDto> userDtos = userDao.getUsers();
        return userConverter.convertUserDtosToUsers(userDtos);
    }

    @Override
    public User getUserByEmail(String userEmail) {
        UserDto userDto = userDao.getUserByEmail(userEmail);
        return userConverter.convertUserDtoToUser(userDto);
    }

    @Override
    public void resetPasswordForUser(User user) {
        mailSender.sendEmail(user.getEmail(), "Please use this password to login: " + user.getPassword());
    }
}
