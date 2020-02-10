package csp.nQueen;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Test {
	static Scanner in = new Scanner(System.in);

	public static void main(String[] args) 
	  { 
	    Queue<Integer> q = new LinkedList<>(); 
	  
	    // Adds elements {0, 1, 2, 3, 4} to queue 
	    for (int i=1; i<6; i++) 
	     q.add(i); 
	  
	    // Display contents of the queue. 
	    System.out.println("Elements of queue-"+q); 
	  
	    // To remove the head of queue. 
	    int removedele = q.remove(); 
	    System.out.println("removed element-" + removedele); 
	  
	    System.out.println(q); 
	  
	    // To view the head of queue 
	    int head = q.peek(); 
	    System.out.println("head of queue-" + head); 
	  
	    // Rest all methods of collection interface, 
	    // Like size and contains can be used with this 
	    // implementation. 
	    int size = q.size(); 
	    System.out.println("Size of queue-" + size); 
	  } 
}
