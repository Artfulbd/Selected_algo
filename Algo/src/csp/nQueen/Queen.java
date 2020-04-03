package csp.nQueen;

import java.util.ArrayList;

public class Queen {
	private int  violationCount, index;
	private ArrayList<Coordinate> path;
	private Coordinate own;
	
	Queen(int x, int y, ArrayList<Coordinate> cr){
		this.own = new Coordinate(x,y);
		this.path = cr;
		this.index = y;
	}
	
	int getIndex() {
		return index;
	}
	void setViolationCount(int vc) {
		this.violationCount = vc;
	}
	int getViolationCount() {
		return violationCount;
	}
	ArrayList<Coordinate> getPath() {
		return path;
	}
	 
	Coordinate getCoordinate() {
		 return own;
	 }
}