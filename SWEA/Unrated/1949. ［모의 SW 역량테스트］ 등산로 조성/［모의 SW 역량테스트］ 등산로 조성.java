import java.io.*;
import java.util.*;

public class Solution{
	static int N, K, max;
	static int[][] map;
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	static boolean[][] isVisited;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			max = 0;
			map = new int[N][N];
			isVisited = new boolean[N][N];
			int maxHeight = 0;
			List<int[]> tops = new LinkedList<>();
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j]>maxHeight) {
						maxHeight = map[i][j];
						tops.removeAll(tops);
						tops.add(new int[] {i,j});
					}else if(map[i][j] == maxHeight) {
						tops.add(new int[] {i,j});
					}
				}
			}
			for(int i=0;i<tops.size();i++) {
				int r = tops.get(i)[0];
				int c = tops.get(i)[1];
				isVisited[r][c] = true;
				findLoad(1, r, c, map[r][c], false);
				isVisited[r][c] = false;
			}
			System.out.println("#"+t+" "+max);
		}
	}
	public static void findLoad(int cnt, int r, int c, int currHeight, boolean flag) {
		if(max < cnt) {
			max = cnt;
		}
		//현재 위치에서 4방향 탐색하여 각 방향으로 탐색
		for(int d=0;d<4;d++) {
			int nr = r+dy[d];
			int nc = c+dx[d];
			if(nr>=0 && nr<N && nc>=0 && nc<N) {
				//다음 높이가 현재 나의 높이 보다 작은 경우 깎지 않고 진입 가능
				if(map[nr][nc] < currHeight) {
					isVisited[nr][nc] = true;
					findLoad(cnt+1, nr, nc, map[nr][nc], flag);
					isVisited[nr][nc] = false;
				}
				//다음 높이가 현재 나의 높이 보다 높거나 같은 경우
				else {
					//visited인지 확인하고 && flag가 flase인 경우에 깎고 진입
					if(!isVisited[nr][nc] && !flag) {
						if(map[nr][nc] - currHeight >=K) {
							continue;
						}else {
							int h = map[nr][nc];
							map[nr][nc] = currHeight - 1;
							flag = true;
							findLoad(cnt+1, nr, nc, map[nr][nc], flag);
							map[nr][nc] = h;
							flag = false;
						}
					}
				}
			}
		}
	}
}
