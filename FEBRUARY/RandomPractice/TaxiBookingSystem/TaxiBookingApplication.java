package RandomPractice.TaxiBookingSystem;
import java.util.*  ;
@SuppressWarnings("all")
class Taxi{
    static int taxiCount = 0 ;
    int id ; 
    boolean booked ;
    char currspot ; 
    int next_available_time ; 
    int totalEarnings;
    List<String> booking_history ; 

    public Taxi(){
        this.id = taxiCount++ ; 
        this.booked = false ; 
        this.currspot = 'A' ; 
        this.next_available_time = 0 ; 
        this.totalEarnings = 0 ; 
        this.booking_history = new ArrayList<>() ; 
    }
    public void setdetails(boolean booked , char currspot , int next_available_time , int totalEarnings , String details){
        this.booked  =booked ; 
        this.currspot = currspot ; 
        this.next_available_time = next_available_time ;
        this.totalEarnings = totalEarnings ;
        this.booking_history.add(details) ;
    }

    public void printAllTaxiDetails(){
        System.out.println("Taxi - "+ this.id + " Total Earnings - " + this.totalEarnings);
        System.out.println("TaxiID    BookingID    CustomerID    From    To    PickupTime    DropTime    Amount");
        for(String trip : booking_history)
        {
            System.out.println(id + "          " + trip);
        }
        System.out.println("--------------------------------------------------------------------------------------");
    }

    public void printTaxiDetails(){
        System.out.println("Taxi - "+ this.id + " Total Earnings - " + this.totalEarnings + " Current spot - " + this.currspot +" Free Time - " + this.next_available_time);

    }

}
public class TaxiBookingApplication {
    public static void BookTaxi(
        int customerID , 
        char pickUp , 
        char dropPoint , 
        int pickUpTime , 
        List<Taxi> freeTaxiList)
    {
        int min =Integer.MAX_VALUE ;
        int travelDistance = 0 ; 
        int earning = 0 ;
        int next_available_time = 0 ; 
        int nextSpot = 'Z' ; 
        Taxi BookedTaxi = null;
        String details = "" ;
        for(Taxi t: freeTaxiList){
            int TaxiPickUpTravelDistance  = Math.abs((t.currspot - '0')-(pickUp - '0'))*15 ; 
            if(TaxiPickUpTravelDistance < min){
                min = TaxiPickUpTravelDistance ; 
                travelDistance = Math.abs((pickUp - '0')-(dropPoint - '0'))*15 ; 
                earning = 100 + (travelDistance-5)*10 ; 
                next_available_time = pickUpTime + travelDistance/15 ; 
                nextSpot = dropPoint ; 
                BookedTaxi = t ; 
                details = customerID + "            " + customerID + "              " + pickUp +  "    " + dropPoint + "             " + pickUpTime + "           " +next_available_time + "          " + earning;
                min = TaxiPickUpTravelDistance;
            }
        }
        if (BookedTaxi != null) {
            BookedTaxi.setdetails(true, (char) nextSpot, next_available_time, BookedTaxi.totalEarnings + earning, details);
        } else {
            System.out.println("No taxi available for booking.");
        }
        System.out.println("Taxi " + BookedTaxi.id + " booked");
    }
    public static List<Taxi> createTaxis(int n){
        List<Taxi> taxis = new ArrayList<Taxi>();
        for(int i=1 ;i <=n;i++){
            Taxi t = new Taxi();
            taxis.add(t);
        }
        return taxis;
    }
    public static List<Taxi> getFreeTaxis(List<Taxi> taxis, int currentTime){
        List<Taxi> freeTaxis = new ArrayList<Taxi>();
        for(Taxi t : taxis){
            if(t.next_available_time <= currentTime){
                freeTaxis.add(t);
            }
        }
        return freeTaxis;
    }
    public static void main(String[] args){
        List<Taxi> taxis =createTaxis(4);
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("1. Book Taxi");
            System.out.println("2. Print Taxi Details");
            System.out.println("3. Print All Taxi Details");
            System.out.println("4. Exit");
            int choice = sc.nextInt();
            if(choice == 1){
                System.out.println("Enter Customer ID");
                int customerID = sc.nextInt();
                System.out.println("Enter Pickup Point");
                char pickUp = sc.next().charAt(0);
                System.out.println("Enter Drop Point");
                char dropPoint = sc.next().charAt(0);
                System.out.println("Enter Pickup Time");
                int pickUpTime = sc.nextInt();
                List<Taxi> freeTaxis = getFreeTaxis(taxis, pickUpTime);
                BookTaxi(customerID, pickUp, dropPoint, pickUpTime, freeTaxis);
            } else if(choice == 2){
                System.out.println("Enter Taxi ID");
                int taxiID = sc.nextInt();
                taxis.get(taxiID).printTaxiDetails();
            } else if(choice == 3){
                for(Taxi t : taxis){
                    t.printAllTaxiDetails();
                }
            } else if(choice == 4){
                break;
            }
        }
        sc.close();
    }
}
