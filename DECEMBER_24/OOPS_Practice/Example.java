class Car{
    String CarName ; 
        static String CarColor ; 
        static String CarModel ;
        static String CarType ;
        private int CarPrice ;
        
        
    
        Car(String CarName,String CarColor,String CarModel,String CarType,int CarPrice){
            this.CarName = CarName ;
        this.CarColor = CarColor ;
        this.CarModel = CarModel ;
        this.CarType = CarType ;
        this.CarPrice = CarPrice ;
    }

    void getCarData(Car c){
        System.out.println("Car Name : "+c.CarName);
        System.out.println("Car Color : "+c.CarColor);
        System.out.println("Car Model : "+c.CarModel);
        System.out.println("Car Type : "+c.CarType);
        System.out.println("Car Price : "+c.CarPrice);    
    }

    void setCardData(Car c ,String CarName,String CarColor,String CarModel,String CarType,int CarPrice){
        c.CarName = CarName ;
        c.CarColor = CarColor ;
        c.CarModel = CarModel ;
        c.CarType = CarType ;
        c.CarPrice = CarPrice ;
    }
}

public class Example{
    public static void main(String[] args) {
        Car c = new Car("Toyota","Red","Camry","Sedan",20000);
        c.getCarData(c);
        Car c1  = new Car("Honda","Black","Civic","Sedan",30000);
        c1.getCarData(c1);
        c1.setCardData(c1,"Honda","Black","Civic","Sedan",50000);
        c1.getCarData(c1);
    }
}