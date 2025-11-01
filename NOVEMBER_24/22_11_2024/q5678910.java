
//binary to octal 
//octal to binary
//decimal to octal 
//octal to decimal
// binary to decimal 
//decimal to binary 

import java.util.*;

public class q5678910 {
    public static void main(String[] args) {
        int a = 120 ; 
        // Int to  Binary
        String binary = Integer.toBinaryString(a) ; 
        System.out.println("Integer " + a + " in Binary is : " + binary );

        //Binaru to decimal
        String binary1 = Integer.toBinaryString(a) ; 
        int b1 = Integer.parseInt(binary1) ;  
        String bo = Integer.toOctalString(b1);

        System.out.println("Binary " + binary1 + " in Octal is : " + bo );
        System.out.println("Octal " + bo + " to Binary " + Integer.toBinaryString(b1))  ;

        System.out.println("decimal " + Integer.toUnsignedString(b1) + " in Octal "  + Integer.toOctalString(b1));
        System.out.println( "Octal "  + Integer.toOctalString(b1) + "decimal " + Integer.toUnsignedString(b1) );

        System.out.println("decimal " + Integer.toUnsignedString(b1) + " in Binary "  + Integer.toBinaryString(b1));
        System.out.println( " in Binary "  + Integer.toBinaryString(b1) + "decimal " + Integer.toUnsignedString(b1) );

    }
}
