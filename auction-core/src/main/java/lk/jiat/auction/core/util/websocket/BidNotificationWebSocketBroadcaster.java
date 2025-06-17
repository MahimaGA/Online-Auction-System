package lk.jiat.auction.core.util.websocket;


import jakarta.websocket.Session;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class BidNotificationWebSocketBroadcaster {
    private static final Map<String, Set<jakarta.websocket.Session>> auctionSessions = new ConcurrentHashMap<>();

    public static void register(String auctionId, Session session) {
        auctionSessions.computeIfAbsent(auctionId, k -> new HashSet<>()).add(session);
    }

    public static void unregister(String auctionId, Session session) {
        Set<Session> sessions = auctionSessions.get(auctionId);
        if (sessions != null) sessions.remove(session);
    }

    public static void broadcast(String auctionId, String message) {
        Set<Session> sessions = auctionSessions.get(auctionId);
        if (sessions != null) {
            for (Session session : sessions) {
                if (session.isOpen()) {
                    System.out.println("Message broadcasting...");
                    session.getAsyncRemote().sendText(message);
                }
            }
        }
    }
}
