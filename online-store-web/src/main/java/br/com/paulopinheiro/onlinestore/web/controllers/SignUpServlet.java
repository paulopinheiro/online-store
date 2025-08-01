package br.com.paulopinheiro.onlinestore.web.controllers;

import br.com.paulopinheiro.onlinestore.core.helpdesk.facades.UserFacade;
import br.com.paulopinheiro.onlinestore.core.helpdesk.facades.impl.DefaultUserFacade;
import br.com.paulopinheiro.onlinestore.persistence.entities.User;
import br.com.paulopinheiro.onlinestore.persistence.entities.impl.DefaultUser;
import static br.com.paulopinheiro.onlinestore.web.filters.PartnerCodeFilter.PARTNER_CODE_COOKIE_NAME;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SignUpServlet", urlPatterns = {"/signup"})
public class SignUpServlet extends HttpServlet {
    private final UserFacade userFacade = DefaultUserFacade.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String baseUrl = request.getScheme() + "://"
                       + request.getServerName() + ":"
                       + request.getServerPort()
                       + request.getServletContext().getContextPath();

        User user = new DefaultUser();
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));

        String partnerCode = null;
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(PARTNER_CODE_COOKIE_NAME)) {
                    partnerCode = cookie.getValue();
                }
            }
        }
        userFacade.registerUser(user, partnerCode);
        response.sendRedirect(baseUrl + "/signin.html");
    }

    @Override
    public String getServletInfo() {
        return "This servlet is used to sign the user up";
    }// </editor-fold>

}
