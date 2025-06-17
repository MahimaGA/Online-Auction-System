package lk.jiat.auction.ejb.remote;

import jakarta.ejb.Local;

@Local
public interface UserSessionManager {
    void setUsername(String username);
    String getUsername();
}
