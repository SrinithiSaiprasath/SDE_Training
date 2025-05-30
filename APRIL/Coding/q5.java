import java.util.Scanner;

public class q5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter the number of rows (m): ");
            int m = scanner.nextInt();
            System.out.print("Enter the number of columns (n): ");
            int n = scanner.nextInt();

            if (m <= 0 || n <= 0) {
                System.out.println("Invalid input");
                return;
            }
            int[][] matrix = new int[m][n];
            System.out.println("Enter the matrix elements:");
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (!scanner.hasNextInt()) {
                        System.out.println("Invalid input");
                        return;
                    }
                    matrix[i][j] = scanner.nextInt();
                }
            }

            System.out.println("Transposed matrix:");
            for (int j = 0; j < n; j++) {
                for (int i = 0; i < m; i++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Invalid input");
        } finally {
            scanner.close();
        }
    }
}