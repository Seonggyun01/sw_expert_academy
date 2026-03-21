import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main{

    static int[][] map;
    static List<int[]> houseList;
    static List<int[]> chickenList;
    static int N, M;
    static int minDist;

    static class Chicken implements Comparable<Chicken>{
        int r, c, distance;
        public Chicken(int r, int c, int distance) {
            this.r = r;
            this.c = c;
            this.distance = distance;
        }
        @Override
        public int compareTo(Chicken o) {
            return Integer.compare(this.distance, o.distance);
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        houseList = new ArrayList<>();
        chickenList = new ArrayList<>();
        minDist = Integer.MAX_VALUE;

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1){
                    houseList.add(new int[] {i,j});
                }else if(map[i][j] == 2){
                    chickenList.add(new int[] {i,j});
                }
            }
        }
        //1. 원소가 M개인 치킨집 조합 구하기
        int[] selected = new int[M];
        combination(0, 0,selected);
        //2. 각 치킨집 집합에서 모든 집들과 치킨 거리 합 구하기
        //3. 최소 집합의 치킨 거리 출력
        System.out.println(minDist);

    }
    static void combination(int count,int start, int[] selected){
        if(count == M){
            //2. 각 치킨집 집합에서 모든 집들과 치킨 거리 합 구하기
            int sum = 0;
            for(int i=0; i<houseList.size();i++){
                int min = Integer.MAX_VALUE;
                for(int j=0;j<M;j++){
                    int[] h = houseList.get(i);
                    int[] c = chickenList.get(selected[j]);
                    int dist = Math.abs(h[0] - c[0]) + Math.abs(h[1] - c[1]);
                    if(min > dist){
                        min = dist;
                    }
                }
                sum += min;
            }
            minDist = Math.min(minDist,sum);
            return;
        }
        for(int i = start;i < chickenList.size();i++){
            selected[count] = i;
            combination(count+1,i+1,selected);
        }
    }
}
