package exercise2;

import java.util.ArrayList;
import java.util.Collections;

public class Astar{
	
	public static ArrayList<Node> solve(Node node){
		ArrayList<Node> open = new ArrayList<Node>();
		ArrayList<Node> closed = new ArrayList<Node>();
		open.add(node);
		Node current;
		while (!open.isEmpty()){
			System.out.println("skjer");
			current = open.remove(0);
			if(current.getIsGoal()){
				ArrayList<Node> path = new ArrayList<Node>();
				while(current!=null){
					path.add(current);
					current = current.getParent();
					return path;
				}
				
			}
			open.addAll(current.createChildren());
			System.out.println("children: "+open);
			Collections.sort(open);
			closed.add(current);
		}
		return null;
	}
	
	public static void main(String args[]){
		
	}
	

}
