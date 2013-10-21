package exercise3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class SimulatedAnnealing {

	//Each node has a responsibility to add their state to this list,
	//We don't want to add the same board twice.
	public static Set<Integer> states = new HashSet<Integer>();

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
		double t = 10.0;
		List<Node> nodes = new ArrayList<Node>();
		while(t>0){
			System.out.println("T: "+t);
			if(p.objectiveFunction()==1.0){
				System.out.println("JIPPI");
				System.out.println(p.objectiveFunction());
				System.out.println(p);
				p.objectiveFunction();
				break;
			}
			nodes.addAll(p.generateNeighbours());
			Collections.sort(nodes);
			Node next = nodes.get(0);
			System.out.println(next.objectiveFunction());
			System.out.println(p.objectiveFunction());
			double q;
			if(p.objectiveFunction()==0){
				q=1;
			}
			else{
				q = (double)(next.objectiveFunction()-p.objectiveFunction())/p.objectiveFunction();
			}
			System.out.println(next);
			System.out.println(p);
			double r = Math.min(1, Math.exp((double)-q/t));
			double x = Math.random();
			System.out.println("Q: "+q);
			System.out.println("X: "+x);
			System.out.println("R: "+r);
			if(x>r){
				System.out.println("BESTBESTBESTBSETBSETBSETBSETBSETBSET");
				p = nodes.get(0);
			}
			else{
				p = nodes.get(random.nextInt(nodes.size()));
			}
			t-=0.01;
		}
		System.out.println(p);

	}

}
