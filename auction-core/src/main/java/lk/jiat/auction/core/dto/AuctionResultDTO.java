package lk.jiat.auction.core.dto;

import java.io.Serializable;

public class AuctionResultDTO implements Serializable {
    private String sortingNumber;
    private String auctionId;
    private String productName;
    private String startDateTime;
    private String endDateTime;
    private String winner;

    public AuctionResultDTO(){

    }

    public AuctionResultDTO(String sortingNumber, String auctionId, String productName, String startDateTime, String endDateTime, String winner) {
        this.sortingNumber = sortingNumber;
        this.auctionId = auctionId;
        this.productName = productName;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.winner = winner;
    }

    public String getSortingNumber() {
        return sortingNumber;
    }

    public void setSortingNumber(String sortingNumber) {
        this.sortingNumber = sortingNumber;
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

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
