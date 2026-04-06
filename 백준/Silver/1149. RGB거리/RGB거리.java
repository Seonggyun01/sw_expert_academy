import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int[][] RGBcost = new int[N][3];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			
			RGBcost[i][0] = Integer.parseInt(st.nextToken());
			RGBcost[i][1] = Integer.parseInt(st.nextToken());
			RGBcost[i][2] = Integer.parseInt(st.nextToken());
		}
		
		//초기 1개의 집을 선택하고 최소의 값으로 선택한다.
		int[][] memo = new int[N][3];
		memo[0][0]=RGBcost[0][0];
		memo[0][1]=RGBcost[0][1];
		memo[0][2]=RGBcost[0][2];
		
		for(int i=1;i<N;i++) {
			for(int j=0;j<3;j++) {
				memo[i][j]= Math.min(memo[i-1][(j+1)%3]+RGBcost[i][j], memo[i-1][(j+2)%3]+RGBcost[i][j]); 
			}
		}
		int result = memo[N-1][0];
		result = Math.min(result,  memo[N-1][1]);
		result = Math.min(result,  memo[N-1][2]);
		System.out.println(result);
	}

}
