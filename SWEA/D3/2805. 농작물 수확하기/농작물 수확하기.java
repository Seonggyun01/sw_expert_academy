import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution{

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1;t<=T;t++) {

			int N = Integer.parseInt(br.readLine());
			
			int[][] map = new int[N][N];
			int sum = 0;
			int middle=N/2;
			
			for(int i=0;i<N;i++) {
				String str = br.readLine();
				for(int j=0;j<N;j++) {
					map[i][j] = str.charAt(j)-'0';
					if(Math.abs(middle-i)<= middle-Math.abs(middle-j)  ) {
						sum+=map[i][j];
					}	
				}
			}
			System.out.println("#"+t+" "+sum);
		}
	}
}


