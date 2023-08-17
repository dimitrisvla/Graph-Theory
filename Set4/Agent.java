import java.util.ArrayList;
import java.util.Random;

public class Agent {
    int value; // the information it holds
    ArrayList<Agent> knownAgents;  // the other agents he/she knows
    int k = 100; // the key
    
    public Agent(int value) {
        this.value = value - k;
        knownAgents = new ArrayList<>();
    }
    
    public int returnKey() { 
    	return k;
    }
}
