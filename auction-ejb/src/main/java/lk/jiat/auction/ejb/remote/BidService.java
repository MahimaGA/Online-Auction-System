package lk.jiat.auction.ejb.remote;

import jakarta.ejb.Local;
import lk.jiat.auction.core.model.BidRecord;

@Local
public interface BidService {
    boolean bidValidateAndStore(String auctionId, String bidderId, String amount);
    BidRecord getLatestBidRecordForAuction(String auctionId);
}
