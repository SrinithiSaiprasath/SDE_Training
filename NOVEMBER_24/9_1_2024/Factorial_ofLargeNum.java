package Date_9_1_2024;

import java.math.BigInteger;

public class Factorial_ofLargeNum {
    public static BigInteger find_factorial(int number) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= number; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    public static void main(String[] args) {
        int number1 = 52;
        System.out.println(find_factorial(number1));
    }
}
