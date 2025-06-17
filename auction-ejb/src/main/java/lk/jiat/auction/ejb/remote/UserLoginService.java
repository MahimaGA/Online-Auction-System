package lk.jiat.auction.ejb.remote;

import jakarta.ejb.Local;

@Local
public interface UserLoginService {
    boolean login(String username, String password);
}
