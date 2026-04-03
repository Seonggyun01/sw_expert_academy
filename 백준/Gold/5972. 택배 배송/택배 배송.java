import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node>{
		int to, weight;

		public Node(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] minVertex = new int[N+1];
		ArrayList<int[]>[] edjList = new ArrayList[N+1];
		for(int i=1;i<N+1;i++) {
			edjList[i]= new ArrayList<>(); 
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			edjList[from].add(new int[] {to, weight});
			edjList[to].add(new int[] {from, weight});
		}
		
		int start = 1;
		int[] minVertext = new int[N+1];
		Arrays.fill(minVertex, Integer.MAX_VALUE);
		minVertex[1] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(1, 0));
		while(!pq.isEmpty()){
			Node curr = pq.poll();
			for(int i=0;i<edjList[curr.to].size();i++) {
				int to = edjList[curr.to].get(i)[0];
				int weight = edjList[curr.to].get(i)[1];
				if(minVertex[to]>curr.weight+weight) {
					minVertex[to] = curr.weight+weight;
					pq.add(new Node(to, minVertex[to]));
				}
			}
		}
		System.out.println(minVertex[N]);
	}

}
