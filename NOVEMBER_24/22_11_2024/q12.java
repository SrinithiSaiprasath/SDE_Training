public class q12{
    public static String findQuadrant(int x, int y) {
        if (x > 0 && y > 0) return "Quadrant 1";
        else if (x < 0 && y > 0) return "Quadrant 2";
        else if (x < 0 && y < 0) return "Quadrant 3";
        else if (x > 0 && y < 0) return "Quadrant 4";
        else return "On Axis";
    }

    public static void main(String[] args) {
        int x = 3, y = -2;
        System.out.println("The point lies in: " + findQuadrant(x, y));
    }
}

