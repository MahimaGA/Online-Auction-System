package lk.jiat.auction.core.model;

import java.io.Serializable;
import java.util.Date;

public class BidRecord implements Serializable {
    private String auctionId;
    private String bidderId;
    private double amount;
    private Date recordedDateTime;

    public BidRecord(String auctionId, String bidderId, double amount, Date recordedDateTime) {
        this.auctionId = auctionId;
        this.bidderId = bidderId;
        this.amount = amount;
        this.recordedDateTime = recordedDateTime;
    }

    public BidRecord(){

    }

    public String getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(String auctionId) {
        this.auctionId = auctionId;
    }

    public String getBidderId() {
        return bidderId;
    }

    public void setBidderId(String bidderId) {
        this.bidderId = bidderId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getRecordedDateTime() {
        return recordedDateTime;
    }

    public void setRecordedDateTime(Date recordedDateTime) {
        this.recordedDateTime = recordedDateTime;
    }
}
