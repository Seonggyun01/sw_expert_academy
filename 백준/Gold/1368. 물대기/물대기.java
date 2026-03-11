import java.io.BufferedReader;
import java.io.InputStreamReader;
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
		int[] direct = new int[N];
		for(int i=0;i<N;i++) {
			direct[i] = Integer.parseInt(br.readLine());
		}
		int[][] adjMatrix = new int[N+1][N+1];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				adjMatrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i=0;i<N;i++) {
			adjMatrix[i][N] = direct[i];
			adjMatrix[N][i] = direct[i];
		}
		
		//정점 N을 추가해서 해볼까?
//		//인접행렬이 잘 들어갔는지 출력 확인
//		for(int i=0;i<N+1;i++) {
//			System.out.println(Arrays.toString(adjMatrix[i]));
//		}
		//PRIM 알고리즘을 위한 초기화
		int[] minVertex = new int[N+1];
		Arrays.fill(minVertex, Integer.MAX_VALUE);
		minVertex[0] = 0;  //임의의 초기 정점 초기화;
		boolean[] visited = new boolean[N+1];  //선택된 정점 체크
		long result = 0;
		int vCnt = 0;
		
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(0,0));
		while(!pq.isEmpty()) {
			Node temp = pq.poll();
			int v = temp.to;
			if(visited[v]) continue;
			visited[v] = true;
			result += temp.weight;
			vCnt++;
			if(vCnt == N+1) break;
			
			for(int to = 0; to<N+1;to++) {
				if(to==v || visited[to]) continue;
				if(minVertex[to] > adjMatrix[v][to]) {
					minVertex[to] = adjMatrix[v][to];
					pq.offer(new Node(to,adjMatrix[v][to]));
				}
				
			}
		}
		System.out.println(result);
	}

}
