/**
 * Self-contained test harness for AssignmentAlgorithm edge cases.
 * Includes tests for various matrix shapes and values,
 * including those containing Integer.MAX_VALUE.
 */

public class AssignmentAlgorithmTest {

    public static void main(String[] args) {
        testSingleElementMatrix();
        testSquareMatrix();
        testMoreRowsThanCols();
        testEqualSquareMatrix();
        testMoreColumnsThanRows();
        testMatrixWithInfs();
        testAllInfsMatrix();
        System.out.println("All tests completed.");
    }

    private static void testSingleElementMatrix() {
        int[][] matrix = {{5}};
        int[][] assignment = AssignmentAlgorithm.assign(matrix);
        if (assignment.length == 1
            && assignment[0][0] == 0
            && assignment[0][1] == 0) {
            System.out.println("testSingleElementMatrix: PASSED");
        } else {
            System.err.println("testSingleElementMatrix: FAILED - expected {{0,0}}, got "
                + format(assignment));
        }
    }

    private static void testSquareMatrix() {
        int[][] matrix = {
            {70, 40, 20, 55},
            {65, 60, 45, 90},
            {30, 45, 50, 75},
            {25, 30, 55, 40}
        };
        int[][] expected = {{0,2}, {1,1}, {2,0}, {3,3}};
        int[][] assignment = AssignmentAlgorithm.assign(matrix);
        if (matches(assignment, expected)) {
            System.out.println("testSquareMatrix: PASSED");
        } else {
            System.err.println("testSquareMatrix: FAILED - expected "
                + format(expected) + ", got " + format(assignment));
        }
    }

    private static void testMoreRowsThanCols() {
        int[][] matrix = {
            {1, 2},
            {2, 1},
            {3, 3}
        };
        int[][] expected = {{0,0}, {1,1}};
        int[][] assignment = AssignmentAlgorithm.assign(matrix);
        if (matches(assignment, expected)) {
            System.out.println("testMoreRowsThanCols: PASSED");
        } else {
            System.err.println("testMoreRowsThanCols: FAILED - expected "
                + format(expected) + ", got " + format(assignment));
        }
    }

    private static void testEqualSquareMatrix() {
        int size = 3;
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = 5;
            }
        }
        int[][] assignment = AssignmentAlgorithm.assign(matrix);
        boolean pass = (assignment.length == size);
        for (int[] pair : assignment) {
            int col = pair[0], row = pair[1];
            if (matrix[row][col] != 5) pass = false;
        }
        if (pass) {
            System.out.println("testEqualSquareMatrix: PASSED");
        } else {
            System.err.println("testEqualSquareMatrix: FAILED - got " + format(assignment));
        }
    }

    private static void testMoreColumnsThanRows() {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6}
        };
        int[][] expected = {{0,0}, {1,1}};
        int [][] expected2 = {{0,1}, {1, 0}}; // another possible assignment
        int[][] assignment = AssignmentAlgorithm.assign(matrix);
        if (matches(assignment, expected) || matches(assignment, expected2)) {
            System.out.println("testMoreColumnsThanRows: PASSED");
        } else {
            System.err.println("testMoreColumnsThanRows: FAILED - expected "
                + format(expected) + ", got " + format(assignment));
        }
    }

    private static void testMatrixWithInfs() {
        int INF = Integer.MAX_VALUE;
        int[][] matrix = {
            {1, INF, 3},
            {4, 2, INF},
            {INF, 0, 1}
        };
        int[][] assignment = AssignmentAlgorithm.assign(matrix);
        if (assignment.length == 3) {
            System.out.println("testMatrixWithInfs: PASSED");
        } else {
            System.err.println("testMatrixWithInfs: FAILED - unexpected result " + format(assignment));
        }
    }

    private static void testAllInfsMatrix() {
        int INF = Integer.MAX_VALUE;
        int[][] matrix = {
            {INF, INF},
            {INF, INF}
        };
        int[][] assignment = AssignmentAlgorithm.assign(matrix);
        if (assignment.length == 0) {
            System.out.println("testAllInfsMatrix: PASSED");
        } else {
            System.err.println("testAllInfsMatrix: FAILED - expected no assignments, got " + format(assignment));
        }
    }

    private static boolean matches(int[][] a, int[][] b) {
        if (a.length != b.length) return false;
        for (int i = 0; i < a.length; i++) {
            if (a[i][0] != b[i][0] || a[i][1] != b[i][1]) return false;
        }
        return true;
    }

    private static String format(int[][] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append("{" + arr[i][0] + "," + arr[i][1] + "}");
            if (i < arr.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}