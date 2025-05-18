package com.example.Projekt_IO.Services;

import com.example.Projekt_IO.Model.Dtos.DeclarationShortDto;
import com.example.Projekt_IO.Model.Entities.Exercise;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.HashMap;



@Service
@RequiredArgsConstructor
public class MatchingService {
    private final DeclarationService declarationService;


    public void matchingAlgorithm(UUID lessonId){
        List<DeclarationShortDto> declarations = declarationService.getAllDeclarationsForLesson(lessonId);

        HashMap<String, Integer> studentHashMap = new HashMap<>();
        HashMap<UUID, Integer> taskHashMap = new HashMap<>();
        int studentIndex = 0, taskIndex = 0;

        for (DeclarationShortDto declaration : declarations) {
            if (!studentHashMap.containsKey(declaration.student)) {
                studentHashMap.put(declaration.student, studentIndex++);
            }
            if (!taskHashMap.containsKey(declaration.exerciseId)) {
                taskHashMap.put(declaration.exerciseId, taskIndex++);
            }
        }

        double[][] costMatrix = new double[studentIndex][taskIndex];
        for (DeclarationShortDto declaration : declarations) {
            int studentIdx = studentHashMap.get(declaration.student);
            int taskIdx = taskHashMap.get(declaration.exerciseId);
            costMatrix[taskIdx][studentIdx] = declaration.pointsInCourse;
        }

        int[][] assignment = AssignmentAlgorithm.assign(costMatrix);

        for (int[] pair : assignment){
            Optional<Exercise> e = exerciseRepository.findById(pair[1]);

            if (e.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise not found")
            }

            Exercise exercise = exercise.get();
            exercise.setApprovedStudent(pair[0]);
            exerciseRepository.save(exercise);
        }

    }
}

/**
 * An implemetation of the Kuhn–Munkres assignment algorithm of the year 1957.
 * https://en.wikipedia.org/wiki/Hungarian_algorithm
 *
 *
 * @author https://github.com/aalmi | march 2014
 * @contributor https://github.com/jnowak123 | May 2025 | Converted to work with double[][] matrix
 * @version 2.0
 */
class HungarianAlgorithm {

    double[][] matrix; // initial matrix (cost matrix)

    // markers in the matrix
    int[] squareInRow, squareInCol, rowIsCovered, colIsCovered, staredZeroesInRow;

    public HungarianAlgorithm(double[][] matrix) {
        if (matrix.length != matrix[0].length) {
            try {
                throw new IllegalAccessException("The matrix is not square!");
            } catch (IllegalAccessException ex) {
                System.err.println(ex);
                System.exit(1);
            }
        }

        this.matrix = matrix;
        squareInRow = new int[matrix.length];       // squareInRow & squareInCol indicate the position
        squareInCol = new int[matrix[0].length];    // of the marked zeroes

        rowIsCovered = new int[matrix.length];      // indicates whether a row is covered
        colIsCovered = new int[matrix[0].length];   // indicates whether a column is covered
        staredZeroesInRow = new int[matrix.length]; // storage for the 0*
        Arrays.fill(staredZeroesInRow, -1);
        Arrays.fill(squareInRow, -1);
        Arrays.fill(squareInCol, -1);
    }

    /**
     * find an optimal assignment
     *
     * @return optimal assignment
     */
    public int[][] findOptimalAssignment() {
        step1();    // reduce matrix
        step2();    // mark independent zeroes
        step3();    // cover columns which contain a marked zero

        while (!allColumnsAreCovered()) {
            int[] mainZero = step4();
            while (mainZero == null) {      // while no zero found in step4
                step7();
                mainZero = step4();
            }
            if (squareInRow[mainZero[0]] == -1) {
                // there is no square mark in the mainZero line
                step6(mainZero);
                step3();    // cover columns which contain a marked zero
            } else {
                // there is square mark in the mainZero line
                // step 5
                rowIsCovered[mainZero[0]] = 1;  // cover row of mainZero
                colIsCovered[squareInRow[mainZero[0]]] = 0;  // uncover column of mainZero
                step7();
            }
        }

        int[][] optimalAssignment = new int[matrix.length][];
        for (int i = 0; i < squareInCol.length; i++) {
            optimalAssignment[i] = new int[]{i, squareInCol[i]};
        }
        return optimalAssignment;
    }

    /**
     * Check if all columns are covered. If that's the case then the
     * optimal solution is found
     *
     * @return true or false
     */
    private boolean allColumnsAreCovered() {
        for (int i : colIsCovered) {
            if (i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Step 1:
     * Reduce the matrix so that in each row and column at least one zero exists:
     * 1. subtract each row minima from each element of the row
     * 2. subtract each column minima from each element of the column
     */
    private void step1() {
        // rows
        for (int i = 0; i < matrix.length; i++) {
            // find the min value of the current row
            double currentRowMin = Double.MAX_VALUE;
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] < currentRowMin) {
                    currentRowMin = matrix[i][j];
                }
            }
            // subtract min value from each element of the current row
            for (int k = 0; k < matrix[i].length; k++) {
                matrix[i][k] -= currentRowMin;
            }
        }

        // cols
        for (int i = 0; i < matrix[0].length; i++) {
            // find the min value of the current column
            double currentColMin = Double.MAX_VALUE;
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[j][i] < currentColMin) {
                    currentColMin = matrix[j][i];
                }
            }
            // subtract min value from each element of the current column
            for (int k = 0; k < matrix.length; k++) {
                matrix[k][i] -= currentColMin;
            }
        }
    }

    /**
     * Step 2:
     * mark each 0 with a "square", if there are no other marked zeroes in the same row or column
     */
    private void step2() {
        int[] rowHasSquare = new int[matrix.length];
        int[] colHasSquare = new int[matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                // mark if current value == 0 & there are no other marked zeroes in the same row or column
                if (matrix[i][j] == 0 && rowHasSquare[i] == 0 && colHasSquare[j] == 0) {
                    rowHasSquare[i] = 1;
                    colHasSquare[j] = 1;
                    squareInRow[i] = j; // save the row-position of the zero
                    squareInCol[j] = i; // save the column-position of the zero
                    continue; // jump to next row
                }
            }
        }
    }

    /**
     * Step 3:
     * Cover all columns which are marked with a "square"
     */
    private void step3() {
        for (int i = 0; i < squareInCol.length; i++) {
            colIsCovered[i] = squareInCol[i] != -1 ? 1 : 0;
        }
    }

    /**
     * Step 7:
     * 1. Find the smallest uncovered value in the matrix.
     * 2. Subtract it from all uncovered values
     * 3. Add it to all twice-covered values
     */
    private void step7() {
        // Find the smallest uncovered value in the matrix
        double minUncoveredValue = Double.MAX_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            if (rowIsCovered[i] == 1) {
                continue;
            }
            for (int j = 0; j < matrix[0].length; j++) {
                if (colIsCovered[j] == 0 && matrix[i][j] < minUncoveredValue) {
                    minUncoveredValue = matrix[i][j];
                }
            }
        }

        if (minUncoveredValue > 0) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (rowIsCovered[i] == 1 && colIsCovered[j] == 1) {
                        // Add min to all twice-covered values
                        matrix[i][j] += minUncoveredValue;
                    } else if (rowIsCovered[i] == 0 && colIsCovered[j] == 0) {
                        // Subtract min from all uncovered values
                        matrix[i][j] -= minUncoveredValue;
                    }
                }
            }
        }
    }

    /**
     * Step 4:
     * Find zero value Z_0 and mark it as "0*".
     *
     * @return position of Z_0 in the matrix
     */
    private int[] step4() {
        for (int i = 0; i < matrix.length; i++) {
            if (rowIsCovered[i] == 0) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (matrix[i][j] == 0 && colIsCovered[j] == 0) {
                        staredZeroesInRow[i] = j; // mark as 0*
                        return new int[]{i, j};
                    }
                }
            }
        }
        return null;
    }

    /**
     * Step 6:
     * Create a chain K of alternating "squares" and "0*"
     *
     * @param mainZero => Z_0 of Step 4
     */
    private void step6(int[] mainZero) {
        int i = mainZero[0];
        int j = mainZero[1];

        Set<int[]> K = new LinkedHashSet<>();
        //(a)
        // add Z_0 to K
        K.add(mainZero);
        boolean found = false;
        do {
            // (b)
            // add Z_1 to K if
            // there is a zero Z_1 which is marked with a "square " in the column of Z_0
            if (squareInCol[j] != -1) {
                K.add(new int[]{squareInCol[j], j});
                found = true;
            } else {
                found = false;
            }

            // if no zero element Z_1 marked with "square" exists in the column of Z_0, then cancel the loop
            if (!found) {
                break;
            }

            // (c)
            // replace Z_0 with the 0* in the row of Z_1
            i = squareInCol[j];
            j = staredZeroesInRow[i];
            // add the new Z_0 to K
            if (j != -1) {
                K.add(new int[]{i, j});
                found = true;
            } else {
                found = false;
            }

        } while (found); // (d) as long as no new "square" marks are found

        // (e)
        for (int[] zero : K) {
            // remove all "square" marks in K
            if (squareInCol[zero[1]] == zero[0]) {
                squareInCol[zero[1]] = -1;
                squareInRow[zero[0]] = -1;
            }
            // replace the 0* marks in K with "square" marks
            if (staredZeroesInRow[zero[0]] == zero[1]) {
                squareInRow[zero[0]] = zero[1];
                squareInCol[zero[1]] = zero[0];
            }
        }

        // (f)
        // remove all marks
        Arrays.fill(staredZeroesInRow, -1);
        Arrays.fill(rowIsCovered, 0);
        Arrays.fill(colIsCovered, 0);
    }
}

/**
 * An extension of the Kuhn–Munkres assignment algorithm of the year 1957.
 * this implementation allows for non-square matrices by converting them to square matrices.
 * 
 * @author https://github.com/jnowak123 | May 2025
 * @version 1.0
 */

class AssignmentAlgorithm {
    
    public static int[][] assign(double[][] matrix) {
        return assign(matrix, 0);
    }

    public static int[][] assign(double[][] matrix, int randomizeValue) {

        int rows = matrix.length;
        int cols = matrix[0].length;
        int maxSize = Math.max(rows, cols);

        // Increase the costs in the atrix by a random value - this makes the algorithm more fair
        if (randomizeValue > 0) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (matrix[i][j] != Double.MAX_VALUE) {
                        matrix[i][j] += Math.random() * randomizeValue;
                    }
                }
            }
        }

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

