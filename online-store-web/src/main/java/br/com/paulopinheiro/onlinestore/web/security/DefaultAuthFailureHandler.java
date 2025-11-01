package br.com.paulopinheiro.onlinestore.web.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;import java.util.logging.Logger;

public class DefaultAuthFailureHandler implements AuthenticationFailureHandler {
    private static final int LOGIN_FAILURE_ATTEMPTS_LIMIT = 3;
    public static final String UNSUCCESSFUL_LOGIN_COUNT_ATTR_KEY = "UNSUCCESSFUL_LOGIN_COUNT";
    private static final Logger LOGGER = Logger.getLogger("DefaultAuthFailureHandler");

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String contextPath = request.getServletContext().getContextPath();

        Integer failedLoginCounter = (Integer) session.getAttribute(UNSUCCESSFUL_LOGIN_COUNT_ATTR_KEY);
        session.setAttribute(UNSUCCESSFUL_LOGIN_COUNT_ATTR_KEY, Optional.ofNullable(failedLoginCounter).isEmpty() ? 1 : ++failedLoginCounter);
        LOGGER.log(Level.WARNING, "Usuccessful login attempt {0}", (Integer) session.getAttribute(UNSUCCESSFUL_LOGIN_COUNT_ATTR_KEY));
        response.sendRedirect(contextPath + "/signin");
    }
}
