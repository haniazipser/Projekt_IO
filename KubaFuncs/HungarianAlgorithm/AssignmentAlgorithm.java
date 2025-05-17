/**
 * This class implements the assignment algorithm using the Hungarian method.
 * It takes a cost matrix as input and returns the optimal assignment in the form of (col, row) pairs.
 */

public class AssignmentAlgorithm {
    
    public static int[][] assign(int[][] matrix) {

        int rows = matrix.length;
        int cols = matrix[0].length;
        int maxSize = Math.max(rows, cols);

        // Convert the matrix to a square matrix, as the Hungarian algorithm requires a square matrix
        int[][] squareMatrix = new int[maxSize][maxSize];
        for (int i = 0; i < maxSize; i++) {
            for (int j = 0; j < maxSize; j++) {
                if (i < rows && j < cols) {
                    squareMatrix[i][j] = matrix[i][j];
                } else {
                    squareMatrix[i][j] = Integer.MAX_VALUE; // Use a large value to indicate no assignment
                }
            }
        }

        // Run the Hungarian algorithm on the square matrix
        int[][] copyMatrix = new int[maxSize][maxSize];
        for (int i = 0; i < maxSize; i++) {
            for (int j = 0; j < maxSize; j++) {
                copyMatrix[i][j] = squareMatrix[i][j];
            }
        }
        HungarianAlgorithm hungarianAlgorithm = new HungarianAlgorithm(copyMatrix);
        int[][] assignment = hungarianAlgorithm.findOptimalAssignment();

        // Filter the assignment to only include valid assignments
        int[][] result = new int[cols][2];
        int resultIndex = 0;
        for (int i = 0; i < assignment.length; i++) {
            if (squareMatrix[assignment[i][1]][assignment[i][0]] != Integer.MAX_VALUE) {
                result[resultIndex][0] = assignment[i][0];
                result[resultIndex][1] = assignment[i][1];
                resultIndex++;
            }
        }

        // Trim the result array to the actual size
        int[][] trimmedResult = new int[resultIndex][2];
        System.arraycopy(result, 0, trimmedResult, 0, resultIndex);

        return trimmedResult;
    }
}
