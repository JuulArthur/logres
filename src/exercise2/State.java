package exercise2;

import java.util.ArrayList;

public class State {
	
	private ArrayList<ArrayList<Node>> board;
	
	public State(ArrayList<ArrayList<Node>> board){
		this.board = board;
	}
	
	public ArrayList<ArrayList<Node>> getBoard(){
		return board;
	}
	
	public ArrayList<State> getPossibleStates(){
		ArrayList<Integer> checkedCars = new ArrayList<Integer>();
		for (ArrayList<Node> row : board){
			
		}
	}

}
