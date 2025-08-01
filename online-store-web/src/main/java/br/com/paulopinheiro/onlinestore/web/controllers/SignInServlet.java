package br.com.paulopinheiro.onlinestore.web.controllers;

import br.com.paulopinheiro.onlinestore.core.helpdesk.facades.UserFacade;
import br.com.paulopinheiro.onlinestore.core.helpdesk.facades.impl.DefaultUserFacade;
import static br.com.paulopinheiro.onlinestore.persistence.dto.RoleDto.ADMIN_ROLE_NAME;
import br.com.paulopinheiro.onlinestore.persistence.entities.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SignInServlet", urlPatterns = {"/signin"})
public class SignInServlet extends HttpServlet {
    public static final String LOGGED_IN_USER_ATTR = "loggedInUser";
    private final UserFacade userFacade;
    {
        userFacade = DefaultUserFacade.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

       User user = userFacade.getUserByEmail(request.getParameter("email"));

       String baseUrl = request.getScheme() + "://"
                      + request.getServerName() + ":"
                      + request.getServerPort()
                      + request.getServletContext().getContextPath();

       if (user != null && user.getPassword().equals(request.getParameter("password"))) {
           request.getSession().setAttribute(LOGGED_IN_USER_ATTR, user);

           if (user.getRoleName().equals(ADMIN_ROLE_NAME)) {
               response.sendRedirect(baseUrl + "/admin/panel");
           } else {
               response.sendRedirect(baseUrl + "/homepage.html");
           }
       } else {
           response.sendRedirect(baseUrl + "signin.html");
       }
    }

    @Override
    public String getServletInfo() {
        return "This servlet is used to sign a user in";
    }// </editor-fold>

}
