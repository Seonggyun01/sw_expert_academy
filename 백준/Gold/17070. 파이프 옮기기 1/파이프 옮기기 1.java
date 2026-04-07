import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
	static int N;
	static int[][] map;
	static int[][] memo;
	static int[] dr = {0,1,1};
	static int[] dc = {1,1,0}; 

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); 
			}
		}
		
		//시작 부터 하향식으로 찾아가는건, BFS로 탐색 가능. 
		//메모이제이션을 어떻게 적용하는게 좋을까?
		memo = new int[N][N];
		dfs(0, 1, 0);
		System.out.println(memo[N-1][N-1]);
		
		
	}
	static void dfs(int r, int c, int state) {
		if(r==N-1 && c==N-1) {
			memo[N-1][N-1] ++;
			return;
		}
		
		if(state == 0 || state == 2) { //가로 이동
			if(isIn(r, c+1) && map[r][c+1]==0) {
				dfs(r, c+1, 0);
			}
		}
		if(state == 1 || state == 2) { //세로 이동
			if(isIn(r+1, c) && map[r+1][c]==0) {
				dfs(r+1, c, 1);
			}
		}
		if(isIn(r+1, c+1) && map[r+1][c]==0 && map[r][c+1]==0 && map[r+1][c+1]==0){ //대각 이동
			dfs(r+1, c+1, 2);
		}
	}
	
	static boolean isIn(int r, int c) {
		return (r>=0 && r<N && c>=0 && c<N);
	}

}
