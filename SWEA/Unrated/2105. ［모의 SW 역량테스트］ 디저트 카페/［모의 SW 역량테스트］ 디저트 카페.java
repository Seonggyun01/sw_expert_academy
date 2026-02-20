
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	static int[][] map;
	static int N;
	static int[] dx = {1, -1, -1, 1};
	static int[] dy = {1, 1, -1, -1};
	static int max;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			max = 0;
			for(int i=0;i<N;i++){
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int curd = 0;
			for(int i=0;i<N-2;i++) {
				for(int j=1;j<N-1;j++) {
					List<Integer> nums = new ArrayList<>();
					int[] moveCnt = new int[4];
					
					dfs(i, j, curd, nums, moveCnt);
				}
			}
			if(max==0) {
				max = -1;
			}
			System.out.println("#"+t+" "+max);
		}
	}

	private static void dfs(int r, int c, int curd, List<Integer> nums, int[] moveCnt) {
		if(curd==3) {
			if(moveCnt[0] != moveCnt[2]) {
				return;
			}
			if(moveCnt[1]>moveCnt[3]) {
				//같은 방향으로 진행
				int nr = r+dy[curd];
				int nc = c+dx[curd];
				if(nr>=0 && nr<N && nc>=0 && nc<N && !nums.contains(map[nr][nc])) {
					nums.add(map[nr][nc]);
					moveCnt[curd]++;
					dfs(nr, nc, curd, nums, moveCnt);
					nums.remove(nums.size()-1);
					moveCnt[curd]--;
				}
			}
			else {
				Set<Integer> set = new HashSet<>(nums);
				if(set.size() == nums.size()) {
					max = Math.max(nums.size(), max);
				}
				
			}
		}
		if(curd<3) {
			
			//같은 방향으로 진행
			int nr = r+dy[curd];
			int nc = c+dx[curd];
			if(nr>=0 && nr<N && nc>=0 && nc<N && !nums.contains(map[nr][nc])) {
				moveCnt[curd]++;
				nums.add(map[nr][nc]);
				dfs(nr, nc, curd, nums, moveCnt);
				nums.remove(nums.size()-1);
				moveCnt[curd]--;
			}
			//오른쪽 길로 진행
			++curd;
			nr = r+dy[curd];
			nc = c+dx[curd];
			if(nr>=0 && nr<N && nc>=0 && nc<N && !nums.contains(map[nr][nc])) {
				
				moveCnt[curd]++;
				nums.add(map[nr][nc]);
				dfs(nr, nc, curd, nums, moveCnt);
				nums.remove(nums.size()-1);
				moveCnt[curd]--;
			}
			
		}
		
		
	}

}
