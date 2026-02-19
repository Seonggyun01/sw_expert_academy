
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	static int N, max;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			//치즈 입력 받기
			map = new int[N][N];
			max = 0;
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			//for(0~N) 날짜 변화
			for(int d=0;d<=100;d++) {
				int sum = 0;
				visited = new boolean[N][N];
				for(int i=0;i<N;i++) {
					for(int j=0;j<N;j++) {
						if(!visited[i][j]&&map[i][j]>d) {
							visited[i][j] = true;
							dfs(i,j,d);
							sum++;
						}
						
					}
				}
				max = Math.max(sum, max);
			}
			
			System.out.println("#"+t+" "+max);
			
		}
		
	}
	static void dfs(int r, int c, int day) {
		
		for(int d=0;d<4;d++) {
			int nr = r+dy[d];
			int nc = c+dx[d];
			if(isIn(nr,nc) && !visited[nr][nc] && map[nr][nc]>day) {
				visited[nr][nc] = true;
				dfs(nr,nc, day);
			}
		}
	}
	private static boolean isIn(int nr, int nc) {
		return (nr>=0 && nr<N && nc>=0 && nc<N);
	}

}
