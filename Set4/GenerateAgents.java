import java.util.ArrayList;
import java.util.Random;
// Vlachos Dimitrios 2641
// Tree representation of the agency created by the "boss"
public class GenerateAgents {

	public static void main(String[] args) {
		Random rand = new Random();
		int min = 1;
		int max = 1000;
		int value;		
		// Create the info each agent holds
		value = rand.nextInt(max-min) + min;
		Agent v1 = new Agent(value);		
		value = rand.nextInt(max-min) + min;
		Agent v2 = new Agent(value);	
		value = rand.nextInt(max-min) + min;
		Agent v3 = new Agent(value);	
		value = rand.nextInt(max-min) + min;
		Agent v4 = new Agent(value);	
		value = rand.nextInt(max-min) + min;
		Agent v5 = new Agent(value);
		value = rand.nextInt(max-min) + min;
		Agent v6 = new Agent(value);
				
		// Connect agents(vertices)
		// Could't find a way to randomly generate a tree 
		v1.knownAgents.add(v2);
		v2.knownAgents.add(v3);
		v2.knownAgents.add(v4);
		v2.knownAgents.add(v5);
        	v3.knownAgents.add(v6);
		
        	printAgency(v1);
	}

	// Undone concept: Encoding >> Encryption >> Decryption >> Decoding
	public static void printAgency(Agent vertex) {

		System.out.println("Info from agent:");
        	System.out.println(vertex.value + "\n");
        
        	int i = 1;
        	for (Agent agent : vertex.knownAgents) {
            		printAgency(agent);
        	}
    }

}

