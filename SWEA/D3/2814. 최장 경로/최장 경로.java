import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static int[] dist;
    static int N, M;
    static int result;
    static boolean[][] edge;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for(int t=1;t<=T;t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); //정점의 개수
            M = Integer.parseInt(st.nextToken()); //간선의 개수
            result = 0;

            edge = new boolean[N+1][N+1];
            Arrays.fill(edge[0],true);
            for(int i=0;i<M;i++){
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                edge[from][to] = true;
                edge[to][from] = true;
            }
            boolean[] selected = new boolean[N+1];

            dfs(0,selected, 0);

            System.out.println("#"+t+" "+result);

        }
    }
    static void dfs(int cnt, boolean[] selected, int curr){
        result = Math.max(cnt, result);

        for(int i=1;i<=N;i++){
            if(edge[curr][i] && !selected[i]){
                selected[i] = true;
                dfs(cnt+1, selected, i);
                selected[i] = false;
            }
        }
    }
}
