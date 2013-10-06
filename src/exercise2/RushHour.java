package exercise2;

import java.util.ArrayList;

public class RushHour {
	
	private static ArrayList<ArrayList<Car>> map = new ArrayList<ArrayList<Car>>();
	
	public static void main(String args[]){
		initializeMap(2);
		for (ArrayList<Car> row: map){
			System.out.println(row);
		}
		RushHourNode firstNode = new RushHourNode(0,null,new Board(map));
		int movesToGoal = Astar.solve(firstNode);
		System.out.println(movesToGoal);
		System.out.println(Astar.numberOfCreatedNodes);
		movesToGoal = BFS.solve(firstNode);
		System.out.println(movesToGoal);
		System.out.println(BFS.numberOfCreatedNodes);
		movesToGoal = DFS.solve(firstNode);
		System.out.println(movesToGoal);
		System.out.println(DFS.numberOfCreatedNodes);
	}
	
	public static void initializeMap(int mapNumber){
		int[][] table = Maps.getMap(mapNumber);
		for (int y = 0; y<6; y++){
			ArrayList<Car> temp = new ArrayList<Car>();
			for (int x = 0; x<6; x++){
				temp.add(null);
			}
			map.add(temp);
		}
		for (int y = 0; y<table.length; y++){
			Car car = new Car(y,table[y][3], table[y][0]==0);
			if (car.getIsHorizontal()){
				for (int i = 0; i<car.getSize(); i++){
					map.get(table[y][2]).set(table[y][1]+i, car);
				}
			}
			else{
				for (int i = 0; i<car.getSize(); i++){
					map.get(table[y][2]+i).set(table[y][1], car);
				}
			}
		}
	}

}
