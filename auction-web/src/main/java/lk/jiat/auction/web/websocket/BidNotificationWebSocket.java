package lk.jiat.auction.web.websocket;

import jakarta.websocket.*;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

import jakarta.jms.*;
import lk.jiat.auction.core.util.websocket.BidNotificationWebSocketBroadcaster;

import javax.naming.InitialContext;
import javax.naming.NamingException;

@ServerEndpoint("/bidNotificationWebSocket/{auctionId}")
public class BidNotificationWebSocket {

    @OnOpen
    public void onOpen(Session session, @PathParam("auctionId") String auctionId) {
        BidNotificationWebSocketBroadcaster.register(auctionId, session);
        System.out.println("New websocket established for auction : " + auctionId);
    }

    @OnMessage
    public void onMessage(String message, Session session, @PathParam("auctionId") String auctionId) {
        try{
            InitialContext context = new InitialContext();
            TopicConnectionFactory factory = (TopicConnectionFactory) context.lookup("jms/MyTopicConnectionFactory");
            TopicConnection connection = factory.createTopicConnection();
            connection.start();

            TopicSession topicSession = connection.createTopicSession(false, jakarta.jms.Session.AUTO_ACKNOWLEDGE);
            Topic topic = (Topic) context.lookup("jms/MyTopic");
            TopicPublisher publisher = topicSession.createPublisher(topic);

            TextMessage topicMessage = topicSession.createTextMessage();
            // add bid json text
            topicMessage.setText(message);

            publisher.publish(topicMessage);

        }catch (NamingException | JMSException e) {
            e.printStackTrace();
        }

    }

    @OnClose
    public void onClose(Session session, @PathParam("auctionId") String auctionId) {
        BidNotificationWebSocketBroadcaster.unregister(auctionId, session);
    }
}
