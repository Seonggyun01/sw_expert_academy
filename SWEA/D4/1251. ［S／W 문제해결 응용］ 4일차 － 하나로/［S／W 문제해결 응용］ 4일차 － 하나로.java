import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int[] parents;
	static long result;
	static BigDecimal E;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			long[] xList = new long[V];
			long[] yList = new long[V];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<V;i++) {
				xList[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<V;i++) {
				yList[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			E = new BigDecimal(st.nextToken());
			
			Edge[] edgeList = new Edge[V*(V-1)/2];
			int index = 0;
			for(int i=0;i<V;i++) {
				for(int j=i+1;j<V;j++) {
					int start = i;
					int end = j;
					long x = (xList[i]-xList[j])*(xList[i]-xList[j]);
					long y = (yList[i]-yList[j])*(yList[i]-yList[j]);
					long weight = x+y;
					
					edgeList[index++] = new Edge(start, end, weight);
				}
			}
			Arrays.sort(edgeList);
			
			result = 0;
			parents = new int[V];
			for(int i=0;i<V;i++) {
				parents[i] = i;
			}
			
			
			int eCnt = 0;
			for(Edge edge : edgeList) {
				int start = edge.start;
				int end = edge.end;
				if(union(start,end)) {
					eCnt++;
					result = result+edge.weight;
					if(eCnt==V-1) break;
				}
			}
			BigDecimal finalResult = new BigDecimal(result).multiply(E).setScale(0,RoundingMode.HALF_UP);
			
			System.out.println("#"+t+" "+finalResult);
			
		}
	}
	private static boolean union(int a, int b) {
		int rootA = findSet(a);
		int rootB = findSet(b);
		if(rootA == rootB) return false;
		parents[rootB] = rootA;
		return true;
	}
	private static int findSet(int a) {
		if(parents[a] == a) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	static class Edge implements Comparable<Edge>{
		int start, end;
		long weight;
		public Edge(int start, int end, long weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.weight,o.weight);
		}
		
	}

}
