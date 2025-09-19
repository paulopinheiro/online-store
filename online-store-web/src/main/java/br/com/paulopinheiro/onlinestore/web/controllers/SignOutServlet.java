package br.com.paulopinheiro.onlinestore.web.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/signout"})
public class SignOutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().invalidate();

        String baseUrl = request.getScheme() + "://"
                       + request.getServerName() + ":"
                       + request.getServerPort()
                       + request.getServletContext().getContextPath();

        response.sendRedirect(baseUrl + "/homepage");
    }
}
