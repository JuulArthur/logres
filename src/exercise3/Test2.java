package exercise3;

import java.util.ArrayList;
import java.util.Collections;

public class Test2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		int[][] board = {{0,0,1},{0,1,1},{1,1,1}};
//		Node node = new Node(3,3,2,board,4);
//		System.out.println(node.getValue());
//		int[][] bordeen = {{0,0,0},{0,0,0},{0,0,0}};
		int[][] bordeto = {{0,0,0},{1,0,0},{0,0,0}};
//		Node nodeen = new Node(3, 3, 1,bordeen,0);
		Node nodeto = new Node(3, 3, 1,bordeto,0);
		System.out.println(nodeto.cellIsAvailable(0, 0));
	}

}
