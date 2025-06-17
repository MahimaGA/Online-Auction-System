package lk.jiat.auction.core.model;

import java.io.Serializable;

public class Bidder implements Serializable {
    private String bidderId;
    private String username;
    private boolean winnerStatus;

    public Bidder(){

    }

    public Bidder(String bidderId, String username, boolean winnerStatus){
        this.bidderId = bidderId;
        this.username = username;
        this.winnerStatus = winnerStatus;
    }

    public String getBidderId() {
        return bidderId;
    }

    public void setBidderId(String bidderId) {
        this.bidderId = bidderId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean getWinnerStatus() {
        return winnerStatus;
    }

    public void setWinnerStatus(boolean winnerStatus) {
        this.winnerStatus = winnerStatus;
    }
}
