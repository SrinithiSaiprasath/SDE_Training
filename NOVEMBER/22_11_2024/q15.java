public class q15{
    public static long calculatePermutations(int n, int r) {
        long result = 1;
        for (int i = 0; i < r; i++) {
            result *= (n - i);
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 5, r = 3; 
        System.out.println("Permutations: " + calculatePermutations(n, r));
    }
}