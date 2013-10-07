package exercise2;

import java.util.ArrayList;

public interface Node extends Comparable<Node>{
	
	//General node to be used for different Astar problems
	
	public ArrayList<Node> getChildren();
	
	public int getTotalCost();
	
	public int getState();
	
	public Board getBoard();
	
	public ArrayList<Node> createChildren();
	
	public Boolean getIsGoal();

	public Boolean getOpen();

	public void setOpen(Boolean open);
	
	public Node getParent();

	public void setParent(Node parent);
	
	public void setIsGoal(Boolean isGoal);

	public void setChildren(ArrayList<Node> children);

	public int compareTo(Node other);
	
	//Debug purpose only
	public String toString();
}
