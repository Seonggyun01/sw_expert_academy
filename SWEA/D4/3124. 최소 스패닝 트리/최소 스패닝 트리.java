
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static int[] parents;
	static long result;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			Edge[] edgeList = new Edge[E];
			for(int i=0;i<E;i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				edgeList[i] = new Edge(start, end, weight);
			}
			
			Arrays.sort(edgeList);
			
			parents = new int[V+1];
			for(int i=0;i<=V;i++) {
				parents[i] = i;
			}
			
			int eCnt = 0;
            result = 0;
			for(Edge edge: edgeList) {
				if(union(edge.start, edge.end)) {
					eCnt++;
					result+=edge.weight;
					if(eCnt == V-1) break;
				}
			}
			System.out.println("#"+t+" "+result);
		}

	}
	private static boolean union(int a, int b) {
		if(findSet(a) == findSet(b)) return false;
		else {
			parents[findSet(b)] = findSet(a);
			return true;
		}
	}
	
	private static int findSet(int a) {
		if(parents[a] == a) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	static class Edge implements Comparable<Edge>{
		int start, end, weight;
		public Edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

}
