import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main{
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

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(br.readLine());
		List<List<Node>> adjList= new ArrayList<>();
		for(int i=0;i<N+1;i++) {
			adjList.add(new ArrayList<Node>());
		}
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			long weight = Integer.parseInt(st.nextToken());
			adjList.get(from).add(new Node(to,weight));
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		long[] distance = new long[N+1];
		boolean[] visited = new boolean[N+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		distance[start] = 0;
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			if(visited[cur.to]) continue;
			visited[cur.to] = true;
			if(cur.to == end) break;
			for(Node next : adjList.get(cur.to)) {
				if(distance[next.to] > distance[cur.to] + next.weight) {
					distance[next.to] = distance[cur.to] + next.weight;
					pq.offer(new Node(next.to, distance[next.to]));
				}
			}
		}
		System.out.println(distance[end]);
	}

}
