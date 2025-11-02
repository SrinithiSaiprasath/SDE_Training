package NOVEMBER_25.Design_Patterns.Observer_DP;

import java.util.* ; 

public class Main{
    static List<Viewer> allViewers ;   
    public static void main(String[] args){
        //Create products 
        //add a viewer 
        //make viewer view all products 
        //give an option if user would like to subscribe to a product 
        //add a notification system which will allow the product to send the data to the user
        //user should also get notification 
        //thye should view the notification 
        //from the notification they should be able to go to the product
    }
}

class Notification{

    String Subject ;
    Viewer person ; 
    Product product ; 
    
    Notification(String subject , Viewer viewer , Product product){
        this.Subject = subject ; 
        this.person = viewer ; 
        this.product =  product ; 
    }

    public String getNotificationSubject(){return this.Subject ; }
    public Viewer getNotificationPerson(){return this.person; } 
    public String getNotificationProduct(){return this.product.getProductName() ; }
    public Product getProduct(){return this.product ; }
}

class Product implements NotificationEngine{
    String name ; 
    String id ; 
    String Manufacturer ; 
    Double price ;
    List<Product> allProducts ; 

    Product(String name , String id , String Manufacturer , Double price){
        this.name = name ; 
        this.id = id ; 
        this.Manufacturer = Manufacturer ;
        this.price = price ;
    }

    public Boolean createProduct(String name , String id , String Manufacturer , Double price){
        if (allProducts != null && allProducts.stream().anyMatch(pr -> pr.id.equals(id))) {
            return false;
        }
        Product p =  new Product(name , id , Manufacturer  , price) ; 
        allProducts.add(p) ;
        return true ; 
    }


    void displayInfo(){
        System.out.println("Product Name : " + name);
        System.out.println("Product ID : " + id);
        System.out.println("Manufacturer : " + Manufacturer);
        System.out.println("Price : " + price);
    }

    public String getProductName(){return this.name ; }
    public String getManufacturer(){return this.Manufacturer ; }
    public String getProductID(){return this.id ; }
    public Double getProductPrice(){return this.price  ; }
    public Product getProductByID(String id){
        if (allProducts == null || allProducts.isEmpty()) return null;
        return allProducts.stream()
                  .filter(p -> java.util.Objects.equals(id, p.id))
                  .findFirst()
                  .orElse(null);
    }

    public void sendNotification(Notification notification){
        Viewer person = notification.getNotificationPerson() ; 
        if (person == null || notification == null) return;
        if(person.getMySubscriptions().contains(notification.getProduct()))
            person.getAllMyNotification().add(notification) ; 
        
    }
    
    public Notification createNotification(Product product , Viewer person){
        String subject =  "Hey there are new updates on the product" + product.getProductName() + "\n" + "Take some time to check it out" + "\n" + "Product ID :" + product.getProductID() ;  
        Notification n =  new Notification(subject, person, product) ; 
        return n ; 

    }
}

class Viewer implements SubscriptionModel , Notifier{
    String Name ; 
    String id ; 
    List<Product> mySubscriptions ; 
    Stack<Notification> myNotifications ; 
    Viewer(String Name, String id ){
        this.Name = Name ; 
        this.id = id ; 
        this.mySubscriptions = new ArrayList<>() ; 
        this.myNotifications = new Stack<>() ;
    }

    public String getName(){return this.Name ; } 
    public String getId(){return this.id ; }
    public List<Product> getMySubscriptions(){ return this.mySubscriptions; }
    public Stack<Notification> getAllMyNotification(){return this.myNotifications ; }
    public Boolean hasSubscribed(Product product){
        for(Product prod : mySubscriptions){
            if(prod.getProductID().equals(product.getProductID())) return true; 
        }
        return false ; 
    }
    public void printMySubscriptions(){
        for(Product prod  : mySubscriptions){
            System.out.println("ID : " + prod.getProductID()); 
            System.out.println("Name : " + prod.getProductName());
            System.out.println("Cost : " + prod.getProductPrice());
        }
    }

    public void Subscribe(Product product){
        mySubscriptions.add(product ) ; 
        System.out.println("You have subscribed to : " + product.getProductName());
        
    }
    public void Unsubscribe(Product product){
        mySubscriptions.remove(product) ; 
        System.out.println("You have unsubscribed to : " + product.getProductName());
    }

    public void viewNotifications(){
        for(Notification n : myNotifications){
            System.out.println("Subject : "+ n.Subject);
            System.out.println("From : " +n.getNotificationProduct()) ;
        }
    }
    public void viewRecentNotification(){
        System.out.println(myNotifications.peek());
    }
}
interface SubscriptionModel{
    void Subscribe(Product product);
    void Unsubscribe(Product product);
}

interface NotificationEngine{
    Notification createNotification(Product product , Viewer Person);
    void sendNotification(Notification notification) ;  
}

interface Notifier{
    void viewNotifications() ; 
    void viewRecentNotification() ; 
}
