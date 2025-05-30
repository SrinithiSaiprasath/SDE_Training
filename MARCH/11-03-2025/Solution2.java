class A extends Thread{
    public void run(){
        for(int i = 0 ; i <= 10 ; i++){
            System.out.println("A In show : " + "" + (i+1));
            try{            
                Thread.sleep(10);
            }
            catch(InterruptedException e){
                System.out.println("Try block is not executed");
            }
        }
    }
}

class B extends Thread{
    public void run(){
        for(int i = 0 ; i <= 10 ; i++){
            System.out.println("B In Show : "  + "" + (i+1));
        }
    }
}

public class Solution2{
    public static void main(String[] args){
        A obj1 = new A();
        B obj2 = new B();
        obj1.setPriority(Thread.MAX_PRIORITY); //8 : high priority
        obj2.setPriority(1); //3 : low priority
        System.out.println(obj1.getPriority()); //8 : normal priority
        System.out.println(obj2.getPriority()); // : normal priority
  
        obj1.start();
        obj2.start(); 
    }
}