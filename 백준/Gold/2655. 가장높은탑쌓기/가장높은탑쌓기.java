import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static class Block implements Comparable<Block>{
		int num, area, height, weight;

		public Block(int num, int area, int height, int weight) {
			super();
			this.num = num;
			this.area = area;
			this.height = height;
			this.weight = weight;
		}
		@Override
		public int compareTo(Block o) {
			//정렬
			//면적이 작은 순서대로
			return Integer.compare(o.area,this.area);
		}
		@Override
		public String toString() {
		    return "Block [번호=" + num + ", 면적=" + area + ", 높이=" + height + ", 무게=" + weight + "]";
		}
	
	}
	
	static List<Block> blocks;
	static int N;
	static int maxLength;
	static boolean[] resultSelect;
	static int[] dp;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		dp = new int[N];
		int[] trace = new int[N];
		for(int i=0; i<N; i++) trace[i] = -1;
		
		blocks = new ArrayList<>();
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int num = i+1;
			int area = Integer.parseInt(st.nextToken());
			int height = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			blocks.add(new Block(num, area, height, weight));
		}
		
		blocks.sort(null);
//		System.out.println(blocks);
		for(int i=0;i<N;i++) {
			dp[i]=blocks.get(i).height; 
			for(int j=0;j<i;j++) {
				if(blocks.get(j).weight > blocks.get(i).weight) {
					if(dp[j] + blocks.get(i).height > dp[i]) {
						dp[i]= dp[j] + blocks.get(i).height;  
						trace[i] = j; 
					}
				}
			}
		}
		int maxIdx = 0;
		for(int i=0;i<N;i++) {
			if(dp[maxIdx] < dp[i]) {
				maxIdx = i;
			}
		}
		
		int curr = maxIdx;
		int count = 0;
		
		while(curr != -1) {
			curr = trace[curr];
			count++;
		}
		System.out.println(count);
		curr = maxIdx;
		while(curr != -1) {
			System.out.println(blocks.get(curr).num);
			curr = trace[curr];
		}
	}

}
