package lk.jiat.auction.ejb.remote;

import jakarta.ejb.Local;
import lk.jiat.auction.core.dto.AuctionDTO;
import lk.jiat.auction.core.model.Auction;

import java.util.List;

@Local
public interface AuctionService {
    List<Auction> getEligibleAuctions();
    List<AuctionDTO> getEligibleAuctionDTOs(List<Auction> eligibleAuctions, String username);
    int getAuctionScheduleStatus(String auctionId);
    AuctionDTO getAuctionDTO(Auction auction, String username);
    String getBidderId(Auction auction, String username);
    boolean getBidderAuthenticateStatus(Auction auction, String bidderId);
    List<Auction> getEndedAuctions();
}
