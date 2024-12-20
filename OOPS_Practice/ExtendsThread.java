public class ExtendsThread{
    public static void main(String[] args)
    {

        Thread t1 =new Thread(new Thread1()) ; 
        Thread t2 =new Thread(new Thread2()) ;

        t1.start() ; 
        t2.start() ;
    }
}

class Thread1 extends Thread{
    @Override
    public void run(){
        for(int i  =1 ; i <= 15 ; i++){
            System.out.println("Thread 1 is running " + i);
        }
    }
}

class Thread2 extends Thread{
    @Override 
    public void run(){
        for(int i = 1 ; i <= 15 ; i++){
            System.out.println("Thread 2 is running " + i);
        }
    }
}