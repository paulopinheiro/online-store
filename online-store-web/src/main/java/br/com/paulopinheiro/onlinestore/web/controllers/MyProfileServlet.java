package br.com.paulopinheiro.onlinestore.web.controllers;

import br.com.paulopinheiro.onlinestore.core.facades.UserFacade;
import br.com.paulopinheiro.onlinestore.core.facades.impl.DefaultUserFacade;
import br.com.paulopinheiro.onlinestore.persistence.entities.User;
import br.com.paulopinheiro.onlinestore.web.Configurations;
import static br.com.paulopinheiro.onlinestore.web.controllers.SignInServlet.LOGGED_IN_USER_ATTR;
import br.com.paulopinheiro.onlinestore.web.filters.PartnerCodeFilter;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(urlPatterns = {"/my-profile"})
public class MyProfileServlet extends HttpServlet {
    private final UserFacade userFacade = DefaultUserFacade.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User loggedInUser = (User)request.getSession().getAttribute(LOGGED_IN_USER_ATTR);

        if (loggedInUser != null) {
            String baseUrl = request.getScheme() + "://"
                           + request.getServerName() + ":"
                           + request.getServerPort()
                           + request.getServletContext().getContextPath();
            String partnerLink = baseUrl + "?"
                               + PartnerCodeFilter.PARTNER_CODE_PARAMETER_NAME + "="
                               + loggedInUser.getPartnerCode();

            List<User> referrals = userFacade.getReferralsForUser(loggedInUser);

            loggedInUser = userFacade.getUserById(loggedInUser.getId());
            request.getSession().setAttribute(SignInServlet.LOGGED_IN_USER_ATTR, loggedInUser);

            request.setAttribute("referrals", referrals);
            request.setAttribute("partnerLink", partnerLink);
            request.getRequestDispatcher(Configurations.VIEWS_PATH_RESOLVER + "/myProfile.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher(Configurations.VIEWS_PATH_RESOLVER + "/signin.jsp").forward(request, response);
        }
    }
}
