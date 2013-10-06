package exercise2;

import java.awt.Point;
import java.util.ArrayList;

public class Board {
	protected ArrayList<ArrayList<Car>> board;
	private ArrayList<Car> checkedCars;
	
	public Board(ArrayList<ArrayList<Car>> board){
		this.board = board;
		checkedCars = new ArrayList<Car>();
	}
	
	public ArrayList<Board> generateAllBoards(){
		checkedCars = new ArrayList<Car>();
		ArrayList<Board> possibleBoards = new ArrayList<Board>();
		int x;
		int y = 0;
		for (ArrayList<Car> row : board){
			x=0;
			for (Car car : row){
				if (car!=null && !checkedCars.contains(car)){
					checkedCars.add(car);
					if(car.getIsHorizontal()){
						if(x>0){
							if(board.get(y).get(x-1)==null){ //Move left
								ArrayList<ArrayList<Car>> tempBoard = new ArrayList<ArrayList<Car>>();
								tempBoard = copyBoard();
								possibleBoards.add(car.moveLeft(new Board(tempBoard), new Point(x,y)));
							}
						}
						if(x+car.getSize()-1<row.size()-1){ //Move right
							if(board.get(y).get(x+car.getSize())==null){
								ArrayList<ArrayList<Car>> tempBoard = new ArrayList<ArrayList<Car>>();
								tempBoard = copyBoard();
								possibleBoards.add(car.moveRight(new Board(tempBoard), new Point(x,y)));
							}
						}
					}
					else{
						if(y>0){
							if(board.get(y-1).get(x)==null){//Move up
								ArrayList<ArrayList<Car>> tempBoard = new ArrayList<ArrayList<Car>>();
								tempBoard = copyBoard();
								possibleBoards.add(car.moveUp(new Board(tempBoard), new Point(x,y)));
							}
						}
						if(y+car.getSize()<row.size()-1){//Move down
							if(board.get(y+car.getSize()).get(x)==null){
								ArrayList<ArrayList<Car>> tempBoard = new ArrayList<ArrayList<Car>>();
								tempBoard = copyBoard();
								possibleBoards.add(car.moveDown(new Board(tempBoard), new Point(x,y)));
							}
						}
					}
				}
				x++;
			}
			y++;
		}
		return possibleBoards;
	}
	
	public ArrayList<ArrayList<Car>> copyBoard(){
		ArrayList<ArrayList<Car>> tempBoard = new ArrayList<ArrayList<Car>>();
		for (ArrayList<Car> boardRow: board){
			ArrayList<Car> tempRow = new ArrayList<Car>();
			for(Car tempCar: boardRow){
				tempRow.add(tempCar);
			}
			tempBoard.add(tempRow);
		}
		return tempBoard;
	}
	
	public String toString(){
		String out = "";
		for (ArrayList<Car> row : board){
			for (Car car : row){
				if(car==null){
					out+="n";
				}
				else{
					out+=Integer.toString(car.getNumber());
				}
			}
			out+="\n";
		}
		return out;
	}

}
