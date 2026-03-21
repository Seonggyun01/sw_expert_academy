import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    static int[][] map;
    static int N, M, result;
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int sR = Integer.parseInt(st.nextToken());
        int sC = Integer.parseInt(st.nextToken());
        int sD = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        dfs(sR, sC, sD);
        System.out.println(result);
    }

    static boolean dfs( int r, int c, int curd){
        //1. 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
        if(map[r][c] == 0){
            map[r][c] = 2;
            result++;
        }
        //4방향 중 청소 되지 않은 곳이 있는지 확인
        boolean cleaned = true;
        for(int d=0;d<4;d++){
            int nextD = (curd + d)%4;
            int nr = r+dr[nextD];
            int nc = c+dc[nextD];
            if(map[nr][nc] == 0){
                cleaned = false;
                break;
            }
        }

        //2.청소할 곳이 없는 경우, 현재 방향에서 뒤로 이동할 수 있다면 이동.
        if(cleaned){
            int nr = r+dr[(curd + 2)%4];
            int nc = c+dc[(curd + 2)%4];
            if(map[nr][nc] != 1){
                boolean finished = dfs(nr,nc,curd);
                if(finished) return true;
            }
            else{
                return true;
            }
        }
        //3. 청소할 곳이 있는 경우,
        else{
            for(int d=0;d<4;d++){
                int nextD = (curd + 3 - d) % 4;
                int nr = r + dr[nextD];
                int nc = c + dc[nextD];
                if(map[nr][nc] == 0){
                    boolean finished = dfs(nr, nc, nextD);
                    if(finished) return true;
                }
            }
        }
        return true;
    }
}
