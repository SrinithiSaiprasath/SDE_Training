public class RunnableThread {
    public static void main(String[] args){
        Thread one = new Thread(new thread1()) ; 
        Thread two = new Thread(new thread2()) ; 
        Thread three = new Thread(() ->{
            for(int i = 1 ; i <= 15 ; i++){
                System.out.println("Thread 3 is running "+ i) ; 
            }
        }) ; 

        one.start() ; 
        two.start() ; 
        three.start() ; 
    }
}

class thread1 implements Runnable{
    @Override
    public void run(){
        for(int  i = 1  ; i <= 10 ; i++)System.out.println("Thread 1 is running " + i);
    }
}

class thread2 implements Runnable{
    @Override
    public void run(){
        for(int  i = 1  ; i <= 15 ; i++)System.out.println("Thread 2 is running " + i);
    }
}

