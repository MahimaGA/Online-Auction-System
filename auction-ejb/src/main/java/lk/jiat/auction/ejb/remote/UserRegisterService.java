package lk.jiat.auction.ejb.remote;

import jakarta.ejb.Local;

@Local
public interface UserRegisterService {
    boolean register(String username, String firstName, String lastName, String email, String password);
}
