
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");
		
		for(int t=1;t<=10;t++) {
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			List<List<Integer>> adjList = new ArrayList<>();
			for(int i=0;i<=V;i++) adjList.add(new ArrayList<Integer>());
			
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<E;i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				adjList.get(to).add(from);
			}
//			System.out.println(adjList);
			
			boolean[] finished = new boolean[V+1];
			Queue<Integer> queue = new ArrayDeque<>();
			for(int i=1;i<=V;i++) {
				if(adjList.get(i).size() == 0) {
					queue.offer(i);
					finished[i] = true;
				}
			}
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t);
			
			while(!queue.isEmpty()) {
				int cur = queue.poll();
				sb.append(" "+cur);
				for(int i=1;i<=V;i++) {
					if(adjList.get(i).isEmpty()) continue;
					else if(adjList.get(i).contains(cur)) {
						adjList.get(i).remove(adjList.get(i).indexOf(cur));
					}
				}
				for(int i=1;i<=V;i++) {
					if(adjList.get(i).size()==0 && !finished[i]) {
						queue.offer(i);
						finished[i] = true;
					}
				}
			}
			
			System.out.println(sb);
		}

	}

}
