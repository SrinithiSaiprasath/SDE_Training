package MRCOOPERPREP.piggybank;

import java.util.* ;
@SuppressWarnings("unused") 

public class User {
    private String name, contact, address;
    private String id;
    
    private String password;
    private static int userCounter = 1000;

    public User(String name, String contact, String address, String password) {
        this.name = name;
        this.id = generateUserId();
        this.contact = contact;
        this.address = address;
        this.password = password;
    }

    private String generateUserId() {
        return "user_" + (userCounter++);
    }

    public String getAccId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}