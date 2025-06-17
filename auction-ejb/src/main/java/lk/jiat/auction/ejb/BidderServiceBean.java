package lk.jiat.auction.ejb;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import lk.jiat.auction.core.model.Auction;
import lk.jiat.auction.core.model.Bidder;
import lk.jiat.auction.core.util.UniqueResourceGenerator;
import lk.jiat.auction.ejb.remote.AuctionManager;
import lk.jiat.auction.ejb.remote.BidderService;

import java.util.List;

@Stateless
public class BidderServiceBean implements BidderService {

    @EJB
    private AuctionManager auctionManager;

    @Override
    public boolean authenticateUser(String auctionId, String username) {
        Bidder matchedBidder = null;

        // get the auction from auction storage
        Auction auction = auctionManager.getAuction(auctionId);

        if(auction != null){
            List<Bidder> registeredBidders = auction.getRegisteredBidders();

            // check if the user already register
            for(Bidder bidder : registeredBidders){
                if(bidder.getUsername().equals(username)){
                    matchedBidder = bidder;
                    break;
                }
            }
        }

        return matchedBidder == null;
    }

    @Override
    public void joinAuction(String auctionId, String username) {
        // get the auction from auction storage
        Auction auction = auctionManager.getAuction(auctionId);

        // generate the unique bidder id
        String bidderId = UniqueResourceGenerator.generateIdForBeans("bidder", 5);

        if(auction != null){
            Bidder bidder = new Bidder(
                    bidderId,
                    username,
                    false
            );

            auction.setRegisteredBidder(bidder);
        }
    }
}
