public class MaxOnesRow {
    public static int rowWithMaxOnes(int[][] matrix) {
        int maxRowIndex = -1, maxOnes = 0;
        
        for (int i = 0; i < matrix.length; i++) {
            int countOnes = countOnesInRow(matrix[i]);
            if (countOnes > maxOnes) {
                maxOnes = countOnes;
                maxRowIndex = i;
            }
        }
        return maxRowIndex;
    }
    
    private static int countOnesInRow(int[] row) {
        int low = 0, high = row.length - 1;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (row[mid] == 1) {
                if (mid == 0 || row[mid - 1] == 0) return row.length - mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {0, 0, 0, 1},
            {0, 1, 1, 1},
            {1, 1, 1, 1},
            {0, 0, 0, 0}
        };
        System.out.println("Row with max 1s: " + rowWithMaxOnes(matrix));
    }
}
