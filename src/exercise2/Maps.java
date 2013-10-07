package exercise2;

public class Maps {
	//Contains the given maps
	public static int[][] mapEasy = {{0,2,2,2},{0,0,4,3}, {0,3,4,2}, {0,4,1,2}, {1,2,0,2},{1,4,2,2}};
	public static int[][] mapMedium = {{0,1,2,2},{0,0,5,3}, {0,1,3,2}, {0,3,0,2}, {1,0,2,3},{1,2,0,2},{1,3,1,2},{1,3,3,3},{1,4,2,2},{1,5,0,2},{1,5,2,2}};
	public static int[][] mapHard = {{0,2,2,2},{0,0,4,2}, {0,0,5,2}, {0,2,5,2}, {0,4,0,2},{1,0,0,3},{1,1,1,3},{1,2,0,2},{1,3,0,2},{1,4,2,2},{1,4,4,2},{1,5,3,3}};
	public static int[][] mapExpert = {{0,0,2,2},{0,0,1,3}, {0,0,5,2}, {0,1,0,2}, {0,2,3,2},{0,3,4,2},{1,0,3,2},{1,2,4,2},{1,3,0,3},{1,4,0,2},{1,4,2,2},{1,5,2,2},{1,5,4,2}};
	
	public static int[][] getMap(int i){
		if(i==0){
			return mapEasy;
		}
		else if(i==1){
			return mapMedium;
		}
		else if(i==2){
			return mapHard;
		}
		else if(i==3){
			return mapExpert;
		}
		else{
			return null;
		}
	}

}
