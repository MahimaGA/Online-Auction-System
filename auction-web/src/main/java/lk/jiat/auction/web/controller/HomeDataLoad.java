package lk.jiat.auction.web.controller;

import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.jiat.auction.core.dto.AuctionDTO;
import lk.jiat.auction.core.model.Auction;
import lk.jiat.auction.core.model.User;
import lk.jiat.auction.ejb.remote.AuctionManager;
import lk.jiat.auction.ejb.remote.AuctionService;
import lk.jiat.auction.ejb.remote.UserSessionManager;

import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeDataLoad extends HttpServlet {

    @EJB
    private AuctionManager auctionManager;

    @EJB
    private AuctionService auctionService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // get the UserSessionManagerBean from the session scope
        UserSessionManager userSessionManager = (UserSessionManager) request.getSession().getAttribute("user");

        // get the user object from user storage
        User user = auctionManager.getUser(userSessionManager.getUsername());

        // get all the eligible auctions
        List<Auction> eligibleAuctions = auctionService.getEligibleAuctions();

        // get eligible auction DTOs
        List<AuctionDTO> eligibleAuctionDTOs = auctionService.getEligibleAuctionDTOs(eligibleAuctions, userSessionManager.getUsername());

        // set user object and eligible auctions to the request object
        request.setAttribute("user", user);
        request.setAttribute("eligibleAuctionDTOs", eligibleAuctionDTOs);

        // forward Home page related data to hom.jsp
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("home.jsp");
        requestDispatcher.forward(request, response);
    }
}
