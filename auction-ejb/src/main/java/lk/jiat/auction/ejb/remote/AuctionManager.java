package lk.jiat.auction.ejb.remote;

import jakarta.ejb.Local;
import lk.jiat.auction.core.model.Auction;
import lk.jiat.auction.core.model.User;

import java.util.Collection;

@Local
public interface AuctionManager {
    User getUser(String username);
    void addUser(User user);

    Auction getAuction(String auctionId);
    Collection<Auction> getAuctions();
    void addAuction(Auction auction);
}
