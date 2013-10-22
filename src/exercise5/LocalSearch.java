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
		System.out.println("Enter k: ");
		int k = Integer.valueOf(reader.readLine());
		Board p = new Board(height, width, k);
		//Seems like this is a nice temperature to start with
		double t = 10.0;
		while(t>0){
			if(p.isSolved()){
				System.out.println("I made it!");
				System.out.println("Here it comes!");
				System.out.println(p);
				System.out.println("I have given the answer");
				break;
			}
			p.modifyBoard();
			t-=0.001;
		}
		System.out.println(p);

	}

}
