package lk.jiat.auction.ejb;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import lk.jiat.auction.core.model.User;
import lk.jiat.auction.ejb.remote.AuctionManager;
import lk.jiat.auction.ejb.remote.UserRegisterService;

@Stateless
public class UserRegisterServiceBean implements UserRegisterService {

    @EJB
    private AuctionManager auctionManager;

    @Override
    public boolean register(String username, String firstName, String lastName, String email, String password){

        boolean isSuccess = false;

        if(!username.isEmpty() &&
                !firstName.isEmpty() &&
                !lastName.isEmpty() &&
                !email.isEmpty() &&
                !password.isEmpty()
        ){
            // check if the user already registered
            if(auctionManager.getUser(username) == null){
                // user doest not have account and continue register process

                User user = new User(
                        username,
                        firstName,
                        lastName,
                        email,
                        password
                );

                auctionManager.addUser(user);
                isSuccess = true;
            }
        }

        return isSuccess;
    }
}
