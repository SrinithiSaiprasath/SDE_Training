import java.util.*;

class Vehicle {
    String make, model;

    Vehicle(String make, String model) {
        this.make = make;
        this.model = model;
    }

    void display() {
        System.out.println(make);
        System.out.println(model);
    }
}

class ElectricVehicle extends Vehicle {
    double batteryCapacity;
    boolean chargingStatus;

    ElectricVehicle(String make, String model, double batteryCapacity, boolean chargingStatus) {
        super(make, model);
        this.batteryCapacity = batteryCapacity;
        this.chargingStatus = chargingStatus;
    }

    @Override
    void display() {
        super.display();
        System.out.println(batteryCapacity + " kWh");
        System.out.println(chargingStatus ? "Charging" : "Not Charging");
    }
}

public class q17 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] vehicleData = sc.nextLine().split(" ");
        Vehicle vehicle = new Vehicle(vehicleData[0], vehicleData[1]);
        vehicle.display();

        String[] evData = sc.nextLine().split(" ");
        ElectricVehicle ev = new ElectricVehicle(evData[0], evData[1], Double.parseDouble(evData[2])
        , Boolean.parseBoolean(evData[3]));
        ev.display();

        sc.close();
    }
}
