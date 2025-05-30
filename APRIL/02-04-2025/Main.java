import java.util.* ; 
//parent class : Shape
abstract class Shape{
    double s1 , s2 , s3 ; 
    double l ,  b ; 
    double s , r ; 
    String color ; 
    boolean oneSides90 ; 
    //def 
    public Shape(){
        this.color = "" ; 
    }
    //cir
    public Shape(double r){
        this.r = r ; 
        this.color = "" ; 
    }
    //rect
    public Shape(double l , double b){
        this.l = l ; 
        this.b = b ; 
    }
    public Shape(double s1 , double s2 , double s3 , boolean oneSides90){
        this.s1= s1 ;
        this.s2= s2;
        this.s3 = s3 ; 
        this.color = "" ; 
        this.oneSides90 = oneSides90 ; 
    }
    // public Shape(double s){
    //     this.s = s ; 
    // }
    abstract double getArea();
    abstract double getPerimeter();
    abstract String getColor();
    abstract void setColor(String color);
    abstract void getAllDetails();
}

//child class 1 of shape : Circle 
//hierarchial inheritance Shape -> Circle
class Circle extends Shape{

    public Circle(double r){
        super(r) ;  
    }
    public double getRadius(){return super.r  ;}
    public String getColor(){return super.color ; }
    public void setColor(String color ){super.color =  color ; }

    public double getArea(){
        double r =  getRadius() ; 
        double res =  Math.PI* r * r ;
        System.out.println("Area :" + res) ; 
        return Math.PI *r*r ; 
    }
    public double getPerimeter(){ 
        double r =  getRadius() ; 
        double res =  Math.PI* r * 2  ;
        System.out.println("Perimeter :" + res);
        return res  ; 
    }
    public void getAllDetails(){
        System.out.println("Circle Details: Radius = " + r + ", Color = " + getColor());
    }

}

//child class 2 of shape : Triangle
//hierarchial inheritance Shape -> Triangle
class Triangle extends Shape{
    
    public Triangle(double s1 , double s2 , double s3 , boolean oneSides90){
        super(s1, s2, s3 , oneSides90); 
    }

    public double gets1(){return super.s1 ; }
    public double gets2(){return super.s2 ; }
    public double gets3(){return super.s3 ; }

    public double getArea(){
        double l = gets1();
        double b = gets2();
        double h = gets3(); 
        if(super.oneSides90){
            return 0.5 * l * b ;  
        }
        else{
            double s = (l+b+h)/ 12 ;
            return Math.sqrt(s*(s-l) + s*(s-b) + s*(s-h)) ; 
        }
    }

    public double getPerimeter(){
        double l = gets1();
        double b = gets2();
        double h = gets3();
        return l+b+h ;
    }
    public String getColor(){
        return super.color ; 
    }
    public void setColor(String color){
        super.color = color; 
    }
    public void getAllDetails(){
        System.out.println("Triangle  Details: s1= " + gets1() +"s2: " + gets2() + "s3: "  + gets3() + ", Color = " + getColor());

    }
}

//child class 3 of shape : Rectangle 
//hierarchial inheritance Shape -> Rectangle
class Rectangle extends Shape {
    public Rectangle(double l , double b){
        super(l,b)  ; 
    }
    public Rectangle(double s){
        super(s ) ;
    }
    public double getLength(){return super.l ;}
    public double getBreadth(){return super.b ; }

    public double getArea(){
        double l = getLength();
        double b = getBreadth();
        System.out.println("Area: "+ l*b);
        return l*b ; 
    }
    public double getPerimeter(){
        double l = getLength();
        double b = getBreadth();
        System.out.println("Perimeter: " + 2*(l+b));
        return 2*(l+b) ; 
    }
    public String getColor(){
        return super.color ; 
    }
    public void setColor(String color){super.color =  color ; }
    public void getAllDetails(){
        System.out.println("Rectangle  Details: Length= " + getLength() +"Breadth: " + getBreadth() + ", Color = " + getColor());

    }

}

//child class 1 of Rectangle:  Square
//multilevel inheritance : Shape -> Rectangle -> Square
class Square extends Rectangle{
    public Square(double s1 , double s2){
        super(s1 ,s2) ; 
    }
    public double getSide(){
        return super.l ; 
    }
    public double getArea(){
        double s = getSide();
        System.out.println("Area: " + s*s);
        return s*s ; 
    }
    public double getPerimeter(){
        double s = getSide() ; 
        System.out.println("Perimeter: " + 4*s);
        return 4*s;
    }
    public String getColor(){
        return super.color ; 
    }
    public void setColor(String color){
        super.color = color ; 
    }
    public void getAllDetails(){
        System.out.println("Square  Details: Side= " +  getSide() +", Color = " + getColor());

    }
}

//main class
public class Main {
    public static void main(String[] args) {
        List<Shape> myShapes = new ArrayList<>();
        Scanner sc = new Scanner(System.in) ; 
        boolean continueCreating = true;
        while(continueCreating){
            System.out.println("do you want to create a shape y/n");
            String c = sc.next();
            if(c.toLowerCase().equals("y")){
                System.out.println("Enter the number of Edges of the shape");
                int n = sc.nextInt();
                switch(n){
                    case 0:
                        System.out.println("Enter the radius of the circle");
                        int radius =  sc.nextInt();
                        Shape mycircle =  new Circle((double) radius) ;
                        System.out.println("Circle Created"); 
                        System.out.println("What color you want to add to circle: ");
                        String color = sc.next();
                        mycircle.setColor(color);
                        mycircle.getAllDetails();
                        System.out.println("Perimeter: " + mycircle.getArea());
                        System.out.println("Area:  " + mycircle.getPerimeter());
                        myShapes.add(mycircle)  ; 
                        break ; 

                    case 3:
                        System.out.println("Enter the sides of the Triangle");
                        double s1 = sc.nextInt();
                        double s2 =  sc.nextInt() ; 
                        double s3 = sc.nextInt() ; 
                        System.out.println("Is there a side of Triangle which is 90deg");
                        boolean is90 =  sc.nextBoolean();
                        Shape mytriangle = new Triangle(s1, s2, s3, is90) ;
                        System.out.println("Enter the color you want to give to your triangle");
                        String color11 = sc.next() ;
                        mytriangle.setColor(color11);
                        System.out.println("Perimeter: " + mytriangle.getPerimeter());
                        System.out.println("Area: " + mytriangle.getArea());
                        mytriangle.getAllDetails();
                        myShapes.add(mytriangle) ; 
                        break ;
                    case 4:
                        System.out.println("Are all the sides of the Quadrilateral Equal");
                        boolean ch = sc.nextBoolean();
                        //square
                        if(ch){
                            System.out.println("Enter the Length of side of Square ");
                            int s= sc.nextInt();
                            Shape mysquare = new Square(s , s);
                            System.out.println("Square Created");
                            System.out.println("Enter color you want to add");
                            String co = sc.next();
                            mysquare.setColor(co);
                            System.out.println("Area: " + mysquare.getArea()) ;
                            System.out.println("Perimeter: " + mysquare.getPerimeter()); 
                            mysquare.getAllDetails();
                            myShapes.add(mysquare) ; 
                            break ;
                        }
                        else{
                            //rectangle
                            System.out.println("Are all angles in 90 deg");
                            boolean y = sc.nextBoolean() ; 
                            if(y){
                                System.out.println("Enter the Length of side of Rectangle ");
                                int l = sc.nextInt();
                                System.out.println("Enter the breadth of side of Rectangle ");
                                int b = sc.nextInt();
                                Shape myRectangle = new Rectangle(l , b);
                                System.out.println("Rectangle  Created");
                                System.out.println("Enter color you want to add");
                                String co = sc.next();
                                myRectangle.setColor(co);
                                System.out.println("Area: " + myRectangle.getArea()) ;
                                System.out.println("Perimeter: " + myRectangle.getPerimeter()); 
                                myRectangle.getAllDetails();
                                myShapes.add(myRectangle) ; 
                                break ;
                            }
                        }
                    default:
                        System.out.println("Invalid Input");
                }
            } else if (c.toLowerCase().equals("n")) {
                continueCreating = false;
            } else {
                System.out.println("Invalid Input. Please enter 'y' or 'n'.");
            }
        }
        sc.close();
    }
}
    


/**
 * System.out.println(myShapes);
        Circle c = new Circle(7.0) ; 
        System.out.println(c.getPerimeter());
        System.out.println(c.getArea());
        c.setColor("Blue"); 
        System.out.println(c.getColor());

        Shape r = new Rectangle(10.0, 12.5) ; 
        System.out.println(r.getPerimeter());
        System.out.println(r.getArea());
        r.setColor("Blue"); 
        System.out.println(r.getColor());

        Shape t = new Triangle(3.0, 4.0, 5.0, true) ; 
        System.out.println(t.getPerimeter());
        System.out.println(t.getArea());
        t.setColor("Blue"); 
        System.out.println(t.getColor());

        Rectangle s = new Square(4.0 , 4.0) ; 
        System.out.println(s.getPerimeter());
        System.out.println(s.getArea());
        s.setColor("Blue"); 
        System.out.println(s.getColor());
 */