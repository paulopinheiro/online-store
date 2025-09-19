package br.com.paulopinheiro.onlinestore.web.controllers;

import br.com.paulopinheiro.onlinestore.core.facades.UserFacade;
import br.com.paulopinheiro.onlinestore.core.facades.impl.DefaultUserFacade;
import br.com.paulopinheiro.onlinestore.core.services.Validator;
import br.com.paulopinheiro.onlinestore.core.services.impl.PasswordValidator;
import br.com.paulopinheiro.onlinestore.persistence.entities.User;
import br.com.paulopinheiro.onlinestore.web.Configurations;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ResourceBundle;

@WebServlet(urlPatterns = {"/edit-profile"})
public class EditProfileServlet extends HttpServlet {
    private final UserFacade userFacade = DefaultUserFacade.getInstance();
    private final Validator passValidator = PasswordValidator.getInstance();
    private final ResourceBundle rb = ResourceBundle.getBundle(Configurations.RESOURCE_BUNDLE_BASE_NAME);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User loggedInUser = (User)request.getSession().getAttribute(SignInServlet.LOGGED_IN_USER_ATTR);

        if (loggedInUser == null) {
            String baseUrl = request.getScheme() + "://"
		           + request.getServerName() + ":"
                           + request.getServerPort()
			   + request.getServletContext().getContextPath();
            response.sendRedirect(baseUrl + "signin");
        } else {
            request.getRequestDispatcher(Configurations.VIEWS_PATH_RESOLVER + "editProfile.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String baseUrl = request.getScheme() + "://"
                       + request.getServerName() + ":"
                       + request.getServerPort()
                       + request.getServletContext().getContextPath();

        User loggedInUser = (User)request.getSession().getAttribute(SignInServlet.LOGGED_IN_USER_ATTR);

        User user = userFacade.getUserById(loggedInUser.getId());
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        String emailParameter = request.getParameter("email");
        user.setEmail(emailParameter);
        String newPasswordParameter = request.getParameter("newPassword");
        if (newPasswordParameter != null && !newPasswordParameter.isEmpty()) user.setPassword(newPasswordParameter);

        User userByEmail = userFacade.getUserByEmail(user.getEmail());

        if (userByEmail != null && !emailParameter.equals(loggedInUser.getEmail())) {
            request.getSession().setAttribute("errMsg", rb.getString("signup.err.msg.email.exists"));
            response.sendRedirect(baseUrl + "/edit-profile");
            return;
        }

        if (!request.getParameter("password").equals(loggedInUser.getPassword())) {
            request.getSession().setAttribute("errMsg", rb.getString("signup.err.msg.old.password.wrong"));
            response.sendRedirect(baseUrl + "/edit-profile");
            return;
        }

        if (newPasswordParameter != null && !newPasswordParameter.isEmpty() && !passValidator.isValid(user.getPassword())) {
            request.getSession().setAttribute("errMsg", rb.getString("signup.err.msg.special.character"));
            response.sendRedirect(baseUrl + "/edit-profile");
            return;
        }

        userFacade.updateUser(user);
        response.sendRedirect(baseUrl + "/my-profile");
    }
}
