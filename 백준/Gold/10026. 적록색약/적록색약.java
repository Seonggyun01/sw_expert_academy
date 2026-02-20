
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		int N = Integer.parseInt(br.readLine());
		char[][] map1 = new char[N][N];
		char[][] map2 = new char[N][N];
		for(int i=0;i<N;i++) {
			String input = br.readLine();
			for(int j=0;j<N;j++) {
				map1[i][j] = input.charAt(j);
				map2[i][j] = input.charAt(j)=='R'?'G':input.charAt(j);
			}
		}
		
		int result1 = 0;
		int result2 = 0;
		
		int[] dx = {1,0,-1,0};
		int[] dy = {0,1,0,-1};
		boolean[][] visited = new boolean[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(visited[i][j]) continue;
				Queue<int[]> queue = new ArrayDeque<>();
				queue.offer(new int[] {i,j});
				visited[i][j] = true;
				char nowColor = map1[i][j];
				while(!queue.isEmpty()) {
					int[] cur = queue.poll();
					for(int d=0;d<4;d++) {
						int ny = cur[0]+dy[d];
						int nx = cur[1]+dx[d];
						if(nx>=0 && nx<N && ny>=0 && ny<N && map1[ny][nx]==nowColor && !visited[ny][nx]) {
							visited[ny][nx] = true;
							queue.offer(new int[] {ny,nx});
						}
					}
				}
				result1++;
			}
		}
		visited = new boolean[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(visited[i][j]) continue;
				Queue<int[]> queue = new ArrayDeque<>();
				queue.offer(new int[] {i,j});
				visited[i][j] = true;
				char nowColor = map2[i][j];
				while(!queue.isEmpty()) {
					int[] cur = queue.poll();
					for(int d=0;d<4;d++) {
						int ny = cur[0]+dy[d];
						int nx = cur[1]+dx[d];
						if(nx>=0 && nx<N && ny>=0 && ny<N && map2[ny][nx]==nowColor && !visited[ny][nx]) {
							visited[ny][nx] = true;
							queue.offer(new int[] {ny,nx});
						}
					}
				}
				result2++;
			}
		}
		
		System.out.println(result1 + " " + result2);
	}

}
