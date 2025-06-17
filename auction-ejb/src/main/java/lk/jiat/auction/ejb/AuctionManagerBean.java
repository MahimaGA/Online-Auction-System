package lk.jiat.auction.ejb;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.*;
import lk.jiat.auction.core.constant.Params;
import lk.jiat.auction.core.model.Auction;
import lk.jiat.auction.core.model.User;
import lk.jiat.auction.ejb.remote.AuctionManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Singleton
@Startup
public class AuctionManagerBean implements AuctionManager {
    private HashMap<String, User> users;
    private HashMap<String, Auction> auctions;

    @PostConstruct
    public void init(){
        // construct the user storage
        if(users == null){
            users = new HashMap<>();
            System.out.println("User storage created...");
        }

        // construct the auction storage
        if(auctions == null){
            auctions = new HashMap<>();
            System.out.println("Auction storage created...");

            Date startDate1 = new Date();
            Date endDate1 = new Date();
            Date startDate2 = new Date();
            Date endDate2 = new Date();
            Date startDate3 = new Date();
            Date endDate3 = new Date();
            Date startDate4 = new Date();
            Date endDate4 = new Date();
            Date startDate5 = new Date();
            Date endDate5 = new Date();

            try{
                startDate1 = new SimpleDateFormat(Params.DATE_FORMAT).parse("2025-05-30 11:30:00");
                endDate1 = new SimpleDateFormat(Params.DATE_TIME_FORMAT1).parse("2025-06-02 14:30:00");

                startDate2 = new SimpleDateFormat(Params.DATE_TIME_FORMAT1).parse("2025-06-03 09:00:00");
                endDate2 = new SimpleDateFormat(Params.DATE_TIME_FORMAT1).parse("2025-06-06 00:31:00");

                startDate3 = new SimpleDateFormat(Params.DATE_TIME_FORMAT1).parse("2025-06-04 07:45:00");
                endDate3 = new SimpleDateFormat(Params.DATE_TIME_FORMAT1).parse("2025-06-07 14:30:00");

                startDate4 = new SimpleDateFormat(Params.DATE_TIME_FORMAT1).parse("2025-06-02 11:00:00");
                endDate4 = new SimpleDateFormat(Params.DATE_TIME_FORMAT1).parse("2025-06-07 09:30:00");

                startDate5 = new SimpleDateFormat(Params.DATE_TIME_FORMAT1).parse("2025-05-31 09:00:00");
                endDate5 = new SimpleDateFormat(Params.DATE_TIME_FORMAT1).parse("2025-06-09 07:45:00");

            }catch(ParseException e){
                e.printStackTrace();
            }

            // put 2 auction for temporary usage
            auctions.put(
                    "auction0001",
                    new Auction(
                            "auction0001",
                            "Handmade Ceramic Vase",
                            1500,
                            3000,
                            startDate1,
                            endDate1
                    )
            );
            auctions.put(
                    "auction0002",
                    new Auction(
                            "auction0002",
                            "Bluetooth Wireless Earbuds",
                            2500,
                            4000,
                            startDate2,
                            endDate2
                    )
            );
            auctions.put(
                    "auction0003",
                    new Auction(
                            "auction0003",
                            "Used Book Bundle",
                            1500,
                            3000,
                            startDate3,
                            endDate3
                    )
            );
            auctions.put(
                    "auction0004",
                    new Auction(
                            "auction0004",
                            "Mini Retro Game Console",
                            2000,
                            3500,
                            startDate4,
                            endDate4
                    )
            );
            auctions.put(
                    "auction0005",
                    new Auction(
                            "auction0005",
                            "Custom Portrait Sketch",
                            4000,
                            8000,
                            startDate5,
                            endDate5
                    )
            );
        }
    }

    @Override
    @Lock(LockType.WRITE)
    public void addUser(User user){
        users.put(user.getUsername(), user);
    }

    @Override
    public User getUser(String username){
        return users.get(username);
    }

    @Override
    @Lock(LockType.WRITE)
    public void addAuction(Auction auction){
        auctions.put(auction.getAuctionId(), auction);
    }

    @Override
    public Auction getAuction(String auctionId){
        return auctions.get(auctionId);
    }

    @Override
    public Collection<Auction> getAuctions(){
        return auctions.values();
    }
}
