import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

// Vlachos Dimitris 2641

//Check if a sequence is Graphical
public class Graphical {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);  
		
		System.out.println("Please give the number of vertices |V|(order of the graph) :\n");
		int vertices = scanner.nextInt();
		scanner.close();
		System.out.println("The number of vertices : |V| = " + vertices);
		
		ArrayList<Integer> degreeArray = degreeProducer(vertices); //1st part 
		checkIfGraphical(degreeArray, vertices); // 2nd part
		// 3rd part inside method checkIfGraphical
	}

	//Produces the degrees for each vertex of the Graph
	public static ArrayList<Integer> degreeProducer(int vertices) {
		// need to store the vertex degree into a list
	    	ArrayList<Integer> degree = new ArrayList<Integer>(); // Store the degrees in an Arraylist

		int min = 0;
		int max = vertices-1;
		int number;
		
		// Randomly generate the degree (number between 0 and |V|-1)
		for(int num=0; num<vertices; num++){ 
			number = min + (int)(Math.random() * (max-min) + 1);
			degree.add(num, number);
		}
		
		int num = 0;
		for(Integer d: degree){ 
			System.out.println("the degree of vertex " + (num+1) + " is:  " + d);
			num++;
		}
		return degree;
	}
	
	// Checks if the sequence of degrees can produce a Graph
	public static void checkIfGraphical(ArrayList<Integer> degreeArray, int n) {
		ArrayList<Integer> degreeArray2 = new ArrayList<Integer>();
		degreeArray2 = degreeArray;
		int newElement;
		// check 1st condition (should be checked outside the loop)
		for(Integer x: degreeArray) {
			if(x > (n-1)) {
				System.out.println("No: Sequence is not graphical");
				System.exit(0); 
			}
		}
		
		//run loop until we see if graphical or not
		while (true) {	
			// check 2nd condition
			int sum = 0;
			
			for(Integer y: degreeArray)  {
				sum += y;
			}
			
			if(sum == 0) {
				System.out.println("Yes: Sequence is graphical");
				graphProduced(n, degreeArray2); 		
				System.exit(0); 
			}
			
			// check 3d condition
			for(Integer d: degreeArray)  {
				if(d < 0) { // if the sequence contains a negative value
					System.out.println("No: Sequence is not graphical");
					System.exit(0);
				}
			}
			
			// 4th condition
			// Sort the sequence using two for loops    
			int temp;
	        	for (Integer a: degreeArray){     
	          		for (Integer b: degreeArray){     
	              			if(a < b) {  //swap elements if not in order
	                 		temp = a;  
	                 		a = b;    
	                 		b = temp;    
	               		}     
	            	}     
	        }    
	        
	        // 5th condition
	        Integer firstNumber = degreeArray2.get(0); // we need to "take" the 1st element
	        degreeArray2.remove(0); //delete the 1st element
	  
	        for(int i=0; i<firstNumber; i++) {  // change the degree sequence  
	            newElement = degreeArray2.get(i) - 1;
	            degreeArray2.remove(i); //delete old element
	            degreeArray2.add(i, newElement); //add new element (previous value - 1)
	        }
		}
	}
	
	// Graph represented as a 2d matrix
	public static int [][] graphProduced(int vertices, ArrayList<Integer> degreeArray2) {	
		int[][]graph2dArray = new int [vertices][vertices];
		
	        for (int i = 0; i < vertices; i++) {
	            int degree = degreeArray2.get(i);
	            for (int j = i + 1; j <= i + degree; j++) {
	            	graph2dArray[i][j] = 1;
	            	graph2dArray[j][i] = 1;
	            }
	        }
          
	        //Print the result
	        for (int i = 0; i < vertices; i++) {
	            for (int j = 0; j < vertices; j++) {
	                System.out.print(graph2dArray[i][j] + " ");
	            }
	            System.out.println();
	        }
        	return graph2dArray; // return if needed
	}


}





