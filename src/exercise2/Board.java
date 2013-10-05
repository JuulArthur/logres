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
		ArrayList<Board> possibleBoards = new ArrayList<Board>();
		ArrayList<ArrayList<Car>> temp;
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
								System.out.println("Left");
								System.out.println(board);
								temp = new ArrayList<ArrayList<Car>>(board);
								possibleBoards.add(car.moveLeft(new Board(temp), new Point(x,y)));
								System.out.println(board);
								temp.get(0).set(0, car);
								System.out.println("Test");
								System.out.println(temp);
								System.out.println(board);
							}
						}
						if(x+car.getSize()<row.size()-1){
							if(board.get(y).get(x+1)==null){
								System.out.println("Right");
								System.out.println(board);
								temp = new ArrayList<ArrayList<Car>>(board);
								possibleBoards.add(car.moveRight(new Board(temp), new Point(x,y)));
								System.out.println(board);
							}
						}
					}
					else{
						if(y>0){
							if(board.get(y-1).get(x)==null){
								possibleBoards.add(car.moveUp(new Board(new ArrayList<ArrayList<Car>>(board)), new Point(x,y)));
							}
						}
						if(y+car.getSize()<row.size()-1){
							if(board.get(y+1).get(x)==null){
								possibleBoards.add(car.moveDown(new Board(new ArrayList<ArrayList<Car>>(board)), new Point(x,y)));
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
