/**
 * An extension of the Kuhn–Munkres assignment algorithm of the year 1957.
 * this implementation allows for non-square matrices by converting them to square matrices.
 * 
 * @author https://github.com/jnowak123 | May 2025
 * @version 1.0
 */

public class AssignmentAlgorithm {
    
    public static int[][] assign(double[][] matrix) {

        int rows = matrix.length;
        int cols = matrix[0].length;
        int maxSize = Math.max(rows, cols);

        // Convert the matrix to a square matrix, as the Hungarian algorithm requires a square matrix
        double[][] squareMatrix = new double[maxSize][maxSize];
        for (int i = 0; i < maxSize; i++) {
            for (int j = 0; j < maxSize; j++) {
                if (i < rows && j < cols) {
                    squareMatrix[i][j] = matrix[i][j];
                } else {
                    squareMatrix[i][j] = Double.MAX_VALUE; // Use a large value to indicate no assignment
                }
            }
        }

        // Run the Hungarian algorithm on the square matrix
        double[][] copyMatrix = new double[maxSize][maxSize];
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
            if (squareMatrix[assignment[i][1]][assignment[i][0]] != Double.MAX_VALUE) {
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
