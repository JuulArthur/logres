package exercise5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board{
	public int[][] board;
	private int width;
	private int height;

	public Board(int height, int width, int k){
		this.height=height;
		this.width=width;
		this.board = generateBoard(height, width);
	}
	
	private int[][] generateBoard(int height, int width){
		Random random = new Random();
		int[][] board = new int[height][width];
		for(int y = 0; y<height;y++){
			board[y][random.nextInt(width)] = 1;
		}
		return board;
	}
	

	public void modifyBoard(){
		Random random = new Random();
		System.out.println(this);
		//x and y are used to give a random position to start looking in the board
		int y = random.nextInt(height);
		double lowest = Double.MAX_VALUE;
		double collisions;
		ArrayList<Integer> cells = new ArrayList<Integer>();
		//int x = findQueen(y);
		for(int x = 0;x<width; x++){
			if(board[y][x]==1){
				board[y][x]=0;
				//If some other cell has the equal amount of collisions we want to move the queen
				//to the other cell
				collisions = getCollisionsInCell(x, y)+0.1;
			}
			else{
				collisions = getCollisionsInCell(x, y);
			}
			if(collisions == lowest){
				cells.add(x);
			}
			else if(collisions < lowest){
				lowest = collisions;
				cells.clear();
				cells.add(x);
			}
		}
		if(cells.size()>1){
			board[y][cells.set(random.nextInt(cells.size()),1)]=1;
		}
		else{
			board[y][cells.get(0)]=1;
		}
		System.out.println(this);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**Checks whether we can place an egg in the given cell or not */
	private int getCollisionsInCell(int x, int y){
		return (getCollisionsInColumn(x, y)+getCollisionsInDiagonals(x, y));
	}
	
	private int getCollisionsToQueen(int x, int y){
		//We don't want this queen to be counted as a collision
		board[y][x] = 0;
		int collisions = getCollisionsInColumn(x, y)+getCollisionsInDiagonals(x, y);
		//Time to put her back
		board[y][x] = 1;
		return collisions;
	}
	
	
	/**Checks if we can the column is already filled with eggs */
	private int getCollisionsInColumn(int x, int row){
		int eggsInColumn = 0;
		for (int y = 0;y<height;y++){
			if(y == row){
				continue;
			}
			if(board[y][x]==1){
				eggsInColumn++;
			}
		}
		return eggsInColumn;
	}
	
	/**Checks if we can the diagonals from this cell is already filled with eggs */
	private int getCollisionsInDiagonals(int initialX, int initialY){
		int x;
		int y;
		if(initialX<initialY){
			x=0;
			y=initialY-initialX;
		}
		else{
			x=initialX-initialY;
			y=0;
		}
		int eggsInDiagonal = 0;
		for(int i = 0; i+x<width && i+y<height;i++){
			if(board[y+i][x+i]==1){
				eggsInDiagonal++;
			}
		}
		if(width-1-initialX<initialY){
			x=width-1;
			y=initialY-x+initialX;
		}
		else{
			x=initialX+initialY;
			y=0;
		}
		for(int i = 0; x-i>0 && i+y<height;i++){
			if(board[y+i][x-i]==1){
				eggsInDiagonal++;
			}
		}
		return eggsInDiagonal;
	}
	
	public boolean isSolved(){
		for(int y=0;y<height;y++){
			for(int x=0;x<width;x++){
				if(board[y][x]==1 && !(getCollisionsToQueen(x, y)==0)){
					return false;
				}
			}
		}
		return true;
	}
	
	/**Used to print out the board in a lovely way*/
	public String toString(){
		String out="";
		for(int[] row: board){
			for(int i: row){
				out+=Integer.toString(i);
				out+=" ";
			}
			out+="\n";
		}
		return out;
	}
}
