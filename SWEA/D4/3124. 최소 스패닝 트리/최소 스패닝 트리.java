import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution{
	static class Node implements Comparable<Node>{
		int vertex, weight;
		Node next;
		public Node(int vertex, int weight, Node next) {
			super();
			this.vertex = vertex;
			this.weight = weight;
			this.next = next;
		}
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			boolean[] visited = new boolean[V+1];  //트리 정점들
			int[] minVertex = new int[V+1];  //트리에서 정점까지 도달하는 가중치

			Node[] adjList = new Node[V+1];
			for (int i = 1; i <= E; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				adjList[from] = new Node(to, weight, adjList[from]); 
				adjList[to] = new Node(from, weight, adjList[to]); //무방향이라 양쪽으로 연결해주기
			}
			//minVertex Integer.MAX_VALUE로 초기화
			Arrays.fill(minVertex, Integer.MAX_VALUE);
			int start = 1;
			minVertex[1] = 0;
			long result = 0;
			int vertexCount = 0;
			
			//비트리 정점에서 가중치가 가장 작은 정점을 선택하고 트리정점으로 넣는다.
			//임의의 시작 정점에 연결된 노드를 pq에 넣어준다.
			PriorityQueue<Node> pq = new PriorityQueue<>();
			pq.offer(new Node(start, 0, null));
			
			while(!pq.isEmpty()) {
				Node v = pq.poll();
				
				if(visited[v.vertex]) continue;
				
				visited[v.vertex] = true;
				result+=v.weight;
				vertexCount++;
				if(vertexCount == V) break;
				
				for(Node node = adjList[v.vertex] ; node != null ; node = node.next) {
					if(!visited[node.vertex]) {
						pq.offer(node);
					}
				}
			}
			
			System.out.println("#"+t+" "+result);
			
		}
	}
}
