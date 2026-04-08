import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution{
	static int D,W,K;
	static char[][] grid;
	static int min;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			grid = new char[D][W];
			min = Integer.MAX_VALUE;
			
			for(int i=0;i<D;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<W;j++) {
					grid[i][j] = st.nextToken().charAt(0); 
				}
			}
			
			dfs(0, 0);
			System.out.println("#"+t+" "+min);
		}
	}
	static void dfs(int cnt, int changeCnt) {
		if(cnt == D) {
			boolean isPass = test();
			if(isPass) {
				if(min>changeCnt) {
					min = changeCnt;
				}
			}
			return;
		}
		if(changeCnt>=min) {
			return;
		}
		//안바꾸고 다음으로 넘어가는 경우
		dfs(cnt+1, changeCnt);
		//A약물 주입하고 넘어가는 경우
		char[] temp = grid[cnt].clone();
		lineChange(cnt, '0');
		dfs(cnt+1, changeCnt+1);
		grid[cnt]= temp.clone(); 
		//B약물 주입하고 넘어가는 경우
		temp = grid[cnt].clone();
		lineChange(cnt, '1');
		dfs(cnt+1, changeCnt+1);
		grid[cnt]= temp.clone(); 
	}
	
	private static void lineChange(int cnt, char c) {
		Arrays.fill(grid[cnt], c);
	}
	static boolean test() {
		boolean pass = true;
		for(int i=0;i<W;i++) {
			boolean smallPass = false;
			int count = 1;
			char c = grid[0][i];
			for(int j=1;j<D;j++) {
				if(c==grid[j][i]) {
					count++;
				}else if(c!=grid[j][i]) {
					count = 1;
					c = grid[j][i];
				}
				
				if(count==K) {
					smallPass = true;
					break;
				}
			}
			if(!smallPass) {
				pass = false;
				break;
			}
		}
		return pass;
	}

}
