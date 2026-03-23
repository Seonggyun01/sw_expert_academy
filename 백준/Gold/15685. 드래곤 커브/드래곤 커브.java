import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = {0,-1,0,1};
    static int[] dc = {1,0,-1,0};
    static boolean[][] map;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        map = new boolean[201][201];
        int result = 0;

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            List<Integer> list = new ArrayList<>();
            list.add(d);
            map[y][x] = true;
            dfs(0,k,list);

            for(int direction : list){
                int nx = x+dc[direction];
                int ny = y+dr[direction];
                map[ny][nx] = true;
                x = nx;
                y = ny;
            }

            //List에 진행 경로를 저장하고, 역으로 뽑으면서 다음 진행 방향을 확인해야 함.
            //반대방향으로 돌리고, 90도 시계방향 회전하며 된다.
        }
//        for(int i=0;i<10;i++){
//            for(int j=0;j<10;j++){
//                System.out.print(map[i][j]?"1 ": "0 ");
//            }
//            System.out.println();
//        }
        //전체 탐색으로 모든 4개의 꼭짓점이 true라면 result++;
        for(int i=0 ;i<200;i++){
            for(int j=0;j<200;j++){
                if(map[i][j] && map[i+1][j] && map[i][j+1] && map[i+1][j+1]) result++;
            }
        }
        System.out.println(result);
    }
    static void dfs(int count, int depth, List<Integer> list){
        if(count == depth){
            return;
        }
        List<Integer> nextD = new ArrayList<>();
        for(int i=list.size()-1;i>=0;i--){ //뒤에서 부터 꺼내야 함..
            int d = list.get(i);
            int nd = ((d+2)%4 +3)%4;   //반대 방향으로 돌리고, 시계방향 회전
            nextD.add(nd);
        }
        list.addAll(nextD);
        dfs(count+1, depth,list);
    }
}
