package exercise2;

import java.awt.Point;

public class Car {
	private int number;
	private int size;
	private Boolean isHorizontal;
	
	public Car(int number, int size, Boolean isHorizontal){
		this.number = number; //The "name" of the car
		this.size = size;
		this.isHorizontal = isHorizontal;
	}
	
	//Checks how many moves this car needs to get out of the way for car
	//0 to get to the goal
	public int moveAway(int x, Board board){
		int y = 2;
		if(board.board.get(1).get(x)==this){
			y=1; //This car is in the way and is also in the spot over the row to car 0
			if (board.board.get(0).get(x)==this){
				y=0;
			}
		}
		if (y==1 && this.size==2){
			//This car can be moved away with one move
			return 1;
		}
		return (3-y);
	}
	
	public int getNumber(){
		return this.number;
	}

	public int getSize() {
		return size;
	}

	public Boolean getIsHorizontal() {
		return isHorizontal;
	}

	public Board moveUp(Board board, Point position){
		Car car = board.board.get(position.y+size-1).get(position.x);
		board.board.get(position.y+size-1).set(position.x,null);
		board.board.get(position.y-1).set(position.x,car);
		return board;
	}
	
	public Board moveDown(Board board, Point position){
		board.board.get(position.y).get(position.x);
		Car car = board.board.get(position.y).set(position.x,null);
		board.board.get(position.y+size).set(position.x,car);
		return board;
	}
	
	public Board moveRight(Board board, Point position){
		Car car = board.board.get(position.y).get(position.x);
		board.board.get(position.y).set(position.x,null);
		board.board.get(position.y).set(position.x+size,car);
		return board;
	}
	
	public Board moveLeft(Board board, Point position){
		Car car = board.board.get(position.y).get(position.x);
		board.board.get(position.y).set(position.x+size-1,null);
		board.board.get(position.y).set(position.x-1,car);
		return board;
	}
	
	//Debug purpose only
	public String toString(){
		return Integer.toString(getNumber());
	}

}
