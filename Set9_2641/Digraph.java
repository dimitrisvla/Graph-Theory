// Vlachos Dimitris 2641

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


class Digraph {
    private int V; // Number of vertices
    private LinkedList<Integer>[] adjlist; // Adjacency list

    // constructor
    Digraph(int v) {
        V = v;
        adjlist = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adjlist[i] = new LinkedList<>();
    }

    // Method to add an edge
    void addEdge(int v, int u) {
        adjlist[v].add(u);
    }

    // Recursive method to perform DFS(vertex u starting point)
    void DFS(int u, boolean[] visited) {
        visited[u] = true;
        for (int neighbor : adjlist[u]) {
            if (!visited[neighbor])
                DFS(neighbor, visited);
        }
    }

    // Method that returns reverse(or transpose) of the specific G
    Digraph getTranspose() {
    	Digraph transposeGraph = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for (int neighbor : adjlist[v])
                transposeGraph.addEdge(neighbor, v);
        }
        return transposeGraph;
    }

    // Method to check if G is strongly connected
    boolean isGStronglyConnected() {
        // 1: Initialize all vertices as not visited
        boolean[] visited = new boolean[V];
        Arrays.fill(visited, false);

        // 2: Perform DFS starting from the first vertex
        DFS(0, visited);

        // If any vertex is not visited, then G is not strongly connected
        for (boolean v : visited) {
            if (!v)
                return false;
        }

        // 3: Get the transpose (or reverse) of G
        Digraph transposeGraph = getTranspose();

        // 4: Reset visited[] for the next DFS
        Arrays.fill(visited, false);

        // 5: DFS starting from the 1st vertex of the transpose G
        transposeGraph.DFS(0, visited);

        // If any vertex is not visited in the transpose G, then the graph is not strongly connected
        for (boolean u : visited) {
            if (!u){
                return false;
            }
        }
     // If all vertices are visited in both DFS calls, then the graph is strongly connected
		return true;
    }

    public static void main(String[] args) {
    	int randu,randv ;
        Digraph graph = new Digraph(21);
        
        // Generate a G(basically the edges)
        for(int i=0; i<80; i++) {
        	randu = ThreadLocalRandom.current().nextInt(0, 20 + 1);
        	randv = ThreadLocalRandom.current().nextInt(0, 20 + 1);
        	graph.addEdge(randu, randv); 
        }

        // Check if the graph is strongly connected
        if (graph.isGStronglyConnected())
            System.out.println("The graph is strongly connected");
        else
            System.out.println("The graph is not strongly connected");
    }
}

