import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution{
	static int N, M;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());
		
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			List<int[]> house = new ArrayList<>();
			int[][] map = new int[N][N];
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j]>0) {
						house.add(new int[] {i,j});
					}
				}
			}
			int result = 0;
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					//map[i,j] 마름모의 중심점 위치
					for(int k=1;k<N*2;k++) {
						//k는 마름모의 중심부터 거리
						int count = 0;
						int kCost = k*k+(k-1)*(k-1);
						for(int h=0;h<house.size();h++) {
							int[] p = house.get(h);
							if(Math.abs(i-p[0]) + Math.abs(j-p[1])<k) {
								count++;
							}
						}
						if((count*M)-kCost>=0 && result < count) {
							result = count;
						}
					}
				}
			}
			System.out.println("#"+t+" "+result);
		}
	}

}
