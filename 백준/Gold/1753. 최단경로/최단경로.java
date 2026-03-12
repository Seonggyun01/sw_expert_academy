import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node>{
		int to;
		long weight;

		public Node(int to, long weight) {
			super();
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return Long.compare(this.weight, o.weight);
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		int start = Integer.parseInt(br.readLine());
		List<Node>[] adjList = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++) {
		    adjList[i] = new ArrayList<>(); 
		}
		for(int i=0;i<E;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjList[from].add(new Node(to,weight));
		}
		long[] minDistance = new long[V+1];
		Arrays.fill(minDistance, Integer.MAX_VALUE);
		boolean[] visited = new boolean[V+1];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		minDistance[start] = 0;
		pq.offer(new Node(start, 0));
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			if(visited[cur.to]) continue;
			visited[cur.to] = true;
			for(Node node:adjList[cur.to]) {
				int to = node.to;
				long weight = node.weight;
				if(minDistance[to]>minDistance[cur.to]+weight) {
					minDistance[to] = minDistance[cur.to]+weight;
					pq.offer(new Node(to,minDistance[cur.to]+weight));
				}
			}
			
		}
		for(int i=1;i<=V;i++) {
			long dist = minDistance[i];
			if(dist == Integer.MAX_VALUE) {
				System.out.println("INF");
			}else {
				System.out.println(dist);
			}
			
		}
	}

}
