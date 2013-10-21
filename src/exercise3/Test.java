package exercise3;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[][] board = {{1,1},{1,1}};
		int width = 2;
		int height = 2;
		int k = 1;
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
		String out = "";
		for(int[] row: board){
			for(int i : row){
				out+=i+" ";
			}
			out+="\n";
		}

	}

}
