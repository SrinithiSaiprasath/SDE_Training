public class Q3 {
    public static void main(String[] args) throws InterruptedException{
        System.out.println("With Space " +"Good Morning \r Science Tech is Easy "); //with space
        System.out.println();
        System.out.println("Without space " + "Good Morning\rScience_Tech is Easy "); //without space
        System.out.println();
        for(int i = 0 ; i < 4 ; i++){
            System.out.print("\rProgress : i = " + i + "%");
            System.out.flush();
            Thread.sleep(1000) ; 
        }
    }
}


//WORKING OF \r in diffrent environments 

/**
 * [1] In VSC or CMD or OC
 * When used in System.out.print() or 
 * System.out.printf(), the output after \r
 *  overwrites the existing content of the same line.
 * Eg: Progress Bars
 * [2]In Eclipse (does not act as a true carriage return )
 * It may treat \r as a newline or render it literally.
 * It gives the output in 2 lines and acts as a 
 */

//Purpose of flush : 
//Forces Immediete 
// output to the current line reducing multiple lines (eg: progress bar)

//Purpose of \r
// More the cursor and over write the content before it 

//both workign togerther  Immediate output is
// recieved and there is dynamic output 