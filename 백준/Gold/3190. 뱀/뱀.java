import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Move{
        int turn;
        char direction;
        public Move(int turn, char direction) {
            this.turn = turn;
            this.direction = direction;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int appleCnt = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][N];
        for(int i=0;i<appleCnt;i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[r - 1][c - 1] = 1; //1은 사과
        }
        st = new StringTokenizer(br.readLine());
        int moveCnt = Integer.parseInt(st.nextToken());
        List<Move> moveList = new ArrayList<>();
        for(int i=0;i<moveCnt;i++){
            st = new StringTokenizer(br.readLine());
            int turn = Integer.parseInt(st.nextToken());
            char direction = st.nextToken().charAt(0);
            moveList.add(new Move(turn,direction));
        }
        boolean isDone = false;
        int turn = 1;
        map[0][0] = 2;
        int[] dr = {0,1,0,-1};
        int[] dc = {1,0,-1,0};
        int d = 0;
        List<int[]> snake = new ArrayList<>();
        snake.add(new int[] {0,0});
        while(!isDone){
            int[] head = snake.get(0);
            int nr = head[0]+dr[d];
            int nc = head[1]+dc[d];
            if(nr<0 || nr>=N || nc<0 || nc>=N || map[nr][nc] == 2){
                isDone = true;
                break;
            }
            snake.add(0, new int[] {nr,nc});

            int next = map[nr][nc];
            map[nr][nc] = 2;
            if(next == 0){
                //사과가 아닌 경우 꼬리를 이동시킨다.
                int[] tail = snake.get(snake.size()-1);
                map[tail[0]][tail[1]] = 0;
                snake.remove(tail);
            }
            if(!moveList.isEmpty()){
                Move m = moveList.get(0);
                if(m.turn == turn){
                    moveList.remove(0);
                    if(m.direction == 'L'){
                        d = (d-1+4)%4;
                    }else if(m.direction == 'D'){
                        d = (d+1)%4;
                    }
                }
            }
            turn++;
        }
        System.out.println(turn);
    }
}
