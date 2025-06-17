package lk.jiat.auction.web.controller;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.jiat.auction.core.constant.Params;
import lk.jiat.auction.core.dto.AuctionResultDTO;
import lk.jiat.auction.core.model.Auction;
import lk.jiat.auction.core.model.BidRecord;
import lk.jiat.auction.core.model.Bidder;
import lk.jiat.auction.ejb.remote.AuctionService;
import lk.jiat.auction.ejb.remote.BidService;
import lk.jiat.auction.ejb.remote.UserSessionManager;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/AuctionResultLoad")
public class AuctionResultLoad extends HttpServlet {

    @EJB
    private AuctionService auctionService;

    @EJB
    private BidService bidService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // check if the UserSessionManager found or not
        if(request.getSession().getAttribute("user") != null){
            // get the UserSessionManagerBean from the session scope
            UserSessionManager userSessionManager = (UserSessionManager) request.getSession().getAttribute("user");

            // UserSessionManager found
            String username = userSessionManager.getUsername();

            // get the ended Auctions list
            List<Auction> endedAuctions = auctionService.getEndedAuctions();

            List<AuctionResultDTO> endedAuctionResultDTOs = new ArrayList<>();

            int tableIterator = 0;

            for(Auction auction : endedAuctions){
                // get the latest BidRecord for this auction
                BidRecord bidRecord = bidService.getLatestBidRecordForAuction(auction.getAuctionId());

                String winner = "---";

                // check if any of the BidRecord available
                if(bidRecord != null){
                    // BidRecord available and get the bidderId from BidRecord
                    String bidderId = bidRecord.getBidderId();

                    // update the winner status to winner in the registered Bidder object
                    for(Bidder bidder : auction.getRegisteredBidders()){
                        if(bidder.getBidderId().equals(bidderId)){
                            if(!bidder.getWinnerStatus()){
                                bidder.setWinnerStatus(true);
                            }
                            // get the bidder username
                            String bidderUsername = bidder.getUsername();

                            // check if the user is current user
                            if(bidderUsername.equals(username)){
                                // winner is the current user
                                winner = bidderId + " (You are the winner!!!)";

                            }else{
                                // winner is not the current user
                                winner = bidderId;
                            }
                            break;
                        }
                    }

                }else{
                    // BidRecord not available
                    winner = "No participants";
                }

                // construct the AuctionResultDTO
                AuctionResultDTO auctionResultDTO = new AuctionResultDTO(
                        ((tableIterator + 1) < 10 ? "0" + String.valueOf(tableIterator + 1) : String.valueOf(tableIterator + 1)),
                        auction.getAuctionId(),
                        auction.getProductName(),
                        new SimpleDateFormat(Params.DATE_TIME_FORMAT2).format(auction.getStartDateTime()),
                        new SimpleDateFormat(Params.DATE_TIME_FORMAT2).format(auction.getEndDateTime()),
                        winner
                );

                // add to the endedAuctionResultDTOs list
                endedAuctionResultDTOs.add(auctionResultDTO);

                tableIterator ++;
            }

            // add AuctionResultDTOs list to the request object
            request.setAttribute("auctionResultDTOs", endedAuctionResultDTOs);

            request.getRequestDispatcher("auction-result.jsp").forward(request, response);

        }else{
            // UserSessionManager not found
            response.sendRedirect("login.jsp");
        }
    }
}
