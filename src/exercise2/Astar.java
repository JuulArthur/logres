package exercise2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Astar{
	//General Astar algorithm
	private static ArrayList<Node> newNodes;
	private static HashSet<Integer> states = new HashSet<Integer>(); //contains a hash-value of the boards int the different created nodes
	public static int numberOfCreatedNodes;
	
	public static int solve(Node startNode){
		//The list containing the nodes to search through
		ArrayList<Node> open = new ArrayList<Node>();
		//Nodes to be checked
		open.add(startNode); 
		//The node being processed
		Node current;
		//We have already here created one node
		numberOfCreatedNodes = 1; 
		while (!open.isEmpty()){
			//Pop the node that seems to be best
			current = open.remove(0);
			if(current.getIsGoal()){
				return current.getTotalCost();
				
			}
			//Create an ArrayList with all possible children for a given node
			newNodes = new ArrayList<Node>(current.createChildren());
			for (Node node: newNodes){
				numberOfCreatedNodes++;
				//Check that no other node has had the same board
				if(!states.contains(node.getState())){ 
					states.add(node.getState());
					open.add(node);
				}
			}
			//Sort the list so that the node that seems best is placed on the top
			Collections.sort(open);
		}
		return -1;
	}

}
