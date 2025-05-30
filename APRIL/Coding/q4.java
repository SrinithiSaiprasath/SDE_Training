import java.util.Scanner;

public class q4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        for (int i = 1; i < n; i += 2) {
            arr[i] *= 2;
        }

        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        scanner.close();
    }
}
