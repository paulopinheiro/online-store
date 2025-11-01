package br.com.paulopinheiro.onlinestore.web.controllers;

import br.com.paulopinheiro.onlinestore.core.facades.UserFacade;
import br.com.paulopinheiro.onlinestore.core.services.Validator;
import br.com.paulopinheiro.onlinestore.core.services.impl.CorePasswordValidator;
import br.com.paulopinheiro.onlinestore.persistence.entities.Role;
import br.com.paulopinheiro.onlinestore.persistence.entities.User;
import br.com.paulopinheiro.onlinestore.persistence.entities.impl.DefaultUser;
import static br.com.paulopinheiro.onlinestore.web.filters.PartnerCodeFilter.PARTNER_CODE_COOKIE_NAME;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignUpController {
    private static final Logger LOGGER = Logger.getLogger("SignUpController");

    @Autowired private UserFacade userFacade;
    @Autowired private Validator passValidator;
    @Autowired private MessageSource messageSource;
    @Autowired private PasswordEncoder passwordEncoder;

    @GetMapping
    public String doGet(Model model) {
        model.addAttribute("user", new DefaultUser());
        return "signup";
    }

    @PostMapping
    public String doPost(@Valid @ModelAttribute("user") DefaultUser user,
                         BindingResult br, HttpSession session,
                         HttpServletRequest request,
                         @CookieValue(value=PARTNER_CODE_COOKIE_NAME, defaultValue="null") String partnerCodeCookie) {
        String notEncryptedPassword = user.getPassword();

        User userByEmail = userFacade.getUserByEmail(user.getEmail());

        if (Optional.ofNullable(userByEmail).isPresent()) {
            session.setAttribute("errMsg", messageSource.getMessage("signup.err.msg.email.exists", null, Locale.getDefault()));
            LOGGER.log(Level.WARNING,"Registration is failed. user with such email {0} already exists.", user.getEmail());
            return "redirect:/signup";
        }

        if (!notEncryptedPassword.equals(user.getRepeatPassword())) {
            session.setAttribute("errMsg", messageSource.getMessage("signup.err.msg.repeat.password", null, Locale.getDefault()));
            LOGGER.log(Level.WARNING,"Registration is failed. Repeat password is not correct.", user.getEmail());
            return "redirect:/signup";
        }

        if (br.hasErrors()) return "signup";

        user.setPassword(passwordEncoder.encode(notEncryptedPassword));

        List<String> errorMessages = passValidator.validate(notEncryptedPassword);
        if (!errorMessages.isEmpty()) {
            String errMsg = messageSource.getMessage("signup.err.msg.general.error", null, Locale.getDefault());
            if (errorMessages.contains(CorePasswordValidator.LENGTH_OR_SPECIAL_CHARACTER_ERROR)) {
                errMsg = messageSource.getMessage("signup.err.msg.special.character", null, Locale.getDefault());
                LOGGER.log(Level.WARNING, "Registration is failed. Password shorter than 8 characters or doesn't contain a special character.");
            }
            if (errorMessages.contains(CorePasswordValidator.MOST_COMMON_PASSWORD)) {
                errMsg = messageSource.getMessage("signup.err.msg.common.password", null, Locale.getDefault());
                LOGGER.log(Level.WARNING, "Registration is failed. User selected one of the most common passwords.");
            }
            session.setAttribute("errMsg", errMsg);
            return "redirect:/signup";
        }

        String partnerCode = null;
        if (!partnerCodeCookie.equals("null")) {
            partnerCode = partnerCodeCookie;
            LOGGER.log(Level.INFO, "Partner code {0} is found in cookie", partnerCode);
        }
        user.setEnabled(true);

        userFacade.registerUser(user, partnerCode);
        LOGGER.log(Level.INFO, "User with email {0} is registered successfully", user.getEmail());
        return "redirect:/signin";
    }
}
