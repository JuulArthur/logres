package exercise3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Node implements Comparable<Node>{
	public int[][] board;
	private int width;
	private int height;
	private int k;
	private List<Node> neighbours = new ArrayList<Node>();
	private int eggs;
	private double value;
	private int state;
	
	
	public Node(int height, int width, int k){
		this.height=height;
		this.width=width;
		this.k=k;
		this.board = new int[width][height];
		this.board[0][0]=1;
		this.eggs = 1;
		this.value = objectiveFunction();
		this.state = Arrays.hashCode(board);
	}
	
	public Node(int height, int width, int k, int[][] board, int eggs){
		this.height=height;
		this.width=width;
		this.k = k;
		this.board = board;
		this.eggs = eggs;
		this.value = objectiveFunction();
		this.state = board.hashCode();
	}
	
	public int[][] cloneBoard(int[][] board){
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
			if(neighbourBoard[y][x]==0){
				neighbourBoard[y][x]=1;
				add=true;
			}
			else{
				add = placeNextPossibleCell(x,y, neighbourBoard);
			}
			if(add){
				addNeighbour(new Node(height, width, k, neighbourBoard, eggs+1));
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
				addNeighbour(new Node(height, width, k, neighbourBoard, eggs-1));
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
			if(neighbourBoard[y][x]==0){
				neighbourBoard[y][x]=1;
				add = true;
			}
			else{
				add = placeNextPossibleCell(x,y, neighbourBoard);
			}
			if(add && remove){
				addNeighbour(new Node(height, width, k, neighbourBoard, eggs));
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
				if(neighbourBoard[y][x]==1){
					neighbourBoard[y][x]=0;
					return true;
				}
			}
			x=0;
		}
		return false;
	}

	private boolean placeNextPossibleCell(int x, int y, int[][] neighbourBoard) {
		for(int row = y;row<height;row++){
			for (int column = x; column<width; column++){
				if(neighbourBoard[y][x]==0){
					neighbourBoard[y][x]=1;
					return true;
				}
			}
		}
		return false;
		
	}

	public double objectiveFunction(){
		int nofIllegaleggs = 0;
		eggs = getNumberOfEggs();
		nofIllegaleggs+=checkRows();
		nofIllegaleggs+=checkColumns();
		nofIllegaleggs+=checkDiagonals();
		double value = 1;
		value -= (double)nofIllegaleggs/10;
		//Subtract point for the difference between desired eggs and how many we have
		value -= Math.abs((double) (width<height ? eggs-(k*width) : eggs-(k*height)))/100;
//		System.out.println(eggs);
//		System.out.println(k*width);
//		System.out.println("NOF: "+nofIllegaleggs);
//		System.out.println((double)Math.abs(eggs-(k*width))/100);
//		System.out.println(value>0 ? value:0);
		return(value>0 ? value : 0);
		
	}
	
	private int checkRows(){
		int nofIllegalEggs = 0;
		int eggsInRow;
		for (int[] row : board){
			eggsInRow=0;
			for(int i : row){
				if(i==1){
					eggsInRow++;
				}
			}
			if(eggsInRow>k){
				nofIllegalEggs+=eggsInRow-k;
			}
		}
//		System.out.println(this);
//		System.out.println("ROWS: " + nofIllegalEggs);
		return nofIllegalEggs;
	}
	
	private int checkColumns(){
		int nofIllegalEggs = 0;
		int eggsInColumn;
		for (int x = 0;x<width;x++){
			eggsInColumn=0;
			for(int y = 0;y<height;y++){
				if(board[y][x]==1){
					eggsInColumn++;
				}
			}
			if(eggsInColumn>k){
				nofIllegalEggs+=eggsInColumn-k;
			}
		}
//		System.out.println(this);
//		System.out.println("Columns: " + nofIllegalEggs);
		return nofIllegalEggs;
	}
	
	private int checkDiagonals(){
		int nofIllegalEggs = 0;
		int eggsInDiagonalR;
		int eggsInDiagonalL;
		for (int column = 0; column<width;column++){
			eggsInDiagonalR=0;
			eggsInDiagonalL=0;
			for(int i = 0; (column+i)<width && i<height;i++){
				if(board[i][column+i]==1){
					eggsInDiagonalR++;
				}
				if(board[i][width-column-i-1]==1){
					eggsInDiagonalL++;
				}
			}
			if(eggsInDiagonalR>k){
				nofIllegalEggs+=eggsInDiagonalR-k;
			}
			if(eggsInDiagonalL>k){
				nofIllegalEggs+=eggsInDiagonalL-k;
			}
		}
		for (int level = 1; level<height-1;level++){
			eggsInDiagonalR=0;
			eggsInDiagonalL=0;
			for(int i = 0; (level+i)<height && i<width;i++){
				if(board[level+i][i]==1){
					eggsInDiagonalR++;
				}
				if(board[level+i][width-1-i]==1){
					eggsInDiagonalL++;
				}
			}
			if(eggsInDiagonalR>k){
				nofIllegalEggs+=eggsInDiagonalR-k;
			}
			if(eggsInDiagonalL>k){
				nofIllegalEggs+=eggsInDiagonalL-k;
			}
		}
//		System.out.println(this);
//		System.out.println("DIAGONAL: " + nofIllegalEggs);
		return nofIllegalEggs;
	}
	
	public int getState(){
		return state;
	}
	
	public double getValue(){
		return value;
	}
	
//	public void generateBoard(int k){
//	int startX=0;
//	for (int y=0; y<height;y++){
//		for(int x=0;x<width;x++){
//			board[y][x] = (x>=startX && x<startX+k ? 1 : 0);
//		}
//		startX+=k;
//		if(startX+k>width){
//			startX=0;
//		}
//	}
//}
	
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
