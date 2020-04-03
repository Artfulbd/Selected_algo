package csp.nQueen;
import java.util.Scanner;
public class Driver {
	static Scanner in = new Scanner(System.in);
	public static void main(String[] args) {
		System.out.print("Enter n: ");
		int n = in.nextInt();
		System.out.println();
		Nqueen nq = new Nqueen(n);
		nq.solve();
		//nq.solveAndPrintRout();
		
		//this is backtrack search

	}

}
