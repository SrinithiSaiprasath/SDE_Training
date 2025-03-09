package Try4;
import java.util.HashMap;

from Try4.User import *;

public class UserManager {
    private HashMap<String , User> users = new HashMap<>() ;
    private HashMap<String , Integer> activeUsers = new HashMap<>() ;

    public void register(String name , String email , String password) {
        User user = new User(name , email , password) ;
        users.put(email , user) ;
    }

    public void login(String name , String password){
        for (String email : users.keySet()) {
            User user = users.get(email) ;
            if (user.getName().equals(name) && user.getPassword().equals(password)) {
                activeUsers.put(email , user.getBadges()) ;
                return ;
            }
        }
    }
    
}
