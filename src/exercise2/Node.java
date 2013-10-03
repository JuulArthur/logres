package exercise2;

import java.util.ArrayList;

public class Node {
	private int g;
	private int h;
	private int f;
	private Boolean open;
	private Node parent;
	private ArrayList<Node> children;
	
	public Node(int g, int h, int f, Boolean open, Node parent){
		this.g = g;
		this.h = h;
		this.f = f;
		this.open = open;
		this.parent = parent;
	}
}
