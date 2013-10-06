package exercise2;

import java.util.ArrayList;
import java.util.HashSet;

public class DFS {
	private static ArrayList<Node> newNodes;
	private static HashSet<Integer> states = new HashSet<Integer>();
	public static int numberOfCreatedNodes = 0;
	
	public static int solve(Node startNode){
		ArrayList<Node> open = new ArrayList<Node>();
		ArrayList<Node> closed = new ArrayList<Node>(); //Trenger jeg denne?
		open.add(startNode);
		Node current;
		numberOfCreatedNodes ++;
		while (!open.isEmpty()){
			current = open.remove(open.size()-1);
			if(current.getIsGoal()){
				return current.getTotalCost();
				
			}
			newNodes = new ArrayList<Node>(current.createChildren());
			for (Node node: newNodes){
				numberOfCreatedNodes++;
				if(!states.contains(node.getState())){
					states.add(node.getState());
					open.add(node);
				}
			}
			closed.add(current);
		}
		return -1;
	}
}
