package csp.nQueen;

import java.util.ArrayList;
import java.util.Stack;
class Coordinate{
	int x,y;
	Coordinate(int a, int b){
		x = a;
		y = b;
	}

	@Override
	public boolean equals(Object obj) {
		Coordinate c = (Coordinate) obj;
		return (c != null) && c.x == this.x && c.y == this.y ? true : false;
	}
}

public class Nqueen {
	private int size;                       //plot size
	private String [][]table;/* = {{ "-","Q1","-","Q3"}, 
								{ "Q0","-","-","-"},
								{ "-","-","Q2","-"},
								{ "-","-","-","-"}};    */          //plot
	private ArrayList<Queen> qns;           //queens list
	private int maxViolatingIndex,  totalViolation;
	private int previousViolatorIndex;
	private Stack<Integer> st;

	Nqueen(int sz){
		size = sz;
		table = new String[sz][sz];
		printTableIndex();
		System.out.println("Random");
		assign();
		//customAssign();
		
		printTable();
		//printQueenCondinates();
		//printQueenRout();
	}
	
	public void solve() {
		reduceViolation();
	}
	
	public void solveAndPrintRout() {
		reduceViolation();
		System.out.println("\n***-Printing all fixed queens rout-***");
		printAllQueensRout();
	}

	private void assign() {
		qns = new ArrayList<>(size);
		int from = 0, to = size - 1, r;
		for(int i = 0 ; i< size; i++) {
			for(int j =0 ;j<size; j++)table[j][i] = "-";
			r = from + (int)(Math.random()*500) % (to + 1 - from) ;
			table[r][i] = "Q"+Integer.toString(i); 			             // actually it is j index
			qns.add(new Queen(r, i, tracePath(r, i))); 					 // adding new queen

		}
	}
	
	private void customAssign() {
		qns = new ArrayList<>(size);
		qns.add(new Queen(1, 0, tracePath(1, 0)));
		qns.add(new Queen(0, 1, tracePath(0, 1)));
		qns.add(new Queen(2, 2, tracePath(2, 2)));
		qns.add(new Queen(0, 3, tracePath(0, 3)));
		
	}

	private void reduceViolation() {
		int i = 0;
		do {
			if(i == 800) {
				assign();
				i = 0;
			}
			i++;
			checkAllViolation();
			if(totalViolation == 0)break;
			st = new Stack<>();
			reAssign(maxViolatingIndex);
			
			st.pop();
			if(maxViolatingIndex == previousViolatorIndex && !st.empty()) {
				maxViolatingIndex = st.peek();	
			}
			previousViolatorIndex = maxViolatingIndex;
		}while(totalViolation >= 0 );
		System.out.println("\nAfter fix:");
		printTable();

	}

	private void reAssign(int j) {
		int minViolation = size-1;
		int minViolationIndex = qns.get(j).getIndex(); 
		st.push(minViolationIndex);
		Coordinate cur, old = qns.get(j).getCoordinate();      // preserving old coordinate
		

		for(int i = 0; i < size; i++) {
			cur = new Coordinate(i,j);                       // current test coordinate
			if(!old.equals(cur)) {                           // preventing old one from re-check 
				qns.set(j, new Queen(i,j,tracePath(i, j)));  // replacing old Q with new Q	
				checkViolationFor(j);
				
				if(qns.get(j).getViolationCount() <= minViolation) {
					minViolation = qns.get(j).getViolationCount();
					minViolationIndex = i;
					st.push(i);
					
				}	
			}	
		}


		// final assigning
		qns.set(j, new Queen(minViolationIndex,j,tracePath(minViolationIndex, j)));
		table[minViolationIndex][j] = "Q"+Integer.toString(j);
		table[old.x][j] = "-";

		checkViolationFor(j);
		qns.get(j).setViolationCount(minViolation);
	}

	private void checkAllViolation() {
		this.totalViolation = 0;
		int mxViolation = 0, index = 0;
		for(int j = 0; j < size; j++) {
			checkViolationFor(j);
			if(mxViolation < qns.get(j).getViolationCount()) {
				index = j;
				mxViolation = qns.get(j).getViolationCount();
			}
			this.totalViolation += qns.get(j).getViolationCount();
			//System.out.println("Q"+j+" violates "+qns.get(j).getViolationCount()+" queen."+
				//	"("+qns.get(j).getCoordinate().x+","+qns.get(j).getCoordinate().y+") ");
			//printRout(qns.get(j));
			//System.out.println();
		}
		this.maxViolatingIndex = index;


	}
	private void checkViolationFor(int j) {
		Queen q = qns.get(j);
		int violationCount = 0;
		for(Queen qn: qns) {
			if(!qn.getCoordinate().equals(q.getCoordinate())) { 
				if(q.getPath().contains(qn.getCoordinate())) {     // true if qn is on q's path
					violationCount++;
				}
			}
		}
		qns.get(j).setViolationCount(violationCount);
	}


	private ArrayList<Coordinate> tracePath(int x, int y) {
		ArrayList<Coordinate> path = new ArrayList<Coordinate>();
		for(int i = 0; i< size; i++) {
			path.add(new Coordinate(i,y));
			path.add(new Coordinate(x,i));
		}
		path.remove(new Coordinate(x,y));
		//getting diagonals
		leftUp(x,y, path);
		leftDown(x,y, path);
		rightUp(x,y, path);
		rightDown(x,y, path);
		return path;
	}

	private void leftUp(int i, int j, ArrayList<Coordinate> path) {
		i--;j--;
		if((i < size && j>=0) && (i>=0 && j<size)) {
			path.add(new Coordinate(i,j));
			leftUp(i,j, path);
		}
	}

	private void leftDown(int i, int j, ArrayList<Coordinate> path) {
		i++;j--;
		if( (i < size && j < size && j>=0)) {
			path.add(new Coordinate(i,j));
			leftDown(i,j, path);
		}
	}

	private void rightUp(int i, int j, ArrayList<Coordinate> path) {
		i--;j++;
		if( i>=0 && j<size) {
			path.add(new Coordinate(i,j));
			rightUp(i,j, path);
		}

	}

	private void rightDown(int i, int j, ArrayList<Coordinate> path) {
		i++;j++;
		if( i < size && j >=0 && j < size) {
			path.add(new Coordinate(i,j));
			rightDown(i,j, path);
		}
	}




	// UTILITY methods
	private void printAllQueensRout() {              //print all queens path(graphically)
		int n = 0;
		for(Queen q: qns) {
			printQueenRoutOf(q,n++);
			System.out.println();
		}		
	}
	
	private void printQueenRoutOf(Queen q, int index) {
		System.out.println("For Q"+index);
		for(int i = 0 ; i< size; i++) {
			for(int j = 0; j<size; j++) {				
				if(q.getPath().contains(new Coordinate(i,j))){
					if(q.getCoordinate().equals(new Coordinate(i,j)) )System.out.print("Q"+index+" ");
					else System.out.print("#  ");
				}
				else System.out.print("-  ");
			}
			System.out.println();
		}
	}

	public void printRout(Queen q) {
		for(Coordinate cd: q.getPath())System.out.print("("+cd.x+","+cd.y+") ");
	}

	private void printQueenCondinates() {
		System.out.println("Queen cordinates: ");
		for(int i = 0; i < size; i++) {
			int x = qns.get(i).getCoordinate().x;
			int y = qns.get(i).getCoordinate().y;
			System.out.println("Q"+i+" ("+x+","+y+")");

		}
	}

	private void printTable() {
		System.out.println("Queens: ("+size+"*"+size+")");
		for(int i = 0; i< size; i++) {
			for(int j = 0; j< size; j++) {
				System.out.printf("%2s  ",table[i][j]);
			}
			System.out.println();
		}
	}

	private void printTableIndex() {
		System.out.println("Table index:");
		for(int i = 0; i< size; i++) {
			for(int j = 0; j< size; j++) {
				System.out.printf("%d%d  ",i,j);
			}
			System.out.println();
		}
	}



}
