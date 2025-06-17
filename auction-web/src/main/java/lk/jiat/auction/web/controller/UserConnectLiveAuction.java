package lk.jiat.auction.web.controller;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lk.jiat.auction.core.constant.Params;
import lk.jiat.auction.core.dto.AuctionDTO;
import lk.jiat.auction.core.dto.BidRecordDTO;
import lk.jiat.auction.core.model.Auction;
import lk.jiat.auction.core.model.BidRecord;
import lk.jiat.auction.core.model.User;
import lk.jiat.auction.ejb.remote.*;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/UserConnectLiveAuction")
public class UserConnectLiveAuction extends HttpServlet {

    @EJB
    private AuctionManager auctionManager;

    @EJB
    private AuctionService auctionService;

    @EJB
    private BidManager bidManager;

    @EJB
    private BidService bidService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String auctionId = request.getParameter("auctionId");

        // get the user session from session scope
        HttpSession session = request.getSession();
        UserSessionManager userSessionManager = (UserSessionManager) session.getAttribute("user");

        // check if the UserSessionManager bean available or not
        if(userSessionManager != null){
            // UserSessionManager found
            String username = userSessionManager.getUsername();

            switch(auctionService.getAuctionScheduleStatus(auctionId)){
                case(Params.AuctionStatus.SCHEDULE_STATUS_NOT_STARTED):
                    response.sendRedirect("home");
                    break;

                case(Params.AuctionStatus.SCHEDULE_STATUS_ON_LIVE):
                    // get the user object from the UserSessionManager
                    User user = auctionManager.getUser(username);

                    // get the auction object from auctions store
                    Auction auction = auctionManager.getAuction(auctionId);
                    // construct the auctionDTO object
                    AuctionDTO auctionDTO = auctionService.getAuctionDTO(auction, username);
                    // get the bidderId from auction
                    String bidderId = auctionService.getBidderId(auction, username);
                    // construct the starting price
                    double startingPrice = auction.getMinEstimatePrice() + (auction.getMaxEstimatePrice() - auction.getMinEstimatePrice()) * 0.1;
                    String formattedStringPrice = new DecimalFormat("###,###.##").format(startingPrice) + Params.CURRENCY;

                    // check if the user is registered to this auction
                    if(bidderId != null){
                        // bidderId found
                        List <BidRecordDTO> bidRecordDTOs = new ArrayList<>();

                        String formattedNextBidIncrement = null;

                        // check if the BidRecord lists are constructed and assign to this auction
                        if(bidManager.getBidRecordsForAuction(auctionId) != null){
                            // BidRecord List found
                            List<BidRecord> bidRecords = bidManager.getBidRecordsForAuction(auctionId);

                            // check if the bidRecords list has any BidRecords
                            if(!bidRecords.isEmpty()){
                                // bidRecords found and get latest BidRecord
                                BidRecord latestBidRecord = bidService.getLatestBidRecordForAuction(auctionId);

                                // calculate the next bid increment
                                double nextBidIncrement = latestBidRecord.getAmount() + (latestBidRecord.getAmount() * 0.1);
                                formattedNextBidIncrement = new DecimalFormat("###,###.##").format(nextBidIncrement) + Params.CURRENCY;

                            }else{
                                // bidRecords not found and assign the next bid increment price as the same as price in starting price
                                formattedNextBidIncrement = formattedStringPrice;
                            }

                            // construct the BidRecordDTO list
                            for(BidRecord bidRecord : bidRecords){
                                String biddingPrice = new DecimalFormat("###,###.##").format(bidRecord.getAmount()) + Params.CURRENCY;

                                BidRecordDTO bidRecordDTO = new BidRecordDTO(
                                        biddingPrice,
                                        bidderId
                                );

                                bidRecordDTOs.add(bidRecordDTO);
                            }

                        }else{
                            // BidRecord List not found
                            bidManager.addBidRecordListForAuction(auctionId);
                            System.out.println("Bid record list established for auction : " + auctionId);

                            // assign the next bid increment price as the same as price in starting price
                            formattedNextBidIncrement = formattedStringPrice;
                        }

                        // put user and AuctionDTO objects to the request object
                        request.setAttribute("user", user);
                        request.setAttribute("auctionDTO", auctionDTO);
                        request.setAttribute("bidderId", bidderId);
                        request.setAttribute("startingPrice", formattedStringPrice);
                        request.setAttribute("nextBidIncrement", formattedNextBidIncrement);
                        request.setAttribute("bidRecordDTOs", bidRecordDTOs);

                        request.getRequestDispatcher("auction-live-room.jsp").forward(request, response);

                    }else{
                        // bidderId not found
                        response.sendRedirect("home");
                    }

                    break;

                case(Params.AuctionStatus.SCHEDULE_STATUS_ENDED):
                    response.sendRedirect("home");
                    break;

                default:
                    response.sendRedirect("home");
                    break;
            }

        }else{
            // UserSessionManager not found
            response.sendRedirect("login.jsp");
        }
    }
}
