/**
 * Self-contained test harness for AssignmentAlgorithm edge cases (double input matrices).
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
        testDecimalValuesMatrix();
        System.out.println("All tests completed.");
    }

    private static void testSingleElementMatrix() {
        double[][] matrix = {{5.0}};
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
        double[][] matrix = {
            {70.0, 40.0, 20.0, 55.0},
            {65.0, 60.0, 45.0, 90.0},
            {30.0, 45.0, 50.0, 75.0},
            {25.0, 30.0, 55.0, 40.0}
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
        double[][] matrix = {
            {1.0, 2.0},
            {2.0, 1.0},
            {3.0, 3.0}
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
        double[][] matrix = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = 5.0;
            }
        }
        int[][] assignment = AssignmentAlgorithm.assign(matrix);
        boolean pass = (assignment.length == size);
        for (int[] pair : assignment) {
            if (matrix[pair[1]][pair[0]] != 5.0) pass = false;
        }
        if (pass) {
            System.out.println("testEqualSquareMatrix: PASSED");
        } else {
            System.err.println("testEqualSquareMatrix: FAILED - got " + format(assignment));
        }
    }

    private static void testMoreColumnsThanRows() {
        double[][] matrix = {
            {1.0, 2.0, 3.0},
            {4.0, 5.0, 6.0}
        };
        int[][] expected1 = {{0,0}, {1,1}};
        int[][] expected2 = {{0,1}, {1,0}};
        int[][] assignment = AssignmentAlgorithm.assign(matrix);
        if (matches(assignment, expected1) || matches(assignment, expected2)) {
            System.out.println("testMoreColumnsThanRows: PASSED");
        } else {
            System.err.println("testMoreColumnsThanRows: FAILED - expected "
                + format(expected1) + " or " + format(expected2)
                + ", got " + format(assignment));
        }
    }

    private static void testMatrixWithInfs() {
        double INF = Double.MAX_VALUE;
        double[][] matrix = {
            {1.0, INF, 3.0},
            {4.0, 2.0, INF},
            {INF, 0.0, 1.0}
        };
        int[][] assignment = AssignmentAlgorithm.assign(matrix);
        if (assignment.length == 3) {
            System.out.println("testMatrixWithInfs: PASSED");
        } else {
            System.err.println("testMatrixWithInfs: FAILED - unexpected result " + format(assignment));
        }
    }

    private static void testAllInfsMatrix() {
        double INF = Double.MAX_VALUE;
        double[][] matrix = {
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

    private static void testDecimalValuesMatrix() {
        double[][] matrix = {
            {1.1, 0.9},
            {2.5, 3.5}
        };
        int[][] expected = {{0,1}, {1,0}};
        int[][] assignment = AssignmentAlgorithm.assign(matrix);
        if (matches(assignment, expected)) {
            System.out.println("testDecimalValuesMatrix: PASSED");
        } else {
            System.err.println("testDecimalValuesMatrix: FAILED - expected "
                + format(expected) + ", got " + format(assignment));
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
