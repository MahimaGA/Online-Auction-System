package lk.jiat.auction.ejb;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Lock;
import jakarta.ejb.LockType;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import lk.jiat.auction.core.model.BidRecord;
import lk.jiat.auction.ejb.remote.BidManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Singleton
@Startup
public class BidManagerBean implements BidManager {
    private HashMap<String, List<BidRecord>> bidRecords;

    @PostConstruct
    public void init(){
        bidRecords = new HashMap<>();
        System.out.println("BidRecord storage created...");
    }

    @Override
    public List<BidRecord> getBidRecordsForAuction(String auctionId){
        return bidRecords.get(auctionId);
    }

    @Override
    @Lock(LockType.WRITE)
    public void addBidRecordListForAuction(String auctionId){
        bidRecords.put(auctionId, new ArrayList<>());
    }

    @Override
    @Lock(LockType.WRITE)
    public void addBidRecordForAuction(String auctionId, BidRecord bidRecord){
        bidRecords.get(auctionId).add(bidRecord);
    }
}
