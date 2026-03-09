import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	static int[] parents;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		parents = new int[N];
		for(int i=0;i<N;i++) {
			parents[i] = i;
		}
		int result = 0;
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(union(a,b)) {
				result = i+1;
				break;
			}
		}
		System.out.println(result);

	}
	private static int FindSet(int n) {
		if(parents[n] == n) return n;
		else return parents[n] = FindSet(parents[n]);
	}
	private static boolean union(int a, int b) {
		if(FindSet(a) == FindSet(b)) {
			return true;
		}else{
			parents[FindSet(b)] = parents[FindSet(a)];
			return false;
		}
	}

}
