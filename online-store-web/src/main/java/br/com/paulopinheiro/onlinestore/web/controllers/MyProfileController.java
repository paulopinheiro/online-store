package br.com.paulopinheiro.onlinestore.web.controllers;

import br.com.paulopinheiro.onlinestore.core.facades.UserFacade;
import br.com.paulopinheiro.onlinestore.persistence.entities.User;
import static br.com.paulopinheiro.onlinestore.web.controllers.SignInController.LOGGED_IN_USER_ATTR;
import br.com.paulopinheiro.onlinestore.web.filters.PartnerCodeFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyProfileController {
    @Autowired private UserFacade userFacade;

    @GetMapping("/my-profile")
    public String doGet(HttpSession session, HttpServletRequest request, Model model) {
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
            session.setAttribute(SignInController.LOGGED_IN_USER_ATTR, loggedInUser);

            model.addAttribute("referrals", referrals);
            model.addAttribute("partnerLink", partnerLink);
            return "myProfile";
        } else {
            return "signin";
        }
    }
}
