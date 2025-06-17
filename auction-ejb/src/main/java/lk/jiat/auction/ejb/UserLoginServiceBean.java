package lk.jiat.auction.ejb;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import lk.jiat.auction.core.model.User;
import lk.jiat.auction.ejb.remote.AuctionManager;
import lk.jiat.auction.ejb.remote.UserLoginService;

@Stateless
public class UserLoginServiceBean implements UserLoginService {

    @EJB
    private AuctionManager auctionManager;

    @Override
    public boolean login(String username, String password){

        boolean isSuccess = false;

        if(!username.isEmpty() &&
                !password.isEmpty()
        ){
            // get user from users
            User user = auctionManager.getUser(username);

            // check the user credentials
            if(user != null){
                // check if the password is matched to the username
                if(user.getPassword().equals(password)){
                    // password matched
                    isSuccess = true;
                }
            }
        }

        return isSuccess;
    }
}
