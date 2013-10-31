package exercise5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class LocalSearch {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter height: ");
		int height = Integer.valueOf(reader.readLine());
		System.out.println("Enter width: ");
		int width = Integer.valueOf(reader.readLine());
		Board p = new Board(height, width);
		//The variable is just used as a counter so we don't go on for an eternity
		double t = 10.0;
		long start = System.currentTimeMillis();    
		while(t>0){
			if(p.isSolved()){
				System.out.println("This is the soloution\n");
				//It takes so long to print out the board that it is usually commented
				//Only uncomment when you need to check the solution
//				System.out.println(p);
				break;
			}
			p.modifyBoard();
			t-=0.001;
		}
		//print out how many milliseconds the algorithm takes to find the solution
		System.out.println(System.currentTimeMillis() - start);
	}

}
