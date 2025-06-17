package lk.jiat.auction.web.controller;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lk.jiat.auction.ejb.remote.UserLoginService;
import lk.jiat.auction.ejb.remote.UserSessionManager;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;

@WebServlet("/UserLogIn")
public class UserLogin extends HttpServlet {

    @EJB
    private UserLoginService userLoginService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();

        boolean isLoggingSuccess = userLoginService.login(username, password);

        if(isLoggingSuccess){

            try {
                InitialContext context = new InitialContext();
                UserSessionManager userSessionManager = (UserSessionManager) context.lookup("java:global/auction-ear/auction-ejb-module/UserSessionManagerBean!lk.jiat.auction.ejb.remote.UserSessionManager");

                // assign username to the userSession
                userSessionManager.setUsername(username);

                // store UserSession bean in the session scope
                session.setAttribute("user", userSessionManager);
                response.sendRedirect("home");

            } catch (NamingException e) {
                throw new RuntimeException(e);
            }

        }else{
            response.sendRedirect("login.jsp");
        }
    }
}
