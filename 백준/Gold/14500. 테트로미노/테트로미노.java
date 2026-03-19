import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M,max;
    static int[][] map;
    static int[] dr = {1,0,-1,0};
    static int[] dc = {0,1,0,-1};
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        max = 0;
        visited = new boolean[N][M];
        map = new int[N][M];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                visited[i][j] = true;
                dfs(1,map[i][j],i,j);
                visited[i][j] = false;
            }
        }

        System.out.println(max);
    }

    static void dfs(int depth, int sum, int r, int c){
        if(depth == 4){
            max = Math.max(max,sum);
            return;
        }
        for(int d=0;d<4;d++){
            int nr = r+dr[d];
            int nc = c+dc[d];
            if(isIn(nr,nc) && !visited[nr][nc]){
                if(depth == 2){
                    visited[nr][nc] = true;
                    dfs(depth+1, sum+map[nr][nc], r, c);
                    visited[nr][nc] = false;
                }

                visited[nr][nc] = true;
                dfs(depth+1,sum+map[nr][nc], nr, nc);
                visited[nr][nc] = false;
            }
        }
    }
    static boolean isIn(int r, int c){
        return r>=0 && r<N && c>=0 && c<M;
    }
}
