package RandomPractice.FlightBookingSystem;

import java.util.*;

class Flight{
    int flightID;
    String flightName;
    static final int totalSeats = 50 ;
    int bookedSeats;
    String Departure_Location ; 
    String Arrival_Location ; 
    String Travel_Date ; 
    String Departure_Time ; 
    String Arrival_Time ; 
    public HashMap < Integer , String > flightdata = new HashMap < Integer , String > () ;
    public List<Flight> flightList = new ArrayList<Flight>();

    public Flight(int flightID, String flightName, String Departure_Location, String Arrival_Location ,String Travel_Date , String Departure_Time , String Arrival_Time) {
        this.flightID = flightID;
        this.flightName = flightName;
        this.bookedSeats = 50;
        this.Departure_Location = Departure_Location ;
        this.Arrival_Location = Arrival_Location ;
        this.Travel_Date = Travel_Date ; 
        this.Departure_Time = Departure_Time ; 
        this.Arrival_Time = Arrival_Time ; 
    }
   
    public int getFlightID() {
        System.out.println("Flight ID "+this.flightID);
        return this.flightID;
    }

    public String getFlightName() {
        System.out.println("Flight Name "+this.flightName);
        return this.flightName;
    }

    public static  Flight createFlight(int flightID, String flightName, String Departure_Location, String Arrival_Location,String Travel_Date , String Departure_Time , String Arrival_Time){
        Flight f = new Flight(flightID, flightName, Departure_Location, Arrival_Location, Travel_Date , Departure_Time ,  Arrival_Time);
        
        return f ; 
    }

    public void showAllFlights(){
        //show all flights
        for(int i = 0 ; i < flightdata.size() ; i++){
            System.out.println(flightdata.get(i));
        }
    }
    public void  getFlightTravelData(Flight f){
        System.out.println(" Departure Location : " + f.Departure_Location);
        System.out.println(" Arrival Location : " + f.Arrival_Location);
        System.out.println("Date " + f.Travel_Date);
        System.out.println("Departure Time "  + f.Departure_Time);
        System.out.println("Arrival Time " +f.Arrival_Time );
    }

    public void displayFlightData(Flight f){
        System.out.println("FlightID : " + f.getFlightID());
        System.out.println(" FlightName : " + f.getFlightName());
        System.out.println(" Departure Location : " + f.Departure_Location);
        System.out.println(" Arrival Location : " + f.Arrival_Location);
    }
}
class User{
    int userID = 1000;
    int usercounter =0;
    String userName;
    @SuppressWarnings("unused")
    String userEmail;
    String userPhone;
    String BookedFlightID;
    int BookedSeats;
    static HashMap < Integer , List<Integer>> bookingData = new HashMap < Integer ,List<Integer> > () ;

    public User(int userID, String userName, String userEmail, String userPhone ,String BookedFlightID, int BookedSeats) {
        this.userID = userID;
        this.userName = userName;
        this.userPhone = userPhone;
        this.BookedFlightID = BookedFlightID;
        this.BookedSeats = BookedSeats;
    }
    public int getUserID() {
        System.out.println("User ID "+this.userID + this.usercounter);
        this.usercounter++;
        return this.userID;
    }
    public String getUserName() {
        System.out.println("User Name "+this.userName);
        return this.userName;
    }

    @SuppressWarnings("all")
    public static User createUser(int userID, String userName, String userEmail, String userPhone, int BookedFlightID, int BookedSeats){
        User u = new User(userID, userName, userPhone, userEmail, "1", 1);
        List<Integer> data =new ArrayList();
        data.add(BookedFlightID);
        data.add(BookedSeats);
        bookingData.put(userID ,data);
        return u ; 
    }
    public void displayUserData(User u){
        System.out.println("UserID : " + u.getUserID());
        System.out.println(" UserName : " + u.getUserName());
        System.out.println(" BookedFlightID : " + u.BookedFlightID);
        System.out.println(" BookedSeats : " + u.BookedSeats);
    }
    public void printBookingData(User u){
        System.out.println("Booking Data : " + u.bookingData);
    }
    
}

class BookTicket{
    int userID ;
    String Departure_Location;
    String Arrival_Location;
    Flight f ;
    HashMap<String , String> flightData =new HashMap<>(); 
    Scanner sc = new Scanner(System.in);
    ArrayList <String> userList = new ArrayList<String>();
    // public User(int userID, String userName, String userEmail, String userPhone ,String BookedFlightID, int BookedSeats)
    // public Flight(int flightID, String flightName, String Departure_Location, String Arrival_Location ,String Travel_Date , String Departure_Time , String Arrival_Time) {
    public BookTicket(int userID, String Departure_Location, String Arrival_Location ){
            this.userID = userID;
            this.Departure_Location = Departure_Location;
            this.Arrival_Location = Arrival_Location;
        }
    public void BookTicket(String name , int userID ,String Departure_Location, String Arrival_Location){
        //new user - create user then book 
        // old user - create or book ticket
        System.out.println("hey");
    }
    public void check_flights(String Departure_Location, String Arrival_Location){
        //check flights available
        //if available then book ticket
        //else show message no flights available
        //check for
        // for()

    }
}
public class FlightBookingSystem {
    public static void main(String[] args){
        // Flight(flightID, flightName, Departure_Location, Arrival_Location, Travel_Date , Departure_Time ,  Arrival_Time);
        // Flight f = Flight.createFlight(1, "Indigo", "Delhi", "Mumbai" ,"12/12/2024" , "10:00" , "12:00");
        // f.displayFlightData(f);
        // f.getFlightID();
        // f.getFlightName();
    }
}
