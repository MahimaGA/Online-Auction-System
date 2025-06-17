package lk.jiat.auction.ejb;

import jakarta.ejb.Lock;
import jakarta.ejb.LockType;
import jakarta.ejb.Stateful;
import lk.jiat.auction.ejb.remote.UserSessionManager;

@Stateful
public class UserSessionManagerBean implements UserSessionManager {
    private String username;

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    @Lock(LockType.WRITE)
    public void setUsername(String username) {
        this.username = username;
    }
}
