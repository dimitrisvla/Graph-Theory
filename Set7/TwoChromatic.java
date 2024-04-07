import java.util.*;

public class TwoChromatic {
    private int[][] adjacencyMatrix;
    private int[] colors;
    
    public static void main(String[] args) {
    	
        int[][] graphMatrix = {
            {1, 0, 1, 0, 1},
            {0, 1, 1, 0, 0},
            {0, 1, 0, 1, 0},
            {1, 0, 1, 1, 1},
            {1, 0, 0, 1, 0}
        };
        
        int verticesNum = graphMatrix.length;

        TwoChromatic twoChromatic= new TwoChromatic(graphMatrix, verticesNum);
        twoChromatic.isTwoChromatic();
     
    }
    
    
    // Constructor to initialize adjacencyMatrix & colors arrays
    public TwoChromatic(int[][] graph, int vertices) {
    	
        adjacencyMatrix = new int[vertices][vertices];
        colors = new int[vertices];

        // Store the Graph array to the adjacency matrix
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                adjacencyMatrix[i][j] = graph[i][j];
            }
        }
    }

    public void isTwoChromatic() {
        int vertices = adjacencyMatrix.length;
        Arrays.fill(colors, -1); // Initialize  vertices with null color

        // We assign the 1st color to the 1st vertex
        colors[0] = 1;

        // Create a queue for BFS traversal
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        // While BFS is not done
        while (!queue.isEmpty()) {
        	
            int vertex = queue.poll();

            // Check adjacent vertices colors
            for (int i = 0; i < vertices; i++) {
                if (adjacencyMatrix[vertex][i] == 1 && colors[i] == -1) {
                    // Assign the opposite color to the adjacent vertex
                    colors[i] = 1 - colors[vertex];
                    queue.add(i); // Add vertex to queue
                } else if ( colors[i] == colors[vertex] && adjacencyMatrix[vertex][i] == 1 ) {
                    // If adjacent vertexes have the same color, the graph is not 2-Chromatic
                	System.out.println("The graph is not 2-Chromatic.");
                	return;
                }
            }
        }

        // If all vertices are colored and everything is ok, the graph is 2-chromatic
        System.out.println("The graph is 2-chromatic.");
    }

 
}

