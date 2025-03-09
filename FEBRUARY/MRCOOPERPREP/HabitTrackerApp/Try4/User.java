
// package HabitTrackerApp.Try4;

public class User {
    private static int userCount = 0 ;
    private String name ; 
    private String email ; 
    private String password ; 
    private int badges = 0 ;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        userCount++ ;
    }

    public String getName() {return name;}
    public String getEmail() {return email;}
    public String getPassword() {return password;}
    public int getBadges() {return badges;}
    public void setPassword(String password) {this.password = password;}
}
