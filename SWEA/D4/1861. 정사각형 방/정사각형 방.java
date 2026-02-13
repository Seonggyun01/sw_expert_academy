
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, roomNum, max;
	static int[][] map;
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
            roomNum = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			map = new int[N][N];
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					visited = new boolean[N][N];
					moveRoom(i,j,1, map[i][j]);
				}
			}
			System.out.println("#"+t+" "+roomNum+" "+max);
		}
	}
	private static void moveRoom(int r, int c, int cnt, int firstRoomNum) {
		
		visited[r][c] = true;
		if(max<cnt) {
			roomNum = firstRoomNum;
			max = cnt;
		}else if(max==cnt && roomNum>firstRoomNum) {
			roomNum = firstRoomNum;
		}
		for(int d=0;d<4;d++) {
			int nr = r + dy[d];
			int nc = c + dx[d];
			if(nr<0 || nr>=N || nc<0 || nc>=N) {
				continue;
			}
			if(!visited[nr][nc] && map[nr][nc]-map[r][c] == 1) {
				moveRoom(nr,nc,cnt+1, firstRoomNum);
			}
		}
		visited[r][c] = false;
	}

}
