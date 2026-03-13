import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution{
	static char[][] map;
	static int[] dr = {0,1,0,-1};
	static int[] dc = {1,0,-1,0};
	static List<int[]> devils;
	static int R;
	static int C;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		int T = Integer.parseInt(st.nextToken());
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new char[R][C];
			
			int[] S = new int[2];
			int[] D = new int[2];
			devils = new ArrayList<>();
			for(int i=0;i<R;i++) {
				String str = br.readLine();
				for(int j=0;j<C;j++) {
					map[i][j] = str.charAt(j);
					if(map[i][j] == 'S') {
						S[0] = i;
						S[1] = j;
					}else if(map[i][j] == 'D') {
						D[0] = i;
						D[1] = j;
					}else if(map[i][j] == '*') {
						devils.add(new int[] {i,j});
					}
				}
			}
			Queue<int[]> queue = new ArrayDeque<>();
			queue.offer(S);
			int result = 0;
			int count = 0;
			boolean flag = false;
			while(!queue.isEmpty()) {
				int size = queue.size();
				count++;
				devilsHands();
				for(int i=0;i<size;i++) {
					int[] cur = queue.poll();
					for(int d=0;d<4;d++) {
						int nr = cur[0]+dr[d];
						int nc = cur[1]+dc[d];
						if(nr<0 || nr>=R || nc<0 || nc>=C || map[nr][nc] =='X' || map[nr][nc] =='*' || map[nr][nc] =='S') continue;
						if(map[nr][nc] == 'D') {
							flag = true;
							break;
						}
						map[nr][nc] = 'S';
						queue.offer(new int[] {nr,nc});
					}
					if(flag) {
						break;
					}
				}
				if(flag) {
					result = count;
					break;
				}
			}
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ");
			if(result == 0) {
				sb.append("GAME OVER");
			}else {
				sb.append(result);
			}
			System.out.println(sb);
			
		}
	}
	
	private static void devilsHands() {
		List<int[]> temp = new ArrayList<>();
		for(int[] dv : devils) {
			for(int d=0;d<4;d++) {
				int nr = dv[0] + dr[d];
				int nc = dv[1] + dc[d];
				if(nr<0 || nr>=R || nc<0 || nc>=C || map[nr][nc]=='D' || map[nr][nc] == '*' || map[nr][nc]=='X') continue;
				map[nr][nc] = '*';
				temp.add(new int[] {nr,nc});
			}
		}
		devils.addAll(temp);
		Set<int[]> set = new HashSet<>(devils);
		devils = new ArrayList<>(set);
	}
}
