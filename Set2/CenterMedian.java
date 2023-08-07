import java.util.*;

public class CenterMedian {
	private static final int infinite = Integer.MAX_VALUE;
	
    public static void main(String[] args) {
    	
    	int[][] adjacencyMatrix = {   	// stolen map
                {0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 1, 0, 0, 0, 0},
                {1, 0, 1, 0, 1, 1, 1, 0},
                {0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 1},
                {0, 1, 0, 0, 0, 0, 1, 0}
            };
    	
    	 int n = adjacencyMatrix.length;
    	 printAdjacencyMatrix(n,adjacencyMatrix); 


    	 int[][] distanceArray = computeEveryPairShortestPath(n, adjacencyMatrix);
    	 int k = distanceArray.length;
    	 printdistanceArray(k,distanceArray); 
    	 
    	 // we need the eccentricity for every vertex
         int[] eccentricityArray = computeEccentricities(n, distanceArray);   
         
         int median = computeMedian(n, distanceArray);
         System.out.println("Median of the graph: " + median);
         
         int center = computeCenter(n, eccentricityArray);
         System.out.println("Center of the graph: " + center);
        
    }
    
    static void printAdjacencyMatrix(int n, int [][]adjacencyMatrix) {
    	// Print the adjacency matrix
        System.out.println("Adjacency matrix:\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println(" ");
        }
        System.out.println("\n");
    }
    
    public static int[][] computeEveryPairShortestPath(int n, int[][] adjacencyMatrix) {
        int[][] distances = new int[n][n];
        
        // Initialize the distance matrix with adjacencyMatrix values
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
            	if(adjacencyMatrix[i][j] == 0) {
            		distances[i][j] = infinite;
            	}else {
            		distances[i][j] = adjacencyMatrix[i][j];
            	}
            }
        }

        // Use Floyd & Warshall 
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (distances[i][k] != infinite && distances[k][j] != infinite && 
                    	distances[i][j] > distances[i][k] + distances[k][j]) {
                    	distances[i][j] = distances[i][k] + distances[k][j];
                    }
                }
            }
        }
        
        return distances;
    }

    
    static void printdistanceArray(int k, int [][]distanceArray) {
    	// Print the adjacency matrix
        System.out.println("Distance array:\n");
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                System.out.print(distanceArray[i][j] + " ");
            }
            System.out.println(" ");
        }
        System.out.println("\n");
    }

    
    public static int[] computeEccentricities(int n, int[][] distanceArray) {
        int[] eccentricities = new int[n];
        int minEccentricity = Integer.MAX_VALUE; 

        for (int i = 0; i < n; i++) {
            eccentricities[i] = Arrays.stream(distanceArray[i]).max().getAsInt();
        }

        return eccentricities;
    }
    
    
    public static int computeCenter(int n, int[] eccentricityArray) {
        int minEccentricity = Arrays.stream(eccentricityArray).min().getAsInt(); // use streams for code simplicity
        for (int i = 0; i < n; i++) {
            if (eccentricityArray[i] == minEccentricity) {
                return i+1;
            }
        }
        return (Integer) null; // If no center found
    }

    public static int computeMedian(int n, int[][] distanceArray) {
        int median = -1;
        int minSumOfDistances = Integer.MAX_VALUE;
        

        for (int i = 0; i < n; i++) {
            int sumOfDistances = Arrays.stream(distanceArray[i]).sum();
            if (sumOfDistances < minSumOfDistances) {
                minSumOfDistances = sumOfDistances;
                median = i;
            }
        }

        return median + 1;
    }
}

