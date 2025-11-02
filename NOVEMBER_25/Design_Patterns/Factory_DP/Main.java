package NOVEMBER_25.Design_Patterns.Factory_DP;

public class Main {
    public static void main(String[] args) {
        Restaurant pizzaShop = new PizzaRestaurant();
        Restaurant asianShop = new AsianRestaurant();
        Restaurant mexicanShop = new MexicanRestaurant();

        Food f1 = pizzaShop.orderFood("pizza");
        System.out.println(f1);

        Food f2 = asianShop.orderFood("rice");
        System.out.println(f2);

        Food f3 = mexicanShop.orderFood("burrito");
        System.out.println(f3);
    }
}
abstract class Food {
    protected String name;
    protected double price;

    Food(String name, double price) {
        this.name = name;
        this.price = price;
    }

    void prepare() {
        System.out.println("Preparing " + name + "...");
    }

    void pack() {
        System.out.println("Packing " + name + " for delivery.");
    }

    public String toString() {
        return String.format("%s (%.2f)", name, price);
    }
}
abstract class Restaurant {
    // Template method that uses the factory method createFood()
    public final Food orderFood(String item) {
        Food food = createFood(item);
        if (food == null) {
            throw new IllegalArgumentException("Item not available: " + item);
        }
        food.prepare();
        food.pack();
        System.out.println("Order placed: " + food);
        return food;
    }

    // Factory Method to be implemented by concrete restaurants
    protected abstract Food createFood(String item);
}
class Pizza extends Food {
    Pizza() {
        super("Pizza", 9.99);
    }

    @Override
    void prepare() {
        System.out.println("Stretching dough, adding sauce and cheese for Pizza.");
    }
}
class RiceBowls extends Food {
    RiceBowls() {
        super("Rice Bowl", 7.49);
    }

    @Override
    void prepare() {
        System.out.println("Cooking rice and adding toppings for Rice Bowl.");
    }
}
class Burrito extends Food {
    Burrito() {
        super("Burrito", 8.50);
    }

    @Override
    void prepare() {
        System.out.println("Assembling tortilla, rice, beans, and fillings for Burrito.");
    }
}
class PizzaRestaurant extends Restaurant {
    @Override
    protected Food createFood(String item) {
        if ("pizza".equalsIgnoreCase(item)) {
            return new Pizza();
        }
        // this restaurant only makes pizzas
        return null;
    }
}
class AsianRestaurant extends Restaurant {
    @Override
    protected Food createFood(String item) {
        if ("rice".equalsIgnoreCase(item) || "ricebowl".equalsIgnoreCase(item) || "rice bowl".equalsIgnoreCase(item)) {
            return new RiceBowls();
        }
        return null;
    }
}
class MexicanRestaurant extends Restaurant {
    @Override
    protected Food createFood(String item) {
        if ("burrito".equalsIgnoreCase(item)) {
            return new Burrito();
        }
        return null;
    }
}

/** food delivery app */
