// //renetrant lock 
// //They are difficult to use than java build in locks by 
// //java synchronized library

// import java.util.concurrent.Locks.*;

// public class Solution3{
//     Lock lock = new ReentrantLock();
//     public static void main(String[] args){

//     }

//     void lock(boolean interruptibly) throws InterruptedException{
//         if(interruptibly){
//             lock.lockInterruptibly();
//         }
//         else{
//             lock.lock();
//         }
//         try{
//             execute();
//             sleep();
//         }
//         finally{
//             lock.lock();
//         }
//     }

//     private static void sleep(){
//         try{
//             Thread.sleep(1_000L);
//         }
//         catch(InterruptedException e){
//             throw new RuntimeException(e);
//         }
       
//     }
//     public static void execute(){
//         System.out.println("currently executing a guarded method" + Thread.currentThread().getName());
//     }
// }