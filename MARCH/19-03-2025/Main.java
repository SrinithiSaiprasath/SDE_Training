import java.sql.Array;
import java.util.*;

class Account{
    List<User> users =  new ArrayList<>();
    List<Restaurant> restaurants =  new ArrayList<>();
    HashMap<String, User> userMap = new HashMap<>();
    HashMap<String, Restaurant> restaurantMap = new HashMap<>();
    public Account(){}
    public boolean checkUniqueMail(String mail){
        for(User user : users){
            if(user.getEmail().equals(mail)){
                System.out.println("Mail ID already exists");
                break ; 
            }
        }
        return true;
    }
    public boolean checkPassowrdMatch(String id, String password){
        for(User user : users){
            if(user.getID().equals(id)&& user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
    
    public void Register(String name , String id , String Address , String City , String State , String ContactNo , String Email , String Password , String DOB){
        if(userMap.containsKey(id) && checkUniqueMail(Email)){
            System.out.println("User already exists");
            return;
        }
        else{
            User user = new User(name , id , Address , City , State , ContactNo , Email , Password , DOB);
            userMap.put(id , user);
            users.add(user) ;
            System.out.println("Your account has been created");
        }
    }
    public void login(String id , String Password){
        if(userMap.containsKey(id) && checkPassowrdMatch(id, Password) ){
            System.out.println("Your are logged In as User: " + id);
        }
    }
}

class User{
    private String name ;
    private String id ; 
    private String Adderss ; 
    private String City;
    private String State;
    private String ContactNo;
    private String Email;
    private String Password;
    private String DOB;
    public User(String name, String id , String Address , String City , String State , String ContactNo , String Email , String Password , String DOB){
        this.name = name;
        this.id = id;
        this.Adderss = Address;
        this.City = City;
        this.State = State;
        this.ContactNo = ContactNo;
        this.Email = Email;
        this.Password = Password;
        this.DOB = DOB;
    }
    public User(){}
    public String getName() {return name;}
    public String getID(){return id ; }
    public String getAddress(){return Adderss; }
    public String getCity(){return City;}
    public String getState(){return State;}
    public String getContactNo(){return ContactNo;}
    public String getEmail(){return Email;}
    public String getPassword(){return Password;}
    public String getDOB(){return DOB;}
    public void setName(String name){this.name = name;}
    public void setID(String id){this.id = id;}
    public void setAddress(String Address){this.Adderss = Address;}
    public void setPassowrd(String Password){this.Password = Password;}
}

class Restaurant{
    String name;
    String id ; 
    String password ; 
    String type ; 
    List<Food> menu =  new ArrayList<>();
    public Restaurant(String name , String id , List<Food> menu, String type){
        this.name = name;
        this.type =  type ; 
        this.id = id;
        this.menu = menu;
    }

    public Restaurant(){}
    public String getName(){return name;}
    public String getID(){return id;}
    public String getPassword(){return password;}
    public List<Food> getMenu(){return menu;}
    public void setName(String name){this.name = name;}
    public void setID(String id){this.id = id;}
    public void setPassword(String password){this.password = password;}
    public void setMenu(List<Food> menu){this.menu = menu;}

    public void viewMenu(){
        for(Food food : menu){
            System.out.println("Name: " + food.getName() + " ID: " + food.getID() + " Type: " + food.getType() + " Price: " + food.getPrice() + " Ingredients: " + food.getIngredients());
        }
    }
    
    public void viewBasedOnType(String type){
        for(Food food : menu){
            if(food.getType().equals(type)){
                System.out.println("Name: " + food.getName() + " ID: " + food.getID() + " Type: " + food.getType() + " Price: " + food.getPrice() + " Ingredients: " + food.getIngredients());
            }
        }
    }
    public void addFood(String name , String id , String type , String price , List<String> ingredients){
        Food food = new Food(name , id , type , price , ingredients);
        menu.add(food);
    }
    public void removeFood(String id){
        for(Food food : menu){
            if(food.getID().equals(id)){
                menu.remove(food);
                break;
            }
        }
    }
    public void updateFood(String id , String name , String type , String price , List<String> ingredients){
        for(Food food : menu){
            if(food.getID().equals(id)){
                food.setName(name);
                food.setType(type);
                food.setPrice(price);
                food.getIngredients().clear();
                food.getIngredients().addAll(ingredients);
                break;
            }
        }
    }

    public void updateStatus(String orderID){
        for(Order order : Order.orders){
            if(order.getID().equals(orderID)){
                order.setStatus("Delivered");
                break;
            }
        }
    }
}
class OrderHistory{
    private String id ; 
    private String userID ; 
    private String restaurantID ; 
    private String foodID ; 
    private String status ; 
    private String date ; 
    private String time ; 
    public OrderHistory(String id , String userID , String restaurantID , String foodID , String status , String date , String time){
        this.id = id;
        this.userID = userID;
        this.restaurantID = restaurantID;
        this.foodID = foodID;
        this.status = status;
        this.date = date;
        this.time = time;
    }
    public OrderHistory(){}
    public String getID(){return id;}
    public String getUserID(){return userID;}
    public String getRestaurantID(){return restaurantID;}
    public String getFoodID(){return foodID;}
    public String getStatus(){return status;}
    public String getDate(){return date;}
    public String getTime(){return time;}
    public void setID(String id){this.id = id;}
    public void setUserID(String userID){this.userID = userID;}
    public void setRestaurantID(String restaurantID){this.restaurantID = restaurantID;}
    public void setFoodID(String foodID){this.foodID = foodID;}
    public void setStatus(String status){this.status = status;}
    public void setDate(String date){this.date = date;}
    public void setTime(String time){this.time = time;}
}
class Food{
    private String name ;
    private String id ; 
    private String type ;
    private String price ;
    private List<String> ingredients = new ArrayList<>();
    public Food(String name , String id , String type , String price , List<String> ingredients){
        this.name = name;
        this.id = id;
        this.type = type;
        this.price = price;
        this.ingredients = ingredients;
    }
    public Food(){}
    public String getName(){return name;}
    public String getID(){return id;}
    public String getType(){return type;}
    public String getPrice(){return price;}
    public List<String> getIngredients(){return ingredients;}
    public void setName(String name){this.name = name;} 
    public void setID(String id){this.id = id;}
    public void setType(String type){this.type = type;}
    public void setPrice(String price){this.price = price;}
}
class Order{
    private String id ; 
    private String userID ;
    private String restaurantID ;
    private String foodID ;
    private String status ;
    private String date ;
    private String time ;
    private String userAddress ; 
    private String userContactNo ;
    public static ArrayList<Order> orders = new ArrayList<>();
    public Order(String id , String userID , String restaurantID , String foodID , String status , String date , String time , String userAddress , String userContactNo){
        this.id = id;
        this.userID = userID;
        this.restaurantID = restaurantID;
        this.foodID = foodID;
        this.status = status;
        this.date = date;
        this.time = time;
        this.userAddress = userAddress;
        this.userContactNo = userContactNo;
    }
    public Order(){}
    public String getID(){return id;}
    public String getUserID(){return userID;}
    public String getRestaurantID(){return restaurantID;}
    public String getFoodID(){return foodID;}
    public String getStatus(){return status;}
    public void setStatus(String status){this.status = status ; }  
    public String getDate(){return date;}
    public String getTime(){return time;}
    public String getUserAddress(){return userAddress;}
    public String getUserContactNo(){return userContactNo;}
    public void searchByOrder(String orderID){
        for(Order order : orders){
            if(order.getID().equals(orderID)){
                System.out.println("Order ID: " + order.getID() + " User ID: " + order.getUserID() + " Restaurant ID: " + order.getRestaurantID() + " Food ID: " + order.getFoodID() + " Status: " + order.getStatus() + " Date: " + order.getDate() + " Time: " + order.getTime() + " User Address: " + order.getUserAddress() + " User Contact No: " + order.getUserContactNo());
            }
        }
    }
    public void searchByUser(String userID){
        for(Order order : orders){
            if(order.getUserID().equals(userID)){
                System.out.println("Order ID: " + order.getID() + " User ID: " + order.getUserID() + " Restaurant ID: " + order.getRestaurantID() + " Food ID: " + order.getFoodID() + " Status: " + order.getStatus() + " Date: " + order.getDate() + " Time: " + order.getTime() + " User Address: " + order.getUserAddress() + " User Contact No: " + order.getUserContactNo());
            }
        }
    }
    public void searchByRestaurant(String restaurantID){
        for(Order order : orders){
            if(order.getRestaurantID().equals(restaurantID)){
                System.out.println("Order ID: " + order.getID() + " User ID: " + order.getUserID() + " Restaurant ID: " + order.getRestaurantID() + " Food ID: " + order.getFoodID() + " Status: " + order.getStatus() + " Date: " + order.getDate() + " Time: " + order.getTime() + " User Address: " + order.getUserAddress() + " User Contact No: " + order.getUserContactNo());
            }
        }
    }
    public void searchByFood(String foodID){
        for(Order order : orders){
            if(order.getFoodID().equals(foodID)){
                System.out.println("Order ID: " + order.getID() + " User ID: " + order.getUserID() + " Restaurant ID: " + order.getRestaurantID() + " Food ID: " + order.getFoodID() + " Status: " + order.getStatus() + " Date: " + order.getDate() + " Time: " + order.getTime() + " User Address: " + order.getUserAddress() + " User Contact No: " + order.getUserContactNo());
            }
        }
    }
}
class Cart{
    private String id ; 
    private String cartName ; 
    private String userID ; 
    private String restaurantID ; 
    private String foodID ; 
    private String date ; 
    private String time ; 
    public static ArrayList<Cart> carts = new ArrayList<>();
    public Cart(String id, String cartName, String userID, String restaurantID, String foodID , String date, String time, int count){
        this.id = id;
        this.cartName = cartName;
        this.userID = userID;
        this.restaurantID = restaurantID;
        this.foodID = foodID;
        this.date = date;
        this.time = time;
    }
    public Cart(){}
    public String getID(){return id;}
    public String getUserID(){return userID;}
    public HashMap<String , ArrayList<Food>> foodOrder =  new HashMap<>();
    public String getDate(){return date;}
    public String getTime(){return time;}
    public HashMap<String , ArrayList<Food>> getFoodOrder(){return foodOrder;}
    public Cart getCart(String id){
        Cart c = null ; 
        for(Cart cart : carts){
            if(cart.getID().equals(id)){
                return cart ; 
            }
            else{
                System.out.println("Cart not found");
            }
        }
        return c ; 
    }

    public void addFoodToCart(String id , List<Food> food){
        foodOrder.put(id , (ArrayList<Food>) food);
    }

    public void createCart( String id , String name , String userID , String restaurantID , List<Food> food , int count){
        Cart cart = new Cart(id , name , userID , restaurantID , "foodID_placeholder" , "12-03-2025" , "12:00" , count);
        carts.add(cart);
    }   

    public void removeFromCart(String id){
        for(Cart cart : carts){
            if(cart.getID().equals(id)){
                carts.remove(cart);
                break;
            }
        }
    }

    public void getAllFood(ArrayList<Food> food){

    }
    
    public void viewCarts(){
        for(Cart c : carts){
            System.out.println("Cart ID: " + "\n" +c.getID() +"\n"+  " Cart Name: " +"\n"+ c.cartName + "\n"+
            " User ID: " + c.getUserID() +"\n"+ " Restaurant ID: " +"\n"+
            //   c.getRestaurantID() +
             " Food: " +
            //   c.getFoodID() + "/n" + 
               " Date: " + c.getDate()
                + " Time: " +
             c.getTime());
        }
    }
    
    public void proceedToBuy(Cart id , Cart Name){
        System.out.println("Proceeding to buy");
        for(Cart cart : carts){
            if(cart.getID().equals(id)){
                carts.remove(cart);
                break;
            }
        }

    }
}
class Payment{
    String userID;
    Cart cart ;      
    public Payment(String userID , Cart cart){
        this.userID = userID;
        this.cart = cart;
    }
    public Payment(){}
    public void makePayment(String userID , Cart cart){
        System.out.println("Payment made successfully");
    }

}
class Favourite{

}
class Menu{
    static Scanner sc = new Scanner(System.in);
    Account accountManager =  new Account() ; 
    public  int getIntInput(){
        int c = sc.nextInt();
        return c ; 
    }
    public void login1(){
        String id = sc.next() ; 
        String password =  sc.next();
        // accountManager.login(id , password) ; 
    }
    public void RegsiterOps(){
        String name = sc.next();
        String id = sc.next();
        String Address =sc.next();
        String City =sc.next();
        String State  = sc.next();
        String ContactNo = sc.next();
        String Email =  sc.next();
        String Password = sc.next();
        String DOB = sc.next();
        // accountManager.Register(name , id , Address , City , State , ContactNo , Email , Password , DOB) ; 
    }
    public void RestrauntOps(){

    }
    public void FoodOps(){

    }
    public void OrderOps(){

    }
    public void CartOps(){

    }
    public void FavouiteOps(){

    }
}
public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        System.out.println("Welcome to DeCaprio");
        System.out.println("Enter your choice");
        while(true){
            System.out.println("1.Login \n 2.Register \n 3.Exit");
            int c = menu.getIntInput();
            switch (c) {
                case 1:
                    // menu.login();                    
                    break;
                case 2:
                    menu.RegsiterOps();
                    break ; 
                case 3 :
                    break ; 
                default:
                    break;
            }

        }
    }
}