import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] map = new int[101];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            map[from] = to;
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            map[from]=to;
        }

        int[] dist = new int[101];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(1);
        while(!q.isEmpty()){
            int curr = q.poll();
            if(curr==100) break;
            for(int d=6;d>0;d--){
                int next = curr+d;
                if(next>100) continue;
                if(map[next]!=0){
                    next = map[next];
                }
                if(dist[next] > dist[curr]+1){
                    dist[next] = dist[curr]+1;
                    q.offer(next);
                }
            }
        }
        System.out.println(dist[100]);
    }
}
