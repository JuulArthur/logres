package exercise2;

public class Maps {
	public static int[][] mapEasy = {{0,2,2,2},{0,0,4,3}, {0,3,4,2}, {0,4,1,2}, {1,2,0,2},{1,4,2,2}};
	public static int[][] mapVeryEasy = {{0,2,2,2}};
	
	public static int[][] getMap(int i){
		if(i==0){
			return mapEasy;
		}
		else if(i==1){
			return mapVeryEasy;
		}
		else{
			return null;
		}
	}

}
