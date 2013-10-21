package exercise3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Node implements Comparable<Node>{
	public int[][] board;
	private int width;
	private int height;
	private int k;
	private List<Node> neighbours = new ArrayList<Node>();
	private int eggs;
	//The value the objective function gives us so we don't have to calculated it more than one time
	private double value;
	
	/**This constructor is only used when creating the first node*/
	public Node(int height, int width, int k){
		this.height=height;
		this.width=width;
		this.k=k;
		this.board = new int[width][height];
		this.eggs = 1;
		this.value = objectiveFunction();
	}
	
	/**This constructor is used by the node when it creates neighbours */
	public Node(int height, int width, int k, int[][] board){
		this.height=height;
		this.width=width;
		this.k = k;
		this.board = board;
		this.eggs = getNumberOfEggs();
		this.value = objectiveFunction();
	}
	
	/** We have a method to clone the board so we don't get any wrong references
	 * @param board  the board to clone
	 * */
	private int[][] cloneBoard(int[][] board){
		int[][] returnBoard = new int[height][width];
		for(int y = 0; y<height; y++){
			for(int x = 0; x<width; x++){
				returnBoard[y][x]=board[y][x];
			}
		}
		return returnBoard;
	}
	
	/** Generates up to 30 neighbours were:
	 * 10 boards have added one egg
	 * 10 boards have removed one egg
	 * 10 boards have moved one egg*/
	public List<Node> generateNeighbours(){
		Random random = new Random();
		//x and y are used to give a random position to start looking in the board
		int x;
		int y;
		//Booleans to check if we actually where able to remove or add an egg.
		boolean remove;
		boolean add;
		//The board we modify to create a neighbour
		int [][] neighbourBoard;
		//Create neighbours where we add eggs
		for(int i = 0;i<10;i++){
			add=false;
			//Create an identical clone to our board
			neighbourBoard = cloneBoard(this.board);
			x = random.nextInt(width);
			y = random.nextInt(height);
			//If the cell is empty and it doesen't collide with too many other eggs
			//we are allowed to add an egg here
			if(neighbourBoard[y][x]==0 && cellIsAvailable(x, y)){
				neighbourBoard[y][x]=1;
				add=true;
			}
			//else we check for the first place we can add an egg
			else{
				add = placeNextPossibleCell(x,y, neighbourBoard);
			}
			//If we managed to add an egg we want to create the neighbor
			if(add){
				addNeighbour(new Node(height, width, k, neighbourBoard));
			}
		}
		//Create neighbours where we remove eggs
		//This works in the same way ass add egg
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
		//This is just a combination of add egg and remove egg
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
	
	//Maybe not necessary, but we have a method to add a neighbor
	private void addNeighbour(Node node){
		this.neighbours.add(node);
	}
	
	/**Find the first egg after the given position to remove*/
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
		//If we didn't find an egg to remove after the given position, let's check from the start
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

	/**Check for the first place after the given position where we can place an egg*/
	private boolean placeNextPossibleCell(int x, int y, int[][] neighbourBoard) {
		for(int row = y;row<height;row++){
			for (int column = x; column<width; column++){
				if(neighbourBoard[row][column]==0 && cellIsAvailable(column, row)){
					neighbourBoard[row][column]=1;
					return true;
				}
			}
		}
		//If we didn't find an available cell after the given position, let's check from the start
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

	/**This function is used to evaluated how good the given node is */
	private double objectiveFunction(){;
		double value = 1;
		//Subtract point for the difference between desired eggs and how many we have
		//We subtract 0.02 points for each step we are away from our goal
		value -= (Math.abs((double) (width<height ? eggs-(k*width) : eggs-(k*height)))/100)*2;
		return(value>0 ? value : 0);
		
	}
	
	/**Checks whether we can place an egg in the given cell or not */
	private boolean cellIsAvailable(int x, int y){
		return (availableRow(y)&&availableColumn(x)&&availableDiagonals(x, y));
	}
	
	/**Checks if we can the row is already filled with eggs */
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
	
	/**Checks if we can the column is already filled with eggs */
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
	
	/**Checks if we can the diagonals from this cell is already filled with eggs */
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
	
	/**Get the value created by the objective function */
	public double getValue(){
		return value;
	}
	
	/**Guess this method name speaks for itself */
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

@Override
/**Used to when sorting the list with nodes */
public int compareTo(Node node) {
	double returnValue = (node.objectiveFunction()-this.objectiveFunction())*1000;
	return  (int) (returnValue);
}
}
