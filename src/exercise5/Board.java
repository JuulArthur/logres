package exercise5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board{
	public int[][] board;
	private int width;
	private int height;

	public Board(int height, int width){
		this.height=height;
		this.width=width;
		this.board = generateBoard(height, width);
	}
	/** 
	 * Generates a random board
	 * @param  height is the height of the board
	 * @param  width is the width of the board
	 * */
	private int[][] generateBoard(int height, int width){
		Random random = new Random();
		int[][] board = new int[height][width];
		for(int y = 0; y<height;y++){
			board[y][random.nextInt(width)] = 1;
		}
		return board;
	}
	
	/** 
	 * Modifies the board to try to find a better soloution
	 * */
	public void modifyBoard(){
		Random random = new Random();
		//x and y are used to give a random position to start looking in the board
		int y = random.nextInt(height);
		double lowest = Double.MAX_VALUE;
		double collisions;
		ArrayList<Integer> cells = new ArrayList<Integer>();
		//We only need allCells to print information to task 2
//		double[] allCells = new double[width];
//		System.out.println(this);
//		System.out.println("Y: "+y);
		for(int x = 0;x<width; x++){
			if(board[y][x]==1){
				//We remove the queen because we don't want to count her as an extra collision
				//And we will always put her back at the end
				board[y][x]=0;
				//If some other cell has the equal amount of collisions we want to move the queen
				//to the other cell
				collisions = getCollisionsInCell(x, y)+0.1;
			}
			else{
				//Find the number of collisions in the current cell
				collisions = getCollisionsInCell(x, y);
			}
//			allCells[x] = collisions;
			if(collisions == lowest){
				cells.add(x);
			}
			else if(collisions < lowest){
				lowest = collisions;
				cells.clear();
				cells.add(x);
			}
		}
		//If we have more than one candidate for where to put the queen,
		//we have to put her in a random position
		if(cells.size()>1){
			board[y][cells.set(random.nextInt(cells.size()),1)]=1;
		}
		else{
			board[y][cells.get(0)]=1;
		}
		//Commented out because we only use it when we need to print for task 2
//		System.out.println(printCells(allCells));
//		System.out.println(" ");
	}
	
	/**Checks whether we can place an egg in the given cell or not 
	 * @param  x the x position of the queen
	 * @param  y the y position of the queen
	 * */
	private int getCollisionsInCell(int x, int y){
		return (getCollisionsInColumn(x, y)+getCollisionsInDiagonals(x, y));
	}
	
	/**Only used when we check if we have found the solution, the only difference between this and
	 * getCollisionsInCell is that we remove the queen and put her back in the same position so we don't
	 * count her as an collision
	 * @param  x the x position to the current queen
	 * @param  y the y position to the current queen
	 * */
	private int getCollisionsToQueen(int x, int y){
		//We don't want this queen to be counted as a collision
		board[y][x] = 0;
		int collisions = getCollisionsInColumn(x, y)+getCollisionsInDiagonals(x, y);
		//Time to put her back
		board[y][x] = 1;
		return collisions;
	}
	
	
	/**Checks if the column is already filled with eggs */
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
		//Search down-right
		//Used to find the start position for where we will check
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
		//Search down-left
		//Used to find the start position for where we will check
		if(width-1-initialX<initialY){
			x=width-1;
			y=initialY-x+initialX;
		}
		else{
			x=initialX+initialY;
			y=0;
		}
		for(int i = 0; x-i>=0 && i+y<height;i++){
			if(board[y+i][x-i]==1){
				eggsInDiagonal++;
			}
		}
		return eggsInDiagonal;
	}
	
	/**Checks whether the board is solved or not */
	public boolean isSolved(){
		for(int y=0;y<height;y++){
			for(int x=0;x<width;x++){
				//Checks if each queen is without collision
				if(board[y][x]==1 && !(getCollisionsToQueen(x, y)==0)){
					return false;
				}
			}
		}
		return true;
	}
	/**Used only for task 2 when we need to print all collisions in the current row */
//	private String printCells(double[] cells){
//		String out = "";
//		for(double d : cells){
//			out+=Double.toString(d);
//			out+=" ";
//		}
//		return out;
//	}
	
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
