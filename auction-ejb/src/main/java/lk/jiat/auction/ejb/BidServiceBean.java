package lk.jiat.auction.ejb;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import lk.jiat.auction.core.constant.Params;
import lk.jiat.auction.core.model.Auction;
import lk.jiat.auction.core.model.BidRecord;
import lk.jiat.auction.ejb.remote.AuctionManager;
import lk.jiat.auction.ejb.remote.AuctionService;
import lk.jiat.auction.ejb.remote.BidManager;
import lk.jiat.auction.ejb.remote.BidService;

import java.util.Date;
import java.util.List;

@Stateless
public class BidServiceBean implements BidService {

    @EJB
    private AuctionManager auctionManager;

    @EJB
    private AuctionService auctionService;

    @EJB
    private BidManager bidManager;

    @Override
    public boolean bidValidateAndStore(String auctionId, String bidderId, String amount){
        Auction auction = auctionManager.getAuction(auctionId);

        boolean isValidateAndStoreSuccess = false;

        // check if the auction schedule status
        int auctionScheduleStatus = auctionService.getAuctionScheduleStatus(auctionId);

        if(auctionScheduleStatus == Params.AuctionStatus.SCHEDULE_STATUS_ON_LIVE){
            // auction is on live

            // check if the bidder is in the registered bidders list
            boolean isBidderAuthenticated = auctionService.getBidderAuthenticateStatus(auction, bidderId);

            if(isBidderAuthenticated){
                // bidder already in the registered bidders list
                BidRecord newestBidRecord = null;

                // get the latest BidRecord
                BidRecord latestBidRecord = getLatestBidRecordForAuction(auctionId);

                if(latestBidRecord != null){
                    // latest bid available
                    // check if the current bids time is not pass by latest bid record
                    if(latestBidRecord.getRecordedDateTime().getTime() < new Date().getTime()){
                        // valid time frame
                        // calculate the next bid increment for current latest bid
                        double nextBidIncrement = latestBidRecord.getAmount() + (latestBidRecord.getAmount() * 0.1);

                        // check if the new bid amount is valid
                        if(Double.parseDouble(amount) >= nextBidIncrement){
                            // amount is valid
                            newestBidRecord = new BidRecord(
                                    auctionId,
                                    bidderId,
                                    Double.parseDouble(amount),
                                    new Date()
                            );
                        }
                    }

                }else{
                    // latest bid not available and continue the starting bid placement
                    // calculate the starting bid price
                    double startingBidPrice = auction.getMinEstimatePrice() + (auction.getMaxEstimatePrice() - auction.getMinEstimatePrice()) * 0.1;

                    // check if the new bid amount is valid
                    if(startingBidPrice <= Double.parseDouble(amount)){
                        // amount is valid
                        newestBidRecord = new BidRecord(
                                auctionId,
                                bidderId,
                                Double.parseDouble(amount),
                                new Date()
                        );
                    }
                }

                if(newestBidRecord != null){
                    // add the newest bidRecord to the bidRecords list
                    isValidateAndStoreSuccess = true;
                    bidManager.addBidRecordForAuction(auctionId, newestBidRecord);

                    System.out.println("New bid record saved [BidRecords list size : " + bidManager.getBidRecordsForAuction(auctionId).size() + "]");
                }
            }
        }

        return isValidateAndStoreSuccess;
    }

    @Override
    public BidRecord getLatestBidRecordForAuction(String auctionId){
        List<BidRecord> bidRecords = bidManager.getBidRecordsForAuction(auctionId);

        BidRecord latestRecordedBidRecord = null;

        if(bidRecords != null){
            for(BidRecord bidRecord : bidRecords){
                Date recordedDateTime = bidRecord.getRecordedDateTime();

                if(latestRecordedBidRecord == null){
                    latestRecordedBidRecord = bidRecord;

                }else{
                    if(latestRecordedBidRecord.getRecordedDateTime().getTime() < recordedDateTime.getTime()){
                        latestRecordedBidRecord = bidRecord;
                    }
                }
            }
        }

        return latestRecordedBidRecord;
    }
}
