package exercise2;

import java.util.ArrayList;
import java.util.HashSet;

public class DFS {
	private static ArrayList<Node> newNodes;
	private static HashSet<Integer> states = new HashSet<Integer>();
	public static int numberOfCreatedNodes;
	
	//Same as BFS, only popping from the bottom instead of the top
	public static int solve(Node startNode){
		ArrayList<Node> open = new ArrayList<Node>();
		open.add(startNode);
		Node current;
		numberOfCreatedNodes = 1;
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
		}
		return -1;
	}
}
