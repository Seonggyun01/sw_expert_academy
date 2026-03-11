import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
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

		int T = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
//			List<Node>[] adjList = new ArrayList[N];
//			for(int i=0;i<N;i++) {
//				adjList[i] = new ArrayList<Node>();
//			}
			long[] from = new long[N];
			long[] to = new long[N];
			StringTokenizer fromStr = new StringTokenizer(br.readLine());
			StringTokenizer toStr = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				from[i] = Integer.parseInt(fromStr.nextToken());
				to[i] = Integer.parseInt(toStr.nextToken());
			}
			double E = Double.parseDouble(br.readLine());
			
			//각 정점까지 도달하는 가중치의 최솟값 배열 초기화
			long[] minVertex = new long[N];
			Arrays.fill(minVertex,Long.MAX_VALUE);
			boolean[] visited = new boolean[N];
			long result = 0;
			int vCnt = 0;
			
			PriorityQueue<Node> pq = new PriorityQueue<>();
			minVertex[0] = 0;
			pq.offer(new Node(0,0));
			while(!pq.isEmpty()) {
				Node temp = pq.poll();
				int v = temp.to;
				if(visited[v]) continue;
				visited[v] = true;
				result+=temp.weight;
				vCnt++;
				if(vCnt == N) break;
				for(int i=0;i<N;i++) {
					if(i==v || visited[i]) continue;
					long weight = (from[v]-from[i])*(from[v]-from[i]) + (to[v]-to[i])*(to[v]-to[i]);
					if(minVertex[i] > weight) {
						minVertex[i] = weight;
						pq.offer(new Node(i,weight));
					}
				}
			}
			
			result = Math.round(result*E);
			System.out.println("#"+t+" "+result);
			
		}
	}

}
