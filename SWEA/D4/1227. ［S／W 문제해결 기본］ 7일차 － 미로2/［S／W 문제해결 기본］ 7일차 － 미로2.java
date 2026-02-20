import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] dx = {0,1,0,-1};
		int[] dy = {1,0,-1,0};
		
		for(int t=1;t<=10;t++) {
			int T = Integer.parseInt(br.readLine());
			int[][] map = new int[100][100];
			boolean[][] visited = new boolean[100][100];
			int[] start = new int[2];

			for(int i=0;i<100;i++) {
				String input = br.readLine();
				for(int j=0;j<100;j++) {
					map[i][j] = input.charAt(j)-'0';
					if(map[i][j] == 2) {
						start[0] = i;
						start[1] = j;
					}
				}
			}
			
			Queue<int[]> queue = new ArrayDeque<>();
			queue.offer(start);
			visited[start[0]][start[1]] = true;
			int result = 0;
			while(!queue.isEmpty()) {
				int[] cur = queue.poll();
				if(map[cur[0]][cur[1]] == 3) {
					result = 1;
					break;
				}
				for(int d=0;d<4;d++) {
					int ny = cur[0] + dy[d];
					int nx = cur[1] + dx[d];
					if(isIn(nx,ny) && map[ny][nx]!=1 && !visited[ny][nx]) {
						queue.offer(new int[] {ny, nx});
						visited[ny][nx] = true;
					}
				}
			}
			System.out.println("#"+t+" "+result);
		}

	}

	private static boolean isIn(int nx, int ny) {
		return (nx>=0 && nx<100 && ny>=0 && ny<100);
	}

}
