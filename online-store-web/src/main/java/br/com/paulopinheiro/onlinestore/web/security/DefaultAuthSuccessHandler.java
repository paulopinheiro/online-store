package br.com.paulopinheiro.onlinestore.web.security;

import br.com.paulopinheiro.onlinestore.core.facades.UserFacade;
import br.com.paulopinheiro.onlinestore.persistence.SetupDataLoader;
import br.com.paulopinheiro.onlinestore.persistence.entities.User;
import static br.com.paulopinheiro.onlinestore.web.controllers.SignInController.LOGGED_IN_USER_ATTR;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class DefaultAuthSuccessHandler implements AuthenticationSuccessHandler {
    private static final Logger LOGGER = Logger.getLogger("DefaultAuthFailureHandler");

    @Autowired private UserFacade userFacade;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User user = userFacade.getUserByEmail(authentication.getName());
        HttpSession session = request.getSession();
        String contextPath = request.getServletContext().getContextPath();
        LOGGER.info("Session is requested");

        if (Optional.ofNullable(user).isPresent()) {
            session.setAttribute(LOGGED_IN_USER_ATTR, user);
            session.setAttribute(DefaultAuthFailureHandler.UNSUCCESSFUL_LOGIN_COUNT_ATTR_KEY, null);
            LOGGER.log(Level.INFO, "User with ID {0} is added to the session", user.getId());

           if (user.getRoles().stream().map(role -> role.getRoleName()).collect(Collectors.toList()).contains(SetupDataLoader.ROLE_ADMIN)) {
               LOGGER.log(Level.INFO, "User with ID {0} is redirected to the admin panel", user.getId());
               response.sendRedirect(contextPath + "/admin/panel");
           } else {
               LOGGER.log(Level.INFO, "User with ID {0} is redirected to the homepage", user.getId());
               response.sendRedirect(contextPath + "/homepage");
           }
        }
    }
}
