package lk.jiat.auction.ejb.remote;

import jakarta.ejb.Local;
import lk.jiat.auction.core.model.BidRecord;

import java.util.List;

@Local
public interface BidManager {
    List<BidRecord> getBidRecordsForAuction(String auctionId);
    void addBidRecordListForAuction(String auctionId);
    void addBidRecordForAuction(String auctionId, BidRecord bidRecord);
}
