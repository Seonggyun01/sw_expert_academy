import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int[] liquids = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			liquids[i]= Integer.parseInt(st.nextToken()); 
		}
		
		int min = Integer.MAX_VALUE;
		int[] result = new int[2];
		
		int left = 0;
		int right = N-1;
		
		while(left!=right) {
			int liquid = liquids[left] + liquids[right];
			if(min > Math.abs(liquid)) {
				min = Math.abs(liquid);
				result[0] = left;
				result[1] = right;
			}
			if(liquid>0) {
				right--;
			}else {
				left++;
			}
		}
		System.out.println(liquids[result[0]]+" "+liquids[result[1]]);
		
	}
}
