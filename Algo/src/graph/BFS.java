package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS {
	static Scanner in = new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		// startIndex is for adjusting array index 0 or other.
		int startIndex = 1;
		int nodeCount = 5, edgeCount = 4, node = 0, edge;
		for(int i = 0; i<nodeCount + startIndex; i++) {
			list.add(new ArrayList<>());
			System.out.println("Object added:"+i);
		}
		/*for(int i = 0; i<edgeCount; i++) {
			node = in.nextInt();
			edge = in.nextInt();
			list.get(node).add(edge);
			// if bidirectional ...
			//list.get(edge).add(node);
		}*/
		list.get(1).add(2);
		list.get(1).add(3);
		list.get(3).add(4);
		list.get(4).add(5);
		
		list.get(2).add(1);
		list.get(3).add(1);
		list.get(4).add(3);
		list.get(5).add(4);
		
		for(int i = startIndex; i< nodeCount + startIndex; i++) {
			System.out.print("\nNode "+i+" is connected with: "+list.get(i));
		}
		int []path = bfs(list, 2, nodeCount);
		//for(int eg : path)System.out.print(eg+", ");
		for(int i = 0; i< path.length ;i++) {
			System.out.println(i+" : "+path[i]);
		}

	}
	
	static int[] bfs(ArrayList<ArrayList<Integer>> list, int src,int nodeCount){
		int []ar = new int[nodeCount+1];
		boolean []isVisited = new boolean[nodeCount+1];
		Arrays.fill(ar, -1);
		Arrays.fill(isVisited, false);
		Queue<Integer> q = new LinkedList<>();
		
		q.offer(src);
		int dis = 1, cur;
		while(!q.isEmpty()) {
			cur = q.poll();
			for(int eg : list.get(cur))
			{
				if(isVisited[eg])continue;
				isVisited[eg] = true;
				q.offer(eg);
				ar[eg] = dis;
			}
			if(!list.get(cur).isEmpty())dis++;
		}
		
		
		return ar;
	}

}
