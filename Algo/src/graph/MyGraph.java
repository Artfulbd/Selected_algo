package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MyGraph {
	private int node, adjustedNodeCount, cost;
	private boolean[] isVisited;
	private boolean isDirected;
	private int[] distanceList;
	private ArrayList<ArrayList<Integer>> list;
	private char[] color;
	private int[][] df;
	private int time;


	MyGraph(int nodeCount, int startFrom, int cost, boolean isDirected){
		this.node = nodeCount;
		this.adjustedNodeCount = this.node + startFrom;
		this.cost = cost == 0 ? 1 : cost;
		this.list = new ArrayList<ArrayList<Integer>>();
		this.isDirected = isDirected;
		for(int i = 0; i < adjustedNodeCount; i++)list.add(new ArrayList<>());
	}

	public void add(int node, int edge) {
		list.get(node).add(edge);
		if(!isDirected)list.get(edge).add(node);
	}

	public int[] bfs(int src){
		if(list.get(src).isEmpty())return new int[adjustedNodeCount];
		distanceList = new int[adjustedNodeCount];
		isVisited = new boolean[adjustedNodeCount];
		for(int i = 0; i<adjustedNodeCount; i++) {
			distanceList[i] = -1;
			isVisited[i] = false;
		}
		//Arrays.fill(distanceList, -1);
		//Arrays.fill(isVisited, false);
		Queue<Integer> q = new LinkedList<>();

		q.offer(src);
		distanceList[src] = 0;
		isVisited[src] = true;
		int dis = cost, cur;
		boolean flag;
		while(!q.isEmpty()) {
			cur = q.poll();
			flag = false;
			for(int eg : list.get(cur))
			{
				if(isVisited[eg])continue;
				isVisited[eg] = true;
				q.offer(eg);
				distanceList[eg] = dis;
				flag = true;
			}
			if(flag)dis += cost;
		}
		return distanceList;
	}

	public void dfs(int src) {
		time = 0;
		color = new char[adjustedNodeCount];
		Arrays.fill(color, 'w');
		df = new int[adjustedNodeCount][2];
		int u,sz;
		color[src] = 'g';
		df[src][0] = ++time;
		sz = list.get(src).size();
		for(int i = 0; i < sz; i++) {
			if(color[u = list.get(src).get(i)] == 'w') {
				dfsVisit(u);
			}
		}
		color[src] = 'b';
		df[src][1] = ++time;
	}
	
	private void dfsVisit(int u) {
		//System.out.println("color ["+u+"]: "+color[u]);
		int v, sz = list.get(u).size();
		df[u][0] = ++time;
		color[u] = 'g';
		for(int i = 0; i < sz; i++) {
			if(color[v = list.get(u).get(i)] == 'w') {
				dfsVisit(v);
			}
		}
		df[u][1] = ++time;
		color[u] = 'b';		
	}
	
	public boolean isConnected(int src, int dest) {
		dfs(src);
		return df[dest][0] == 0 ? false : true;
	}
	
	public void printGraph() {
		System.out.println("**Printing graph:");
		for(int i = adjustedNodeCount - node; i< adjustedNodeCount; i++) {
			System.out.println("Node "+i+" is connected with: "+list.get(i));
		}
	}

	public int nearestNodeOf(int src) {
		if(list.get(src).isEmpty())return -1;
		return list.get(src).get(0);
	}

	public int farOffNodeOf(int src) {
		if(list.get(src).isEmpty())return -1;
		int []hold = bfs(src);
		int max = -1, ans = -1;
		for(int i = 0; i<hold.length; i++) {
			if(hold[i] != 0 && max <= hold[i]) {
				max = hold[i];
				ans = i;
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int nodeCount = 7, start = 1, target, src;
		MyGraph ug = new MyGraph(nodeCount, start, 3, !false);
		ug.add(1,2);
		ug.add(1,3);
		ug.add(3,4);
		ug.add(4,6);
		ug.add(5,7);

		src = 1;
		ug.printGraph();
		int []path = ug.bfs(src);
		for(int i = 1; i< path.length ;i++) {
			System.out.println(i+" : "+path[i]);
		}

		System.out.println("Farest node of "+src+": "+ug.farOffNodeOf(src));
		System.out.println("Nearest node of "+src+": "+ug.nearestNodeOf(src));
		target = 6;
		System.out.println("Is "+src+" connected with "+target+" ?: "+ug.isConnected(src, target));
		for(int i = 1; i<nodeCount+1; i++ ) {
			System.out.println(i+"> dis:"+ug.df[i][0]+"  fin:"+ug.df[i][1]);
		}

	}

}
