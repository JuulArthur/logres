package exercise3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class SimulatedAnnealing {

	public static void main(String[] args) throws NumberFormatException, IOException {
		Random random = new Random();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter height: ");
		int height = Integer.valueOf(reader.readLine());
		System.out.println("Enter width: ");
		int width = Integer.valueOf(reader.readLine());
		System.out.println("Enter k: ");
		int k = Integer.valueOf(reader.readLine());
		Node p = new Node(height, width, k);
		//Seems like this is a nice temperature to start with
		double t = 10.0;
		//The List containing all the nodes
		List<Node> nodes = new ArrayList<Node>();
		boolean best = false;
		while(t>0){
			//We want to see the temperature at all times
			System.out.println("Temperature: "+t);
			//If the objective function returns 1 we know we have the optimal solution
			if(p.getValue()==1.0){
				System.out.println("\n \n");
				System.out.println("Found (one of) the optimal solutions");
				System.out.println("\n \n");
				System.out.println(p);
				best = true;
				break;
			}
			//Add all neighbours to our current node
			nodes.addAll(p.generateNeighbours());
			//Sort the list containing all node, we sort the so that the node with
			//the best value from the objective function comes first in the list
			Collections.sort(nodes);
			Node next = nodes.get(0);
			double q;
			//Hopefullt this never happens, but we want to be sure we doesn't divide by 0
			if(p.getValue()==0){
				//We set q to a high number, because our current node isn't any good
				q=1;
			}
			else{
				//We use the given formula to calculate q
				q = (double)(next.getValue()-p.getValue())/p.getValue();
			}
			//Given formula for r
			double r = Math.min(1, Math.exp((double)-q/t));
			//X is a random number from 0 to 1;
			double x = Math.random();
			if(x>r){
				//Get the best node
				p = nodes.get(0);
			}
			else{
				//Get a random node
				p = nodes.get(random.nextInt(nodes.size()));
			}
			//We don't want our list to become so huge that it steals all our memory
			//so we just delete the worst nodes if we get too many
			if(nodes.size()>=1000){
				nodes.subList(500, nodes.size()).clear();
			}
			//With this value for dT we'll usually get to the answer
			t-=0.001;
		}
		if(!best){
			System.out.println("\n \n");
			System.out.println("The best I could find is this");
			System.out.println("\n \n");
			System.out.println(nodes.get(0));
		}

	}

}
