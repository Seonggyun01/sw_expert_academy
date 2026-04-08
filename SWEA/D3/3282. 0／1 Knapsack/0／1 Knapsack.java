import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution{

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			int[][] goods = new int[N+1][2];
			for(int i=1;i<N+1;i++) {
				st = new StringTokenizer(br.readLine());
				int v = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				goods[i][0] = v;
				goods[i][1] = c;
			}
			
			int[][] dp = new int[N+1][K+1];
			
			for(int i=1;i<N+1;i++) {  //현재 물건 번호
				for(int j=1;j<K+1;j++) {  //현재 가방 부피 
					if(goods[i][0] > j) {  //물건의 부피가 가방 부피보다 큰 경우 
						dp[i][j]= dp[i-1][j]; 
					}else { //물건의 부피가 가방 부피보다 작은 경우 -> max(현재 물건을 넣는 경우, 물건을 안 넣는 경우)
						dp[i][j]= Math.max(dp[i-1][j-goods[i][0]]+goods[i][1], dp[i-1][j]); 
					}
				}
			}
			System.out.println("#"+t+" "+dp[N][K]);
		}
		
	}

}
