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
		boolean best = false;
		while(t>0){
			System.out.println("Temperature: "+t);
			if(p.getValue()==1.0){
				System.out.println("\n \n");
				System.out.println("Found (one of) the optimal solutions");
				System.out.println("\n \n");
				System.out.println(p);
				best = true;
				break;
			}
			nodes.addAll(p.generateNeighbours());
			Collections.sort(nodes);
			Node next = nodes.get(0);
			double q;
			if(p.getValue()==0){
				q=1;
			}
			else{
				q = (double)(next.getValue()-p.getValue())/p.getValue();
			}
			double r = Math.min(1, Math.exp((double)-q/t));
			double x = Math.random();
			if(x>r){
				p = nodes.get(0);
			}
			else{
				p = nodes.get(random.nextInt(nodes.size()));
			}
			if(nodes.size()>=1000){
				nodes.subList(500, nodes.size()).clear();
			}
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
