package br.com.paulopinheiro.onlinestore.web.filters;

import static br.com.paulopinheiro.onlinestore.persistence.dto.RoleDto.MANAGER_ROLE_NAME;
import br.com.paulopinheiro.onlinestore.persistence.entities.User;
import static br.com.paulopinheiro.onlinestore.web.controllers.SignInServlet.LOGGED_IN_USER_ATTR;
import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(servletNames = {"purchase","fulfilment"})
public class ManagementFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        User user = (User) ((HttpServletRequest)request).getSession().getAttribute(LOGGED_IN_USER_ATTR);

        if (user!=null) {
            if (user.getRoleName().equals(MANAGER_ROLE_NAME)) {
                chain.doFilter(request, response);
            } else {
                ((HttpServletResponse)response).sendError(403);
            }
        } else {
            ((HttpServletResponse)response).sendRedirect(request.getScheme() 
                                                       + "://"
                                                       + request.getServerName()
                                                       + ":"
                                                       + request.getServerPort()
                                                       + request.getServletContext().getContextPath() + "/signin");
        }
    }
}
