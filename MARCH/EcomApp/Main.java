import java.util.*;

abstract class Person {
    protected String name;
    protected Long phoneNo;
    protected String address;
    protected String adhaarID;
    protected String mailID;
    protected String password;
    private static int userCount = 1;
    protected String id;

    public Person(String name, Long phoneNo, String address, String adhaarID, String mailID, String password) {
        this.name = name;
        this.phoneNo = phoneNo;
        this.address = address;
        this.adhaarID = adhaarID;
        this.mailID = mailID;
        this.password = password;
        this.id = name + "_" + userCount++;
    }

    String getName() {
        return name;
    }

    Long getPhoneNo() {
        return phoneNo;
    }

    String getAdhaarID() {
        return adhaarID;
    }

    String getMailID() {
        return mailID;
    }

    String getPassword() {
        return password;
    }

    String getID() {
        return id;
    }

    String getAddress() {
        return address;
    }
}

class AccountManager {
    private List<User> users = new ArrayList<>();
    public List<Administrator> administrators = new ArrayList<>();
    public List<Contractor> contractors = new ArrayList<>();
    public List<User> loggedInUser = new ArrayList<>();
    public List<WareHouse> wareHouses = new ArrayList<>();

    public void Register(String name, Long phoneNo, String Address, String Adhaar_ID, String mailID, String password) {
        User newUser = new User(name, phoneNo, Address, Adhaar_ID, mailID, password);
        users.add(newUser);
    }

    public AccountManager() {

        WareHouse wh1 = new WareHouse("WH", "WH1", new ArrayList<>());
        wareHouses.add(wh1);
        Administrator admin1 = new Administrator("Admin1", 123456789L, "Address1", "123456789", "main@mail.com",
                "123456789", new ArrayList<>(), this);
        administrators.add(admin1);
        Contractor contractor1 = new Contractor("Admin1", 123456789L, "Address1", "123456789", "main@mail.com",
                "123456789", new ArrayList<>());
        contractors.add(contractor1);
    }

    public void login(String id, String password) {
        for (User user : users) {
            if (user.getID().equals(id) && user.getPassword().equals(password)) {
                loggedInUser.add(user);
                System.out.println("Login Successful");
                return;
            }
        }
        System.out.println("Invalid Credentials");
    }

}

class User extends Person {
    String name;
    Long phoneNo;
    String Address;
    String Adhaar_ID;
    String mailID;
    String password;
    int usercount = 1;
    String id;

    public User(String name, Long phoneNo, String Address, String Adhaar_ID, String mailID, String password) {
        super(name, phoneNo, Address, Adhaar_ID, mailID, password);
    }

}

class Administrator extends Person {
    String name;
    Long phoneNo;
    String Address;
    String Adhaar_ID;
    String mailID;
    String password;
    int usercount = 1;
    String id;
    private List<Product> recievedProducts = new ArrayList<>();
    private AccountManager accountManager;

    public Administrator(String name, Long phoneNo, String Address, String Adhaar_ID, String mailID, String password,
            List<Product> recieveProducts, AccountManager accountManager) {
        super(name, phoneNo, Address, Adhaar_ID, mailID, password);
        this.recievedProducts = recieveProducts;
        this.accountManager = accountManager;
    }

    public Administrator(String name, Long phoneNo, String Address, String Adhaar_ID, String mailID, String password,
            List<Product> recieveProducts) {
        super(name, phoneNo, Address, Adhaar_ID, mailID, password);
        this.recievedProducts = recieveProducts;
    }

    public void viewRecievedProducts() {
        for (Product product : recievedProducts) {
            System.out.println("Name : " + product.getName() + "Cost: " + product.getCost() + "\n");
        }
    }

    List<Product> getRecievedProducts() {
        return recievedProducts;
    }

    public void AddProductsInWareHouse(String id, Product product) {
        for (WareHouse wh : accountManager.wareHouses) {
            if (wh.getmainID().equals(id)) {
                wh.getProducts().add(product);
                System.out.println("Product Added in WareHouse : " + wh.getName());
            } else
                System.out.println("WareHouse Not Found");
        }
    }

    public void viewWareHouse(){
                
    }
}

class WareHouse {
    String id;
    String name;
    static ArrayList<Product> products = new ArrayList<>();

    public WareHouse(String id, String name, ArrayList<Product> products) {
        this.name = name;
        this.id = id;
        this.products = products;

    }

    String getName() {
        return name;
    }

    String getmainID() {
        return id;
    }

    public void viewProducts() {
        for (Product product : products) {
            System.out.println("Name : " + product.getName());
            System.out.println("Cost: " + product.getCost());
            System.out.println("Available Count: " + (product.getSentCount() - product.getSoldCount()));
        }
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
}

class Contractor extends Person {
    String name;
    Long phoneNo;
    String Address;
    String Adhaar_ID;
    String mailID;
    String password;
    int usercount = 1;
    String id;
    private List<Product> sentProducts = new ArrayList<>();

    public Contractor(String name, Long phoneNo, String Address, String Adhaar_ID, String mailID, String password,
            List<Product> sentProducts) {
        super(name, phoneNo, Address, Adhaar_ID, mailID, password);
        this.sentProducts = sentProducts;
    }

    private AccountManager accountManager;

    public void sendProducts(String id, Product product) {
        Administrator ad = null;
        for (Administrator admins : accountManager.administrators) {
            if (admins.getID().equals(id)) {
                ad = admins;
                admins.getRecievedProducts().add(product);

            }
        }
        if (ad != null) {
            System.out.println("Product : " + product.getName() + " sent to Admin " + ad.getName());
        } else {
            System.out.println("Admin with ID " + id + " not found.");
        }
    }
}

class Customer extends User {
    Scanner sc = new Scanner(System.in);
    Cart MyCart;
    HashMap<String, Integer> boughtItems = new HashMap<>();

    public Customer(String name, Long phoneNo, String Address, String Adhaar_ID, String mailID, String password,
            Cart Cart, HashMap<String, Integer> boughtProducts) {
        super(name, phoneNo, Address, Adhaar_ID, mailID, password);
        this.boughtItems = boughtProducts;
        this.MyCart = Cart;
    }

    public void viewProducts() {
        for (Product product : WareHouse.products) {
            System.out.println("Name: " + product.getName() + "\n" + "Cost" + product.getCost() + "\n"
                    + "AvailableCount: " + (product.getAvailableCount()) + "\n");
        }
    }

    public void buyProducts(String name, int count) {
        for (Product pd : WareHouse.products) {
            if (pd.getName().equalsIgnoreCase(name) && (pd.getAvailableCount()) >= count) {
                pd.setSoldCount(count);
                pd.setAvailableCount(count);
                boughtItems.put(name, count);
                double cost = pd.getCost() * count;
                System.out.println("Current Amount To be Paid: " + cost + " Rs");
                double cash = 1000.0;
                String verifyPayment = "I PAID THE AMOUNT";

                if (verifyPayment.equals("I PAID THE AMOUNT") && Math.abs(pd.getCost() - cash) < 0.01) {
                    System.out.println("Purchase Complete");
                } else {
                    System.out.println("Transaction Failed: Incorrect Payment");
                }
                return;
            }
        }
        System.out.println("Product Not Available");
    }

    public void addProductsToCart(String name, int count) {
        for (Product product : WareHouse.products) {
            if (product.getName().equalsIgnoreCase(name)) {
                MyCart.getMyCart().put(product, count);
                System.out.println("Product Added to Cart");
            }
        }
    }

    public void viewMyCart() {
        for (Map.Entry<Product, Integer> entry : MyCart.getMyCart().entrySet()) {
            Product prod = entry.getKey();
            Integer count = entry.getValue();
            System.out.println("Product: " + prod);
            System.out.println("Count: " + count);
            System.out.println("Cost: " + prod.getCost() * count + "Rs");

        }
    }

    public void buyFromCart(String name, int count) {
        for (Map.Entry<Product, Integer> entryset : MyCart.getMyCart().entrySet()) {
            Product p = entryset.getKey();
            int c = entryset.getValue();

            for (Product pd : WareHouse.products) {
                if (pd.getName().equalsIgnoreCase(name) && (pd.getSentCount() - pd.getSoldCount()) > count) {
                    pd.setSoldCount(count);
                    pd.setAvailableCount(count);
                    boughtItems.put(name, count);
                    double cost = pd.getCost() * count;
                    System.out.println("Current Amount To be Paid:  " + cost + "Rs");
                    System.out.println("Enter the cash amount in decimals to proceed payment :- ");
                    // double cash = sc.nextDouble();
                    double cash = 1000.0;
                    System.out.println("Write - 'I PAID THE AMOUNT ' to complete Transaction ");
                    // String verifyPayment = sc.next();
                    String verifyPayment = "I PAID THE AMOUNT";

                    if (verifyPayment.equals("I PAID THE AMOUNT") && pd.getCost() == cash) {
                        System.out.println("Purchase Complete");
                    }
                    System.out.println("Product Bought");
                } else {
                    System.out.println("Product Not Available");
                }
            }
        }
    }
}

class Cart {
    Product product;
    int count;
    HashMap<Product, Integer> myCart = new HashMap<>();

    public Cart(Product product, int count) {
        this.product = product;
        this.count = count;
    }

    public HashMap<Product, Integer> getMyCart() {
        return myCart;
    }

}

class Product {
    public Product(String name, String id, String purpose, String genre, double cost, int sentCount, int soldCount) {
        this.name = name;
        this.id = id;
        this.purpose = purpose;
        this.genre = genre;
        this.cost = cost;
        this.sentCount = sentCount;
        this.soldCount = soldCount; // increase after each buy
        this.availableCount = sentCount; // decrease after each buy
    }

    private String name;
    private String id;
    private String purpose;
    private String genre;
    private double cost;
    private int sentCount;
    private int availableCount;
    private int soldCount;

    public int getAvailableCount() {
        return availableCount;
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return id;
    }

    public String getPurpose() {
        return purpose;
    }

    public double getCost() {
        return cost;
    }

    public int getSentCount() {
        return sentCount;
    }

    public int getSoldCount() {
        return soldCount;
    }

    public void setSoldCount(int count) {
        this.soldCount += count;
    }

    public void setAvailableCount(int count) {
        this.availableCount -= count;
    }
}

public class Main {
    public static void main(String[] args) {
        AccountManager accountManager = new AccountManager();

        accountManager.Register("User1", 9876543210L, "Address1", "123456789", "user1@mail.com", "password1");
        accountManager.Register("User2", 9123456789L, "Address2", "987654321", "user2@mail.com", "password2");

        accountManager.login("User1_1", "password1");
        accountManager.login("User2_2", "password2");

        Product product1 = new Product("Product1", "P001", "For Use", "Genre1", 100.0, 50, 10);
        Product product2 = new Product("Product2", "P002", "For Sale", "Genre2", 200.0, 40, 5);

        accountManager.administrators.get(0).AddProductsInWareHouse("WH1", product1);
        accountManager.administrators.get(0).AddProductsInWareHouse("WH1", product2);

        accountManager.wareHouses.get(0).viewProducts();

        accountManager.contractors.get(0).sendProducts("Admin1_1", product1);
        accountManager.administrators.get(0).viewRecievedProducts();

        if (!accountManager.loggedInUser.isEmpty() && accountManager.loggedInUser.get(0).getID().equals("User1_1")) {
            User loggedInUser = accountManager.loggedInUser.get(0);
            if (loggedInUser instanceof User) {
                Customer customer = new Customer(loggedInUser.getName(), loggedInUser.getPhoneNo(),
                        loggedInUser.getAddress(), loggedInUser.getAdhaarID(), loggedInUser.getMailID(),
                        loggedInUser.getPassword(), new Cart(null, 0), new HashMap<>());

                // Customer Views Products
                customer.viewProducts();

                // Customer Adds Products to Cart
                customer.addProductsToCart("Product1", 2);
                customer.addProductsToCart("Product2", 1);

                // Customer Views Cart
                customer.viewMyCart();

                // Customer Buys Products from Cart
                customer.buyFromCart("Product1", 1);
                customer.buyFromCart("Product2", 1);

                // Customer Buys Products directly
                customer.buyProducts("Product1", 1);
            }
        }
    }
}

