package exercise2;

import java.awt.Point;
import java.util.ArrayList;

public class RushHourNode implements Node{
	//Spesific node to solve the Rush Hour puzzle
	private int costToNode;
	private int estimationToGoal;
	private int totalCost;
	private Boolean isGoal;
	private Board board;
	private int state;
	private Boolean open;
	private Node parent;
	private ArrayList<Node> children;
	
	public RushHourNode(int costToNode, Node parent, Board board){
		this.costToNode = costToNode;
		this.parent = parent;
		this.board = board;
		estimationToGoal = generateEstimationToGoal();
		totalCost = costToNode+estimationToGoal;
		isGoal=(estimationToGoal==0);
		children = new ArrayList<Node>();
		//The state is a hashCode of the ArrayList containing the cars
		state = board.board.hashCode();
	}
	
	public int getState(){
		return this.state;
	}

	public int getCostToNode() {
		return costToNode;
	}
	
	public Board getBoard(){
		return this.board;
	}

	public void setCostToNode(int costToNode) {
		this.costToNode = costToNode;
	}

	public int getEstimationToGoal() {
		return estimationToGoal;
	}

	public int generateEstimationToGoal() {
		//The estimation is based on how many steps there are left for the car to the goal
		//plus how many steps the cars in the way have to move in order to get to the goal
		//we don't take into consideration that these cars may also be blocked by other cars.
		ArrayList<Car> row = this.board.board.get(2);
		int index=0;
		int heuristic = 0;
		for (int i = 0; i<row.size();i++){
			if (row.get(i)!=null && row.get(i).getNumber()==0){
				index = i+1; //The index of the point to the right on the cars
				break;
			}
		}
		heuristic = row.size()-1-index;
		for (int i = index+1;i<row.size();i++){
			if(row.get(i)!=null){
				heuristic+=row.get(i).moveAway(i, board);
			}
		}
		return heuristic;
	}

	public int getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}

	public Boolean getIsGoal() {
		return isGoal;
	}

	public void setIsGoal(Boolean isGoal) {
		this.isGoal = isGoal;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public ArrayList<Node> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<Node> children) {
		this.children = children;
	}

	@Override
	public ArrayList<Node> createChildren() {
		//Creates one children for every possible move on the board
		ArrayList<Board> childrenBoards = new ArrayList<Board>(board.generateAllBoards());
		for (Board childrenBoard : childrenBoards){
			children.add(new RushHourNode(this.costToNode+1, this, childrenBoard));
		}
		return children;
	}

	@Override
	public int compareTo(Node other) {
		//Used to sort the open list in Astar
		return this.getTotalCost()-other.getTotalCost();
	}
	
	//Debug purpose only
	public String toString(){
		return (""+totalCost);
	}
}
