package lk.jiat.auction.core.dto;

import java.io.Serializable;

public class AuctionDTO implements Serializable {
    private String auctionId;
    private String productName;
    private String minEstimatePrice;
    private String maxEstimatePrice;
    private String startDateTime;
    private String endDateTime;
    private boolean registerStatus;

    public AuctionDTO(){

    }

    public AuctionDTO(String auctionId, String productName, String minEstimatePrice, String maxEstimatePrice, String startDateTime, String endDateTime, boolean registerStatus){
        this.auctionId = auctionId;
        this.productName = productName;
        this.minEstimatePrice = minEstimatePrice;
        this.maxEstimatePrice = maxEstimatePrice;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.registerStatus = registerStatus;
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

    public String getMinEstimatePrice() {
        return minEstimatePrice;
    }

    public void setMinEstimatePrice(String minEstimatePrice) {
        this.minEstimatePrice = minEstimatePrice;
    }

    public String getMaxEstimatePrice() {
        return maxEstimatePrice;
    }

    public void setMaxEstimatePrice(String maxEstimatePrice) {
        this.maxEstimatePrice = maxEstimatePrice;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public boolean getRegisterStatus() {
        return registerStatus;
    }

    public void setRegisterStatus(boolean registerStatus) {
        this.registerStatus = registerStatus;
    }
}
