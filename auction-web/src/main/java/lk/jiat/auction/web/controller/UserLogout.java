package lk.jiat.auction.web.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/UserLogout")
public class UserLogout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get the Session object from the session scope
        HttpSession session = request.getSession();

        // remove the UserSessionManagerBean object from the session scope
        session.removeAttribute("user");
        session.invalidate();

        response.sendRedirect("login.jsp");
    }
}
