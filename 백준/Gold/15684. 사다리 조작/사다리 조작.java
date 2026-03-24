import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] grid;
    static int C, R, H;
    static int result = Integer.MAX_VALUE;
    static int limit = 3;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        C = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = 2*C-1;

        grid = new int[R][C];
        for(int c=0;c<(C);c+=2){
            for(int r=0;r<R;r++){
                grid[r][c] = 1;
            }
        }
        for(int i=0;i<H;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            grid[a-1][b*2-1] = 1;
        }
//        for(int i=0;i<R;i++){
//            for(int j=0;j<C;j++){
//                System.out.print(grid[i][j]+" ");
//            }
//            System.out.println();
//        }
        //완탐
        dfs(0,1, 0);
        if(result==Integer.MAX_VALUE){
            result = -1;
        }
        System.out.println(result);
    }

    static void dfs(int r, int c, int count){
        if(count > 3){
            return;
        }
        if(isConnected()) {
            if(result>count){
                result = count;
                limit = result;
            }
            return;
        }

        for(int i=r;i<R;i++){
            int col = i==r?c:1;
            for(int j=col;j<C;j+=2){
                if(grid[i][j] == 1) continue;
                if(j > 1 && grid[i][j-2] == 1) continue;
                if(j < C-2 && grid[i][j+2] == 1) continue;

                grid[i][j] = 1;
                dfs(i, j+2, count+1);
                grid[i][j] = 0;

            }
        }
    }
    static boolean isConnected(){
        for(int i=0;i<C;i+=2){
            boolean check = lineCheck(i);
            if(!check){
                return false;
            }
        }
        return true;
    }
    static boolean lineCheck(int start){
        int[] cur = new int[] {0, start};
        while(true){
            if(cur[0] == R && cur[1] == start){
                return true;
            }else if(cur[0] == R && cur[1] != start){
                return false;
            }

            if(cur[1] > 0 && grid[cur[0]][cur[1]-1]==1){
                cur[1] -=2;
                cur[0]++;
                continue;
            }else if(cur[1]<C-1 && grid[cur[0]][cur[1]+1]==1){
                cur[1]+=2;
                cur[0]++;
                continue;
            }else{
                cur[0]++;
                continue;
            }
        }
    }
}
