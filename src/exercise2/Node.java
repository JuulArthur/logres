package exercise2;

import java.util.ArrayList;

public interface Node extends Comparable<Node>{
	
	public ArrayList<Node> getChildren();
	
	public int getTotalCost();
	
	public void createChildren();
	
	public Boolean getIsGoal();

	public Boolean getOpen();

	public void setOpen(Boolean open);
	
	public Node getParent();

	public void setParent(Node parent);
	
	public void setIsGoal(Boolean isGoal);

	public void setChildren(ArrayList<Node> children);

	public int compareTo(Node other);
}
