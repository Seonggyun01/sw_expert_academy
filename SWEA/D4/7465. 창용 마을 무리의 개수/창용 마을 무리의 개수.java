import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int[] parents;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			parents = new int[N+1];
			for(int i=0;i<N+1;i++) {
				parents[i] = i;
			}
			for(int i=0;i<M;i++) {
				st = new StringTokenizer(br.readLine());
				int left = Integer.parseInt(st.nextToken());
				int right = Integer.parseInt(st.nextToken());
				Union(left, right);
//				System.out.println(Arrays.toString(parents));
			}
			int result = 0;
			for(int i=1;i<=N;i++) {
				if(parents[i] ==i ) {
					result++;
				}
			}
//			System.out.println(Arrays.toString(parents));
			System.out.println("#"+t+" "+result);
		}
	}
	private static int FindSet(int n) {
		if(parents[n]==n) return n;
		else return (parents[n] = FindSet(parents[n]));
	}
	private static boolean Union(int left, int right) {
		boolean flag = false;
		if(FindSet(left) == FindSet(right)) {
			return flag;
		}else {
			parents[FindSet(right)] = FindSet(left);
		}
		return flag;
	}

}
