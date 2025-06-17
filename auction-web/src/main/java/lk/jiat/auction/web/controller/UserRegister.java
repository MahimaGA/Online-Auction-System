package lk.jiat.auction.web.controller;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.jiat.auction.ejb.remote.UserRegisterService;

import java.io.IOException;

@WebServlet("/UserRegister")
public class UserRegister extends HttpServlet {

    @EJB
    private UserRegisterService userRegisterService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        boolean isUserRegistered = userRegisterService.register(username, firstName, lastName, email, password);

        if(isUserRegistered){
            response.sendRedirect("login.jsp");
        }else{
            response.sendRedirect("register.jsp");
        }
    }
}
