# HungarianAlgorithm
A Java implementation of the Kuhn–Munkres assignment algorithm:
https://en.wikipedia.org/wiki/Hungarian_algorithm

## Example
### Input 
|   | 0     | 1     | 2     | 3     |
|---|-------|-------|-------|-------|
|**0**|70.0  | 40.0  | 20.0  | 55.0  |
|**1**|65.0  | 60.0  | 45.0  | 90.0  |
|**2**|30.0  | 45.0  | 50.0  | 75.0  |
|**3**|25.0  | 30.0  | 55.0  | 40.0  |

### Output
```
Col0 => Row2 (30)
Col1 => Row1 (60)
Col2 => Row0 (20)
Col3 => Row3 (40)

Total: 150
```

## Usage
```
double[][] dataMatrix = {
  {70.0, 40.0, 20.0, 55.0},
  {65.0, 60.0, 45.0, 90.0},
  {30.0, 45.0, 50.0, 75.0},
  {25.0, 30.0, 55.0, 40.0}
};  

HungarianAlgorithm ha = new HungarianAlgorithm(dataMatrix);
int[][] assignment = ha.findOptimalAssignment();
```
The results are returned as a two-dimensional array where each subarray represents an assignment. The first element of each assignment represents the column number, and the second element represents the row number of the ```dataMatrix```.
```
{{0, 2}, {1, 1}, {2, 0}, {3, 3}}
```
