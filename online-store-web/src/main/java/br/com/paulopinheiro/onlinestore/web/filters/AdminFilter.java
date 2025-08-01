package br.com.paulopinheiro.onlinestore.web.filters;

import static br.com.paulopinheiro.onlinestore.persistence.dto.RoleDto.ADMIN_ROLE_NAME;
import br.com.paulopinheiro.onlinestore.persistence.entities.User;
import static br.com.paulopinheiro.onlinestore.web.controllers.SignInServlet.LOGGED_IN_USER_ATTR;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AdminFilter", urlPatterns = {"/admin/*"})
public class AdminFilter extends HttpFilter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        User user = (User) ((HttpServletRequest)request).getSession().getAttribute(LOGGED_IN_USER_ATTR);
        System.out.println(user);

        if (user !=null) {
            if (user.getRoleName().equals(ADMIN_ROLE_NAME)) {
                chain.doFilter(request, response);
            } else {
                ((HttpServletResponse)response).sendError(403);
            } 
        } else {
            ((HttpServletResponse) response).sendRedirect(request.getScheme() + "://"
                                                        + request.getServerName() + ":"
                                                        + request.getServerPort()
                                                        + request.getServletContext().getContextPath() + "/signin.html");
        }
    }
}
