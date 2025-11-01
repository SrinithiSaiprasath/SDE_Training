public class q18 {
    public static String findRoots(int a, int b, int c) {
        int d = b * b - 4 * a * c;
        if (d < 0)
            return "No Real Roots";
        else {
            double root1 = (-b + Math.sqrt(d)) / (2.0 * a);
            double root2 = (-b - Math.sqrt(d)) / (2.0 * a);
            return " Real Roots: " + root1 + " and " + root2;
        }
    }

    public static void main(String[] args) {
        int a = 1, b = 4, c = 4; 
        System.out.println(findRoots(a, b, c));
    }
}
