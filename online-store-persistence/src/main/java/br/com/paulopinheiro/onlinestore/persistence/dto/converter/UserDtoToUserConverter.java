package br.com.paulopinheiro.onlinestore.persistence.dto.converter;

import br.com.paulopinheiro.onlinestore.persistence.dto.UserDto;
import br.com.paulopinheiro.onlinestore.persistence.entities.User;
import br.com.paulopinheiro.onlinestore.persistence.entities.impl.DefaultUser;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDtoToUserConverter {
    @Autowired private RoleDtoToRoleConverter roleConverter;

    public UserDto convertUserIdToUserDtoWithOnlyId(int customerId) {
        UserDto userDto = new UserDto();

        userDto.setId(customerId);

        return userDto;
    }

    public User convertUserDtoToUser(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        User user = new DefaultUser();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setRoles(roleConverter.convertRoleDtosToRoles(userDto.getRoles()));
        if (Optional.ofNullable(userDto.getMoney()).isPresent())
            user.setMoney(userDto.getMoney().doubleValue());
        user.setCreditCard(userDto.getCreditCard());
        user.setPartnerCode(userDto.getPartnerCode());
        user.setReferrerUser(convertUserDtoToUser(userDto.getReferrerUser()));
        user.setEnabled(userDto.isEnabled());

        return user;
    }

    public UserDto convertUserToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPassword(user.getPassword());
        userDto.setRoles(roleConverter.convertRolesToRoleDtos(user.getRoles()));
        userDto.setMoney(BigDecimal.valueOf(user.getMoney()));
        userDto.setCreditCard(user.getCreditCard());
        userDto.setPartnerCode(user.getPartnerCode());
        userDto.setEnabled(user.isEnabled());

        return userDto;
    }

    public List<User> convertUserDtosToUsers(List<UserDto> userDtos) {
        List<User> users = new ArrayList<>();

        for (UserDto userDto : userDtos) {
            users.add(convertUserDtoToUser(userDto));
        }
        return users;
    }

    public List<UserDto> convertUsersToUserDtos(List<User> users) {
        List<UserDto> userDtos = new ArrayList<>();

        for (User user: users) userDtos.add(convertUserToUserDto(user));

        return userDtos;
    }
}
