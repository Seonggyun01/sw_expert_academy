
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution{
	static int N, B, result;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			result = Integer.MAX_VALUE;
			int[] height = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				height[i] = Integer.parseInt(st.nextToken());
			}
			

			subset(0, 0, height);
			System.out.println("#"+t+" "+(result-B));
		}
	}
	
	//부분집합
	static void subset(int cnt, int sum, int[] height) {
		if(cnt == N) {
			
			if(sum>=B && result>sum) {
				result = sum;
			}
			return;
		}
		
		subset(cnt+1, sum+height[cnt], height);
		subset(cnt+1, sum, height);
		
	}

}
