package br.com.paulopinheiro.onlinestore.web.controllers;

import br.com.paulopinheiro.onlinestore.web.Configurations;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Locale;

@WebServlet(urlPatterns = {"/change-locale"})
public class ChangeLocalServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String language = request.getParameter("locale");

        if (language==null) {
            Locale.setDefault(new Locale(Configurations.DEFAULT_ENGLISH_LANGUAGE));
            request.getSession().setAttribute("locale", Configurations.DEFAULT_ENGLISH_LANGUAGE);
        } else if (language.equals(Configurations.FRENCH_LANGUAGE)) {
            Locale.setDefault(new Locale(Configurations.FRENCH_LANGUAGE));
            request.getSession().setAttribute("locale", Configurations.FRENCH_LANGUAGE);
        }

        String baseUrl = request.getScheme() + "://"
                       + request.getServerName() + ":"
                       + request.getServerPort()
                       + request.getServletContext().getContextPath();

        response.sendRedirect(baseUrl);
    }
}
