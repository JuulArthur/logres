package exercise2;

import java.awt.Point;

public class Car {
	private int number;
	private int size;
	private Boolean isHorizontal;
	
	public Car(int number, int size, Boolean isHorizontal){
		this.number = number;
		this.size = size;
		this.isHorizontal = isHorizontal;
	}
	
	public int moveAway(int x, Board board){
		int y = 2;
		if(board.board.get(1).get(x)==this){
			y=1;
			if (board.board.get(0).get(x)==this){
				y=0;
			}
		}
		if (y==1 && this.size==2){
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

	public void setSize(int size) {
		this.size = size;
	}

	public Boolean getIsHorizontal() {
		return isHorizontal;
	}

	public void setIsHorizontal(Boolean isHorizontal) {
		this.isHorizontal = isHorizontal;
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
		board.board.get(position.y+size-1).set(position.x,car);
		return board;
	}
	
	public Board moveRight(Board board, Point position){
		Car car = board.board.get(position.y).get(position.x-1);
		board.board.get(position.y).set(position.x-1,null);
		board.board.get(position.y).set(position.x+size,car);
		return board;
	}
	
	public Board moveLeft(Board board, Point position){
		Car car = board.board.get(position.y).get(position.x+size-1);
		board.board.get(position.y).set(position.x+size-1,null);
		board.board.get(position.y).set(position.x-1,car);
		return board;
	}

}
