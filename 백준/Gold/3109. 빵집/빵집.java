import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	static char[][] map;
	static int[] dx = {1,1,1};
	static int[] dy = {-1,0,1};
	static boolean isConnect;
	static int R,C, result;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		for(int i=0;i<R;i++) {
			String str = br.readLine();
			for(int j=0;j<C;j++) {
				map[i][j] = str.charAt(j);
			}
		}
		for(int r=0;r<R;r++) {
			isConnect = false;
			dfs(new int[] {r,0});
		}
		System.out.println(result);
		
	}
	private static void dfs(int[] cur) {
		if(isConnect) return;
		if(cur[1] == C-1) {
			isConnect = true;
			result++;
			return;
		}
		map[cur[0]][cur[1]] = 'x';
		for(int d=0;d<3;d++) {
			int nr = cur[0]+dy[d];
			int nc = cur[1]+dx[d];
			if(nr>=0 && nr<R && nc>=0 && nc<C && map[nr][nc]=='.') {
				dfs(new int[] {nr,nc});
			}
		}
		
	}

}
