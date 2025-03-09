// package RandomPractice.FlightBookingSystem ;
// package MRCOOPERPREP.RandomPractice.FlightBookingSystem;
import java.util.*;

class Flight {
    int flightID;
    String flightName;
    static final int totalSeats = 50;
    int bookedSeats;
    String departureLocation;
    String arrivalLocation;
    String travelDate;
    String departureTime;
    String arrivalTime;

    public Flight(int flightID, String flightName, String departureLocation, String arrivalLocation, String travelDate, String departureTime, String arrivalTime) {
        this.flightID = flightID;
        this.flightName = flightName;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.travelDate = travelDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.bookedSeats = 0;
    }

    public boolean hasAvailableSeats(int requestedSeats) {
        return (totalSeats - bookedSeats) >= requestedSeats;
    }

    public void bookSeats(int seats) {
        bookedSeats += seats;
    }

    public void displayFlightData() {
        System.out.println("Flight ID: " + flightID);
        System.out.println("Flight Name: " + flightName);
        System.out.println("Departure: " + departureLocation);
        System.out.println("Arrival: " + arrivalLocation);
        System.out.println("Date: " + travelDate);
        System.out.println("Departure Time: " + departureTime);
        System.out.println("Arrival Time: " + arrivalTime);
        System.out.println("Available Seats: " + (totalSeats - bookedSeats));
    }
}

class User {
    int userID;
    String userName;
    String userEmail;
    String userPhone;
    List<Integer> bookedFlightIDs = new ArrayList<>();

    public User(int userID, String userName, String userEmail, String userPhone) {
        this.userID = userID;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
    }

    public void bookFlight(int flightID) {
        bookedFlightIDs.add(flightID);
    }

    public void displayUserData() {
        System.out.println("User ID: " + userID);
        System.out.println("Name: " + userName);
        System.out.println("Email: " + userEmail);
        System.out.println("Phone: " + userPhone);
        System.out.println("Booked Flights: " + bookedFlightIDs);
    }
}
interface FlightBookingSystem {
    void addFlight(Flight flight);
    User registerUser(int userID, String userName, String userEmail, String userPhone);
    void showAvailableFlights(String departure, String arrival);
    void bookFlight(User user, String departure, String arrival, int seats);
}
public class Sample3 implements FlightBookingSystem{
    List<Flight> flightList = new ArrayList<>();
    List<User> userList = new ArrayList<>();

    public void addFlight(Flight flight) {
        flightList.add(flight);
    }

    public User registerUser(int userID, String userName, String userEmail, String userPhone) {
        User user = new User(userID, userName, userEmail, userPhone);
        userList.add(user);
        return user;
    }

    @SuppressWarnings("override")
    public void showAvailableFlights(String departure, String arrival) {
        boolean found = false;
        for (Flight flight : flightList) {
            if (flight.departureLocation.equalsIgnoreCase(departure) && flight.arrivalLocation.equalsIgnoreCase(arrival)) {
                flight.displayFlightData();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No flights available for the specified route.");
        }
    }

    public void bookFlight(User user, String departure, String arrival, int seats) {
        for (Flight flight : flightList) {
            if (flight.departureLocation.equalsIgnoreCase(departure) && flight.arrivalLocation.equalsIgnoreCase(arrival) && flight.hasAvailableSeats(seats)) {
                flight.bookSeats(seats);
                user.bookFlight(flight.flightID);
                System.out.println("Flight booked successfully for " + user.userName);
                return;
            }
        }
        System.out.println("Booking failed. No available flight or insufficient seats.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FlightBookingSystem system = new Sample3();
        
        // Add sample flights
        system.addFlight(new Flight(101, "Indigo", "Delhi", "Mumbai", "2024-12-12", "10:00", "12:00"));
        system.addFlight(new Flight(102, "Air India", "Delhi", "Chennai", "2024-12-15", "08:00", "11:30"));
    
        // Register a new user with predefined values
        User me = system.registerUser(1001, "John Doe", "john@example.com", "9876543210");
        System.out.println("User registered successfully!");
    
        // Search for flights
        String departure = "Delhi";
        String arrival = "Mumbai";
        system.showAvailableFlights(departure, arrival);
    
        // Book flight
        int seatsToBook = 2;
        system.bookFlight(me, departure, arrival, seatsToBook);
    
        // Display final user information
        System.out.println("\nUser Information After Booking:");
        me.displayUserData();
         //display flightData 
        System.out.println("\nFlight Information After Booking:");
        for (Flight flight : ((Sample3) system).flightList) {
            flight.displayFlightData();
        }

        sc.close();
    }
    /*
OOP Concepts Used:

1. Encapsulation:
   - Class Flight encapsulates flight-related data and methods
   - Class User encapsulates user-related data and methods
   - Methods like bookSeats(), displayFlightData() hide implementation details

2. Inheritance:
   - Sample3 class implements FlightBookingSystem interface
   - Implementation inheritance through interface

3. Abstraction:
   - FlightBookingSystem interface provides abstraction layer
   - Abstract methods defined in interface:
     * addFlight()
     * registerUser()
     * showAvailableFlights()
     * bookFlight()

4. Polymorphism:
   - Runtime polymorphism through interface implementation
   - Method overriding in Sample3 class of interface methods

5. Class and Object:
   - Classes: Flight, User, Sample3
   - Objects: system, flight, user instances

6. Data Hiding:
   - Lists maintained in Sample3 class (though should be private)
   - Internal state management in Flight class

7. Association:
   - User class has a List of booked flight IDs (One-to-Many)
   - Sample3 class has Lists of Flights and Users (One-to-Many)

8. Interface:
   - FlightBookingSystem defining contract for implementation

Areas for Improvement:
1. Make class fields private for better encapsulation
2. Add getters/setters where needed
3. Use proper access modifiers
4. Implement proper error handling
5. Add input validation
*/
}
