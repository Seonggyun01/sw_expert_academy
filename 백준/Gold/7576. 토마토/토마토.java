import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		int[][] day = new int[N][M];
		Deque<int[]> queue = new ArrayDeque<>();
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					queue.addLast(new int[] {i,j});
					day[i][j] = 1;
				}
				else if(map[i][j] == -1) {
					day[i][j] = -1;
				}
			}
		}
		
		while(!queue.isEmpty()) {
			int[] cur = queue.pollFirst();
			for(int d=0;d<4;d++) {
				int nr = cur[0]+dy[d];
				int nc = cur[1]+dx[d];
				if(nr<0||nr>=N||nc<0||nc>=M||day[nr][nc]>=1||map[nr][nc] == -1) continue;
				queue.add(new int[] {nr,nc});
				day[nr][nc] = day[cur[0]][cur[1]]+1;
			}
		}
		int max = 0;
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(day[i][j] == 0) {
					max = -1;
					System.out.println(max);
					return;
				}
				max = Math.max(max, day[i][j]);
			}
		}
		System.out.println(max-1);

	}

}
