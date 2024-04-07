//Vlachos Dimitris 2641
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class TournamentGraph {
    private int Vertices;
    private List<List<Integer>> adjacencyList;

    // constructor	
    public TournamentGraph(int vertices) {
        this.Vertices = vertices;
        
        adjacencyList = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
        	adjacencyList.add(new ArrayList<>());
        }
    }

    public void addEdge(int tail, int head) {
    	adjacencyList.get(tail).add(head);
    }

    public boolean isTournament() {

    	for (int i = 0; i < Vertices; i++) {
            for (int j = i + 1; j < Vertices; j++) {
                if (!adjacencyList.get(i).contains(j) && 
                	!adjacencyList.get(j).contains(i)) {
                    return false;
                }
            }
        }

        for (int i = 0; i < Vertices; i++) {
            if (adjacencyList.get(i).contains(i)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
    	int randu, randv ;  
    	boolean isTournament;
        TournamentGraph graph = new TournamentGraph(21);
        
        // Generate G(basically the edges)
        for(int i=0; i<40; i++) {
        	randu = ThreadLocalRandom.current().nextInt(0, 20 + 1);
        	randv = ThreadLocalRandom.current().nextInt(0, 20 + 1);
        	graph.addEdge(randu, randv);
        }
        
        isTournament = graph.isTournament();
        System.out.println("Is the given graph a tournament graph? \n");
        if(isTournament) {
        	System.out.println("It is tournament.");
        }else {
        	System.out.println("It is not tournament.");
        }
    }
}

