import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Edge implements Comparable<Edge> {
        int to, weight;
        public Edge( int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        List<Edge>[] edjList = new ArrayList[V + 1];
        for (int i = 0; i <= V; i++) {
            edjList[i] = new ArrayList<Edge>();
        }

        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edjList[from].add(new Edge(to,weight));
            edjList[to].add(new Edge(from,weight));
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[V+1];
        int[] minVertex = new int[V+1];
        int start = 1;
        int count = 0;
        int totalWeight = 0;
        pq.offer(new Edge(1,0));

        while(!pq.isEmpty()){
            Edge edge = pq.poll();
            if(visited[edge.to]) continue;
            visited[edge.to] = true;
            totalWeight += edge.weight;
            count++;
            if(count == V) break;
            for(Edge next : edjList[edge.to]){
                if(visited[next.to]) continue;
                pq.offer(new Edge(next.to,next.weight));
            }
        }

        System.out.println(totalWeight);
    }
}
