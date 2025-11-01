package br.com.paulopinheiro.onlinestore.web.filters;

import br.com.paulopinheiro.onlinestore.core.facades.UserFacade;
import br.com.paulopinheiro.onlinestore.persistence.entities.User;
import br.com.paulopinheiro.onlinestore.web.controllers.SignInController;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.support.WebApplicationContextUtils;

@WebFilter("/*")
public class RememberMeFilter implements Filter {
    private UserFacade userFacade;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest)request).getSession();

        User user = (User) session.getAttribute(SignInController.LOGGED_IN_USER_ATTR);
        if (Optional.ofNullable(user).isEmpty()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (Optional.ofNullable(authentication).isPresent()) {
                if (!(authentication instanceof AnonymousAuthenticationToken)) {
                    user = userFacade.getUserByEmail(authentication.getName());
                    if (Optional.ofNullable(user).isPresent()) {
                        session.setAttribute(SignInController.LOGGED_IN_USER_ATTR, user);
                    }
                }
            }
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext());
        this.userFacade = ctx.getBean(UserFacade.class);
    }
}