package exercise2;

import java.awt.Point;
import java.util.ArrayList;

public class Board {
	protected Car[][] board;
	private ArrayList<Car> checkedCars;
	
	public Board(Car[][] board){
		this.board = board;
		checkedCars = new ArrayList<Car>();
	}
	
	public ArrayList<Board> generateAllBoards(){
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
							if(board.get(y).get(x-1)==null){
								possibleBoards.add(car.moveLeft(new Board(board), new Point(x,y)));
							}
						}
						if(x+car.getSize()<row.size()-1){
							if(board.get(y).get(x+1)==null){
								possibleBoards.add(car.moveRight(new Board(board), new Point(x,y)));
							}
						}
					}
					else{
						if(y>0){
							if(board.get(y-1).get(x)==null){
								possibleBoards.add(car.moveUp(new Board(board), new Point(x,y)));
							}
						}
						if(y+car.getSize()<row.size()-1){
							if(board.get(y+1).get(x)==null){
								possibleBoards.add(car.moveDown(new Board(board), new Point(x,y)));
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

}
