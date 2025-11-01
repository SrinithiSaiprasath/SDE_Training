public class q16 {
    public static int countDigit3(int n) {
        int count = 0, factor = 1, start = 0, current = 0, stop = 0;

        while (n / factor != 0) {
            start = n - (n / factor) * factor;
            current = (n / factor) % 10;
            stop = n / (factor * 10);

            if (current < 3)
                count += stop * factor;
            else if (current == 3)
                count += stop * factor + start + 1;
            else
                count += (stop + 1) * factor;

            factor *= 10;
        }
        return count;
    }

    public static void main(String[] args) {
        int n = 50;
        System.out.println("Count of digit 3: " + countDigit3(n));
    }
}
