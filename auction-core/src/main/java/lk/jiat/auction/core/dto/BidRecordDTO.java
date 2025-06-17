package lk.jiat.auction.core.dto;

import java.io.Serializable;

public class BidRecordDTO implements Serializable {
    private String price;
    private String bidderId;

    public BidRecordDTO(){

    }

    public BidRecordDTO(String price, String bidderId) {
        this.price = price;
        this.bidderId = bidderId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBidderId() {
        return bidderId;
    }

    public void setBidderId(String bidderId) {
        this.bidderId = bidderId;
    }
}
