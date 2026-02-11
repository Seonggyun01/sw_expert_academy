
import java.io.IOException;
import java.util.Scanner;

public class Solution {
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int t=1;t<=T;t++) {
			int N = sc.nextInt();
			
			int[] mountains = new int[N];
			for(int i=0;i<N;i++) {
				mountains[i] = sc.nextInt();
			}
			int result = 0;
			
			boolean up = mountains[0]<mountains[1];
			int minPoint1 = 0;
			int maxPoint = 0;
			int minPoint2 = 0;

			for(int i=1;i<N;i++) {
				if(up && mountains[i-1]<mountains[i]) {
					++maxPoint;
					++minPoint2;
					
				}else if(up && mountains[i-1]>mountains[i]) {
					up = false;
					++minPoint2;
					if(i==N-1) {
						int u = maxPoint-minPoint1;
						int d = minPoint2-maxPoint;
						result+=u*d;
					}
				}else if(!up && mountains[i-1]>mountains[i]) {
					++minPoint2;
					if(i==N-1) {
						int u = maxPoint-minPoint1;
						int d = minPoint2-maxPoint;
						result+=u*d;
					}
				}else if(!up && mountains[i-1]<mountains[i]) {
					up = true;
					int u = maxPoint-minPoint1;
					int d = minPoint2-maxPoint;
					result+=u*d;
					minPoint1 = minPoint2;
					maxPoint = ++minPoint2;
				}
			}
			System.out.println("#"+t+" "+result);
		}
	}

}
