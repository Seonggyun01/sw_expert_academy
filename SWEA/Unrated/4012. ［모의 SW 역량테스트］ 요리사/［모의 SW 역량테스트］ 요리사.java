
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int aSum, bSum, result;
	static int[][] arr;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			arr = new int[N][N];
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int half = N/2;
			boolean[] selected = new boolean[N];
			
			result = Integer.MAX_VALUE;
			combination(0,0,half,selected);
			//N/2개 조합을 뽑고, 거기서 2개 부분집합 List를 구하고, List의 시너지 합을 구하여 차이의 절댓값이 작으면 업데이트
			
			System.out.println("#"+t+" "+result);
			
		}
	}
	static void combination(int cnt, int start ,int cntMax, boolean[] selected) {
		if(cnt == cntMax) {
			aSum = bSum =0;
			for(int i=0;i<selected.length;i++) {
				for(int j=0;j<selected.length;j++) {
					if(i==j)continue;
					if(selected[i] && selected[j]) {
						aSum+=arr[i][j];
					}else if(!selected[i] && !selected[j]) {
						bSum+=arr[i][j];
					}
				}
			}
			if(result>Math.abs(aSum-bSum)) {
				result = Math.abs(aSum-bSum);
			}
			
			return;
		}
		
		for(int i=start;i<selected.length;i++) {
			//selected[cnt]를 선택한 경우
			selected[i] = true;
			combination(cnt+1, i+1 ,cntMax, selected);
			selected[i] = false;
		}
	}

}
