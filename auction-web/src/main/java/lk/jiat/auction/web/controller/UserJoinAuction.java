package lk.jiat.auction.web.controller;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lk.jiat.auction.ejb.remote.BidderService;
import lk.jiat.auction.ejb.remote.UserSessionManager;

import java.io.IOException;

@WebServlet("/UserJoinAuction")
public class UserJoinAuction extends HttpServlet {

    @EJB
    private BidderService bidderService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String auctionId = request.getParameter("auctionId");

        // get username from the session scope
        HttpSession session = request.getSession();

        // check if the UserSessionBean in the session scope
        if(session.getAttribute("user") != null){
            // UserSessionBean found
            UserSessionManager userSessionManager = (UserSessionManager) session.getAttribute("user");
            String username = userSessionManager.getUsername();

            // check if the user already registered to this auction
            boolean isUserAuthenticated = bidderService.authenticateUser(auctionId, username);

            if(isUserAuthenticated){
                // user join to the auction room
                bidderService.joinAuction(auctionId, username);
            }

            response.sendRedirect("home");

        }else{
            // UserSessionBean not found
            response.sendRedirect("login.jsp");
        }

    }
}
