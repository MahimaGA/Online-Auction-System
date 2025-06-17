package lk.jiat.auction.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Auction implements Serializable {
    private String auctionId;
    private String productName;
    private double minEstimatePrice;
    private double maxEstimatePrice;
    private Date startDateTime;
    private Date endDateTime;
    private List<Bidder> registeredBidders = new ArrayList<>();

    public Auction(){

    }

    public Auction(String auctionId, String productName, double minEstimatePrice, double maxEstimatePrice, Date startDateTime, Date endDateTime){
        this.auctionId = auctionId;
        this.productName = productName;
        this.minEstimatePrice = minEstimatePrice;
        this.maxEstimatePrice = maxEstimatePrice;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public String getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(String auctionId) {
        this.auctionId = auctionId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getMinEstimatePrice() {
        return minEstimatePrice;
    }

    public void setMinEstimatePrice(double minEstimatePrice) {
        this.minEstimatePrice = minEstimatePrice;
    }

    public double getMaxEstimatePrice() {
        return maxEstimatePrice;
    }

    public void setMaxEstimatePrice(double maxEstimatePrice) {
        this.maxEstimatePrice = maxEstimatePrice;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public List<Bidder> getRegisteredBidders(){
        return registeredBidders;
    }

    public void setRegisteredBidder(Bidder bidder){
        System.out.println("New bidder registered...");
        this.registeredBidders.add(bidder);
    }
}
