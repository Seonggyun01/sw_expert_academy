import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main{
	static int[][] map;
	static boolean[][] visited;
	static int N, M, islendCount;
	static int[] dr = {1,0,-1,0};
	static int[] dc = {0,1,0,-1};
	static List<List<int[]>> islend;
	static List<Edge> edges;
	static int[] parents;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//각 섬에서 다른 섬까지 연결할 수 있는 다리가 있는지 확인하고, 최소 길이가 2이상인 다리만 간선으로 저장.
       
		//1.섬의 개수 및 섬의 좌표 찾기
		islend = new ArrayList<>();
		islendCount = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(visited[i][j]) continue;
				findIsland(i,j, 0);
				
			}
		}
		
//		for(List<int[]> islend1:islend) {
//			for(int[] is: islend1) {
//				System.out.print(Arrays.toString(is));
//			}
//			System.out.println();
//		}
		
		//2.각 섬 사이의 간선 구하기
		edges = new ArrayList<>();
		makeEdge();
		
		//3.각 간선을 정렬 후 크루스칼 수행
		makeSet();
		edges.sort(null);
		int connectedVertex = 0;
		int sumOfweight = 0;
	
		for(Edge edge : edges){
			if(union(edge.start, edge.end)){
				connectedVertex++;
				sumOfweight += edge.weight;
				if(connectedVertex == islendCount-1) break;
			}
		}

		if(connectedVertex < islendCount-1) {
			System.out.println(-1);
			return;
		}
		System.out.println(sumOfweight);
		
				
	}
	private static boolean union(int a, int b){
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}
	private static int findSet(int a){
		if(parents[a] == a) return a;
		return parents[a] = findSet(parents[a]);
	}

	private static void makeSet() {
		parents = new int[islendCount];
		for(int i=0;i<islendCount;i++) {
			parents[i] = i;
		}
	}

	private static void makeEdge(){
		for(int i=0;i<islendCount;i++){
			for(int j=i+1;j<islendCount;j++){
				List<int[]> v1 = islend.get(i);
				List<int[]> v2 = islend.get(j);
				for(int[] islend1:v1){
					for(int[] islend2:v2){
						if(islend1[0] == islend2[0]) {
							int dist = Math.abs(islend1[1] - islend2[1]) -1;
							boolean isConnected = true;
							for(int c = Math.min(islend1[1], islend2[1])+1; c<Math.max(islend1[1], islend2[1]); c++) {
								if(map[islend1[0]][c] == 1) {
									isConnected = false;
									break;
								}
							}
							if(dist >= 2 && isConnected) {
								edges.add(new Edge(i,j,dist));
							}
						}else if(islend1[1] == islend2[1]) {
							int dist = Math.abs(islend1[0] - islend2[0]) - 1;
							boolean isConnected = true;
							for(int r = Math.min(islend1[0], islend2[0])+1; r<Math.max(islend1[0], islend2[0]); r++) {
								if(map[r][islend1[1]] == 1) {
									isConnected = false;
									break;
								}
							}
							if(dist >= 2 && isConnected) {
								edges.add(new Edge(i,j,dist));
							}
						}
					}
				}
			}
		}
	}

	static class Edge implements Comparable<Edge>{
		int start, end, weight;
		public Edge(int start, int end, int weight){
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	private static void findIsland(int r, int c, int depth) {
		visited[r][c] = true;
		if(map[r][c] == 0) return;
		if(depth == 0) {
			islend.add(new ArrayList<>());
			islend.get(islendCount++).add(new int[] {r,c});
		}
		for(int d=0;d<4;d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(nr>=0 && nr<N && nc>=0 && nc<M && !visited[nr][nc] && map[nr][nc]==1) {
				islend.get(islendCount-1).add(new int[] {nr,nc});
				findIsland(nr,nc,depth+1);
			}
		}
	}

}
