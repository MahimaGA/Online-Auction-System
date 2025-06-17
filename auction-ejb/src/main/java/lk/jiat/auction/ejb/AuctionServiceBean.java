package lk.jiat.auction.ejb;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import lk.jiat.auction.core.constant.Params;
import lk.jiat.auction.core.dto.AuctionDTO;
import lk.jiat.auction.core.model.Auction;
import lk.jiat.auction.core.model.Bidder;
import lk.jiat.auction.ejb.remote.AuctionManager;
import lk.jiat.auction.ejb.remote.AuctionService;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateless
public class AuctionServiceBean implements AuctionService {
    @EJB
    private AuctionManager auctionManager;

    @Override
    public List<Auction> getEligibleAuctions(){
        List<Auction> eligibleAuctions = new ArrayList<>();

        for(Auction auction : auctionManager.getAuctions()){
            // check if the auction is closed
            Date auctionEndDate = auction.getEndDateTime();
            Date currentDate = new Date();

//            long timeDifferenceInMillis = Math.abs(auctionEndDate.getTime() - currentDate.getTime());
//            long timeDifferenceInDates = TimeUnit.DAYS.convert(timeDifferenceInMillis, TimeUnit.MILLISECONDS);

            if((auctionEndDate.getTime() - currentDate.getTime()) > 0){
                // auction is not closed
                eligibleAuctions.add(auction);
            }
        }

        return eligibleAuctions;
    }

    @Override
    public List<Auction> getEndedAuctions(){
        List<Auction> endedAuctions = new ArrayList<>();

        for(Auction auction : auctionManager.getAuctions()){
            // check if the auction is closed
            Date auctionEndDate = auction.getEndDateTime();
            Date currentDate = new Date();

            if((auctionEndDate.getTime() - currentDate.getTime()) < 0){
                // auction is closed
                endedAuctions.add(auction);
            }
        }

        return endedAuctions;
    }

    @Override
    public List<AuctionDTO> getEligibleAuctionDTOs(List<Auction> eligibleAuctions, String username){

        List<AuctionDTO> eligibleAuctionDTOs = new ArrayList<>();

        for(Auction auction : eligibleAuctions){
            // construct the auctionDTO object
            AuctionDTO auctionDTO = getAuctionDTO(auction, username);

            eligibleAuctionDTOs.add(auctionDTO);
        }

        return eligibleAuctionDTOs;
    }

    @Override
    public int getAuctionScheduleStatus(String auctionId){
        // get the auction object from the auction storage
        Auction auction = auctionManager.getAuction(auctionId);

        Date auctionStartDate = auction.getStartDateTime();
        Date auctionEndDate = auction.getEndDateTime();
        Date currentDate = new Date();

        int scheduleStatus;

        if((auctionEndDate.getTime() - currentDate.getTime()) > 0){
            // auction is not closed and check if the auction is already started or not
            if((currentDate.getTime() - auctionStartDate.getTime()) > 0){
                // auction is on live
                scheduleStatus = Params.AuctionStatus.SCHEDULE_STATUS_ON_LIVE;

            }else{
                // auction is not stared yet
                scheduleStatus = Params.AuctionStatus.SCHEDULE_STATUS_NOT_STARTED;
            }

        }else {
            // auction is closed
            scheduleStatus = Params.AuctionStatus.SCHEDULE_STATUS_ENDED;
        }

        return scheduleStatus;
    }

    @Override
    public AuctionDTO getAuctionDTO(Auction auction, String username){
        // construct the min and max estimated price
        String minEstimatePrice = new DecimalFormat("###,###.##").format(auction.getMinEstimatePrice()) + Params.CURRENCY;
        String maxEstimatePrice = new DecimalFormat("###,###.##").format(auction.getMaxEstimatePrice()) + Params.CURRENCY;

        // construct the start and end date time
        String startDateTime = new SimpleDateFormat(Params.DATE_TIME_FORMAT2).format(auction.getStartDateTime());
        String endDateTime = new SimpleDateFormat(Params.DATE_TIME_FORMAT2).format(auction.getEndDateTime());

        // check if the user already registered to this auction
        boolean registerStatus = false;

        for(Bidder bidder : auction.getRegisteredBidders()){
            if(bidder.getUsername().equals(username)){
                registerStatus = true;
                break;
            }
        }

        AuctionDTO auctionDTO = new AuctionDTO(
                auction.getAuctionId(),
                auction.getProductName(),
                minEstimatePrice,
                maxEstimatePrice,
                startDateTime,
                endDateTime,
                registerStatus
        );

        return auctionDTO;
    }

    @Override
    public String getBidderId(Auction auction, String username){
        // get the registered bidders from auction object
        List<Bidder> bidders = auction.getRegisteredBidders();

        String foundedBidderId = null;

        for(Bidder bidder : bidders){
            if(bidder.getUsername().equals(username)){
                foundedBidderId = bidder.getBidderId();
                break;
            }
        }

        return foundedBidderId;
    }

    @Override
    public boolean getBidderAuthenticateStatus(Auction auction, String bidderId){
        // get the registered bidders from auction object
        List<Bidder> bidders = auction.getRegisteredBidders();

        boolean authenticateStatus = false;

        for(Bidder bidder : bidders){
            if(bidder.getBidderId().equals(bidderId)){
                authenticateStatus = true;
                break;
            }
        }

        return authenticateStatus;
    }
}
