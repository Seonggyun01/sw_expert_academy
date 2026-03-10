
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int result, cx, cy, hx, hy, N;
	static int[][] consumer;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		
		//회사, 집, 각 고객 위치 는 2차원 정수(x,y)
		//두 위치 거리 계산 |x1-x2|+|y1-y2|
		//회사에서 출발해서 각 고객을 모두 방문하고 집으로 돌아오는 경로 중 최단거리
		
		//회사에서 처음 고객은 누굴 선택할까?
		
		
		//각 정점간의 간선의 가중치는 미리 구할까? 필요할 때 연산할까?
		
				
		int T = Integer.parseInt(st.nextToken());
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			cx = Integer.parseInt(st.nextToken());  //회사 x좌표
			cy = Integer.parseInt(st.nextToken());  //회사 y좌표
			hx = Integer.parseInt(st.nextToken());  //집 x좌표
			hy = Integer.parseInt(st.nextToken());  //집 y좌표
			consumer = new int[N][2];       //고객 x,y 좌표
			for(int i=0;i<N;i++) {
				consumer[i][0] = Integer.parseInt(st.nextToken());
				consumer[i][1] = Integer.parseInt(st.nextToken());
			}
			result = Integer.MAX_VALUE;
			boolean[] visited = new boolean[N];
			for(int i=0;i<N;i++) {
				int cost = distance(cx, cy, consumer[i][0], consumer[i][1]);
				visited[i] = true;
				dfs(1,cost,visited,i);
				visited[i] = false;
			}
			
			System.out.println("#"+t+" "+result);
		}
	}
	
	private static void dfs(int vCnt, int cost, boolean[] visited, int cur) {
		if(vCnt == N) {
			int nCost = distance(consumer[cur][0], consumer[cur][1], hx, hy);
			result = Math.min(result, cost+nCost);
			return;
		}
		for(int i=0;i<N;i++) {
			if(visited[i]) continue;
			int nCost = distance(consumer[cur][0], consumer[cur][1],consumer[i][0], consumer[i][1]);
			if(cost+nCost>result) continue;
			visited[i] = true;
			dfs(vCnt+1, cost+nCost, visited, i);
			visited[i] = false;
		}
	}
	
	
	private static int distance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1-x2)+Math.abs(y1-y2);
	}

}
