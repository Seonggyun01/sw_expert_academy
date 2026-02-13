
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution{
	static int N;
	static int[] memo = new int[100000];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1;t<=T;t++) {
			N = Integer.parseInt(br.readLine());
			play(N);
			System.out.println("#"+t+" "+memo[N]);
		}
	}
	private static int play(int n) {
		//숫자가 n자릿수이면 1~n의 부분집합을 구하여 각각 잘라서 곱을 구하고 이전에 구한 turn이 있는지 확인하고 없다면 재귀, 있으면 현재 턴수에 memo에 있는 턴을 더하고 memo[초기숫자]를 업데이트 해준다.
		//자릿수 구하기
		
		int size = 0;
		for(int i=10000,s=4;i>0;i/=10) {
			if(n/i != 0) {
				size = s;
				break;
			}
			--s;
		}
		if(size<1) {
			memo[n] = 0;
			return memo[n];
		}
		if(memo[n] > 0) {
			return memo[n];
		}
		//부분집합 구하기(비트 마스킹)
		for(int i=1;i<(int)Math.pow(2,size);i++) {
			int nextNum = 1;
			int currNum = n;
			//다음 숫자 구하기
			for(int j=size-1;j>=0;j--) {
				if((i&(1<<j)) != 0) {
					nextNum*=currNum/(int)Math.pow(10, j+1);
					currNum%=Math.pow(10,j+1);
				}
			}
			nextNum*=currNum;
			
			int child = play(nextNum);
			memo[n] = Math.max(memo[n], child+1);
			
		}
		return memo[n];
	}
}
