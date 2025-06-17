package lk.jiat.auction.ejb.mdb;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.EJB;
import jakarta.ejb.MessageDriven;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import lk.jiat.auction.core.util.UniqueResourceGenerator;
import lk.jiat.auction.core.util.websocket.BidNotificationWebSocketBroadcaster;
import lk.jiat.auction.ejb.remote.BidService;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Topic"),
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/MyTopic")
})
public class BidNotificationMessageDrivenBean implements MessageListener {

    @EJB
    private BidService bidService;

    @Override
    public void onMessage(Message message) {
        try {
            // get the bid json text
            String bidJsonText = message.getBody(String.class);

            // get data from bidJsonText
            String auctionId = UniqueResourceGenerator.getJsonTextValue(bidJsonText, "auctionId");
            String bidderId = UniqueResourceGenerator.getJsonTextValue(bidJsonText, "bidderId");
            String amount = UniqueResourceGenerator.getJsonTextValue(bidJsonText, "amount");

            if(auctionId != null && bidderId != null && amount != null){
                // validate and store the newest BidRecord in the BidRecords list
                boolean isBidRecordValidateAndStored = bidService.bidValidateAndStore(auctionId, bidderId, amount);

                if(isBidRecordValidateAndStored){
                    // broadcast to all websocket clients who join to this auction room
                    BidNotificationWebSocketBroadcaster.broadcast(auctionId, bidJsonText);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
