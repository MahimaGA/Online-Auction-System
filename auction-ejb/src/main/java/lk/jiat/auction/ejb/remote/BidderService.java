package lk.jiat.auction.ejb.remote;

import jakarta.ejb.Local;

@Local
public interface BidderService {
    boolean authenticateUser(String auctionId, String username);
    void joinAuction(String auctionId, String username);
}
