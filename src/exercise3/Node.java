package exercise3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.sun.corba.se.spi.legacy.connection.GetEndPointInfoAgainException;

public class Node implements Comparable<Node>{
	public int[][] board;
	private int width;
	private int height;
	private int k;
	private List<Node> neighbours = new ArrayList<Node>();
	private int eggs;
	private double value;
	
	
	public Node(int height, int width, int k){
		this.height=height;
		this.width=width;
		this.k=k;
		this.board = new int[width][height];
		this.eggs = 1;
		this.value = objectiveFunction();
	}
	
	public Node(int height, int width, int k, int[][] board){
		this.height=height;
		this.width=width;
		this.k = k;
		this.board = board;
		this.eggs = getNumberOfEggs();
		this.value = objectiveFunction();
	}
	
	private int[][] cloneBoard(int[][] board){
		int[][] returnBoard = new int[height][width];
		for(int y = 0; y<height; y++){
			for(int x = 0; x<width; x++){
				returnBoard[y][x]=board[y][x];
			}
		}
		return returnBoard;
	}
	
	
	public List<Node> generateNeighbours(){
		Random random = new Random();
		int x;
		int y;
		boolean remove;
		boolean add;
		int [][] neighbourBoard;
		//Create neighbours where we add eggs
		for(int i = 0;i<10;i++){
			add=false;
			neighbourBoard = cloneBoard(this.board);
			x = random.nextInt(width);
			y = random.nextInt(height);
			if(neighbourBoard[y][x]==0 && cellIsAvailable(x, y)){
				neighbourBoard[y][x]=1;
				add=true;
			}
			else{
				add = placeNextPossibleCell(x,y, neighbourBoard);
			}
			if(add){
				addNeighbour(new Node(height, width, k, neighbourBoard));
			}
		}
		//Create neighbours where we remove eggs
		for(int i = 0;i<10 && i<eggs;i++){
			remove = false;
			neighbourBoard = cloneBoard(this.board);
			x = random.nextInt(width);
			y = random.nextInt(height);
			if(neighbourBoard[y][x]==1){
				neighbourBoard[y][x]=0;
				remove = true;
			}
			else{
				remove = removeNextPossibleCell(x,y, neighbourBoard);
			}
			if(remove){
				addNeighbour(new Node(height, width, k, neighbourBoard));
			}
		}
		//Create neighbours where we move eggs
		for(int i = 0;i<10 && i<eggs;i++){
			remove = false;
			add = false;
			neighbourBoard = cloneBoard(this.board);
			x = random.nextInt(width);
			y = random.nextInt(height);
			if(neighbourBoard[y][x]==1){
				neighbourBoard[y][x]=0;
				remove = true;
			}
			else{
				remove = removeNextPossibleCell(x,y, neighbourBoard);
			}
			x = random.nextInt(width);
			y = random.nextInt(height);
			if(neighbourBoard[y][x]==0 && cellIsAvailable(x, y)){
				neighbourBoard[y][x]=1;
				add = true;
			}
			else{
				add = placeNextPossibleCell(x,y, neighbourBoard);
			}
			if(add && remove){
				addNeighbour(new Node(height, width, k, neighbourBoard));
			}
		}
		return this.neighbours;
	}
	
	private void addNeighbour(Node node){
		this.neighbours.add(node);
	}
	
	private boolean removeNextPossibleCell(int x, int y, int[][] neighbourBoard) {
		for(int row = y;row<height;row++){
			for (int column = x; column<width; column++){
				if(neighbourBoard[row][column]==1){
					neighbourBoard[row][column]=0;
					return true;
				}
			}
			x=0;
		}
		for(int row = 0;row<=y;row++){
			for(int column = 0;column<width;column++){
				if(neighbourBoard[row][column]==1){
					neighbourBoard[row][column]=0;
					return true;
				}
			}
			
		}
		return false;
	}

	private boolean placeNextPossibleCell(int x, int y, int[][] neighbourBoard) {
		for(int row = y;row<height;row++){
			for (int column = x; column<width; column++){
				if(neighbourBoard[row][column]==0 && cellIsAvailable(column, row)){
					neighbourBoard[row][column]=1;
					return true;
				}
			}
		}
		for(int row = 0;row<=y;row++){
			for (int column = 0; column<width; column++){
				if(neighbourBoard[row][column]==0 && cellIsAvailable(column, row)){
					neighbourBoard[row][column]=1;
					return true;
				}
			}
		}
		return false;
		
	}

	private double objectiveFunction(){;
		double value = 1;
		//Subtract point for the difference between desired eggs and how many we have
		//We subtract 0.02 points for each step we are away from our goal
		value -= (Math.abs((double) (width<height ? eggs-(k*width) : eggs-(k*height)))/100)*2;
		return(value>0 ? value : 0);
		
	}
	
	private boolean cellIsAvailable(int x, int y){
		return (availableRow(y)&&availableColumn(x)&&availableDiagonals(x, y));
	}
	
	private boolean availableRow(int y){
		int eggsInRow = 0;
		for (int i : board[y]){
			if(i==1){
				eggsInRow++;
				if(eggsInRow>=k){
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean availableColumn(int x){
		int eggsInColumn = 0;
		for (int y = 0;y<height;y++){
			if(board[y][x]==1){
				eggsInColumn++;
				if(eggsInColumn>=k){
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean availableDiagonals(int initialX, int initialY){
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
				if(eggsInDiagonal>=k){
					return false;
				}
			}
		}
		eggsInDiagonal=0;
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
				if(eggsInDiagonal>=k){
					return false;
				}
			}
		}
		return true;
	}
	
	public double getValue(){
		return value;
	}
	
	private int getNumberOfEggs(){
		int numberOfEggs=0;
		for(int[] row : this.board){
			for(int i : row){
				if(i==1){
					numberOfEggs++;
				}
			}
		}
		return numberOfEggs;
	}
	
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

@Override
public int compareTo(Node node) {
	double returnValue = (node.objectiveFunction()-this.objectiveFunction())*1000;
	return  (int) (returnValue);
}
}
