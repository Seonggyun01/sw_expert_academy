import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static class Fish implements Comparable<Fish>{
        int r, c, power;
        public Fish(int r, int c, int power) {
            this.r = r;
            this.c = c;
            this.power = power;
        }

        @Override
        public int compareTo(Fish o) {
            return Integer.compare(this.power, o.power);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        Fish babyShark = new Fish(0,0,2);
        List<Fish> fishList = new ArrayList<>();

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9){
                    babyShark.r = i;
                    babyShark.c = j;
                    map[i][j] = 0;
                }
                else if(map[i][j] > 0){
                    fishList.add(new Fish(i,j,map[i][j]));
                }
            }
        }
        /*
        map에서 다음 물고기를 탐색하는 건 탐색에 n^2이 걸린다.
        -> Fish 클래스를 만들고, list.sort로 크기가 작은 순으로 정렬한다?
         어짜피 크기가 작은 물고기 중 가장 가까운 > 가장 위쪽 > 가장 왼쪽 의 물고기를 선택하고 그쪽으로 이동한다.
         아예 정렬을 위 순으로 하는건 불가능(2의 크기 물고기가 1의 크기 물고기 보다 멀어도 1의 크기 물고기가 오름차순 정렬 됨;;
         -> 크기 순으로 정렬 -> 현재 아기 상어 크기보다 작은 객체만 탐색, 각 물고기와 아기상어 사이의 거리를 구한다.
         -> 그 물고기의 정보를 현재 우선순위 Fish로 따로 저장한다. 다음 물고기가 현재 물고기보다 r이 작은 경우 업데이트, 같으면 c가 작은 경우 업데이트
         -> 그 물고기를 List에서 뺀다. 어짜피 빼도 크기 순으로 정렬되어 있어서 추가적 정렬 불필요.
         */
        fishList.sort(null);
        boolean isDone = false;
        int eatCount = 0;
        int time = 0;
        if(fishList.isEmpty()) {
            isDone = true;
        }
        while(!isDone){
            Fish target = new Fish(50,50,1); // 초기값으로 가장 멀리있는 물고기 할당
            //아기 상어 크기보다 작거나 같은 물고기들 거리 탐색
            int dist = 0;
            //아기 상어보다 크기가 큰 물고기가 있다면 지나갈 수 없다... ㅠㅠ bfs로 거리 탐색 해보자.
            int[][] distMap = bfs(new int[] {babyShark.r, babyShark.c}, babyShark.power);
            for(Fish fish : fishList){
                if(fish.power>=babyShark.power) break;
                //가능한 물고기 중 가장 위쪽이고 왼쪽인 물고기 저장
                int beforeDist = target.r == 50? Integer.MAX_VALUE:distMap[target.r][target.c];
                int curDist = distMap[fish.r][fish.c];
                if(curDist == -1){
                    continue;
                }
                if(beforeDist<curDist) continue;
                else if(beforeDist>curDist) {
                    target = fish;
                    dist = curDist;
                    continue;
                }
                else {
                    if(target.r<fish.r) continue;
                    else if(target.r > fish.r) {
                        target = fish;
                        dist = curDist;
                        continue;
                    }
                    else {
                        if(target.c<fish.c) continue;
                        else if(target.c > fish.c){
                            target = fish;
                            dist = curDist;
                            continue;
                        }
                    }
                }
            }
            if(target.r == 50){
                isDone = true;
                continue;
            }
            eatCount ++;
            time += dist;
            babyShark.r = target.r;
            babyShark.c = target.c;
            if(eatCount == babyShark.power){
                babyShark.power ++;
                eatCount = 0;
            }
            map[target.r][target.c] = 0;
            fishList.remove(target);
        }
        System.out.println(time);
    }
    static int[] dr = {0,1,0,-1};
    static int[] dc = {1,0,-1,0};
    static int[][] bfs(int[] babyShark, int power){
        Queue<int[]> q = new ArrayDeque<>();
        int[][] distMap = new int[N][N];
        for(int i=0;i<N;i++){
            Arrays.fill(distMap[i],-1);
        }
        boolean[][] visited = new boolean[N][N];
        q.offer(babyShark);
        visited[babyShark[0]][babyShark[1]] = true;
        distMap[babyShark[0]][babyShark[1]] = 0;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            for(int d = 0; d < 4; d++){
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                int nextDist = distMap[cur[0]][cur[1]] + 1;
                if(nr<0 || nr>=N || nc<0 || nc>=N || visited[nr][nc] || map[nr][nc]>power) continue;
                q.offer(new int[] {nr,nc});
                visited[nr][nc] = true;
                distMap[nr][nc] = nextDist;
            }
        }
        return distMap;
    }
}
