import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static int[][] map;
    static int[][] A;
    static ArrayList<Integer>[][] treeList;
    static int[] dr = {-1,-1,-1,0,1,1,1,0};
    static int[] dc = {-1,0,1,1,1,0,-1,-1};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());  //땅 크기 N*N
        M = Integer.parseInt(st.nextToken());  //M개의 초기 나무
        K = Integer.parseInt(st.nextToken());  //K년 지난 후 나무 개수 출력

        A = new int[N][N];
        //A[r][c]의 정보 입력 //겨울에 추가되는 양분 정보
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //현재 토양에 있는 양분을 저장할 배열 int[][] map
        map = new int[N][N];
        //처음 토양 양분
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                map[i][j]+=5;
            }
        }
        //현재 토양에 있는 나무 List[N][N] treeList
        treeList = new ArrayList[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                treeList[i][j] = new ArrayList<Integer>();
            }
        }//각 위치에 나무 정보를 저장할 리스트 생성

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());  //x좌표
            int y = Integer.parseInt(st.nextToken());  //y좌표
            int z = Integer.parseInt(st.nextToken());  //나무 나이
            treeList[x-1][y-1].add(z);
        }
        //K년 반복하고 마지막에 전체 나무 개수 출력
        for(int k=0;k<K;k++){
            //봄
            //여름
            spring();
            //가을
            fall();
            //겨울
            winter();
        }
        int result = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                result += treeList[i][j].size();
            }
        }
        System.out.println(result);

    }
    //봄에는 나무마다 자신의 나이만큼 양분을 먹는다.
    //양분은 나이가 어린 나무부터 먹는다.
    //만약 양분이 부족하다면 나무는 죽는다.
    //(봄 + 여름)여름에는 봄에 죽은 나무가 양분으로 변한다. 양분 = 나이/2
    static void spring(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                int treeCount = treeList[i][j].size();
                treeList[i][j].sort(null);
                int removeIdx = -1;
                int addNutrition = 0;
                for(int t=0;t<treeCount;t++){
                    int treeAge = treeList[i][j].get(t);
                    if(treeAge <= map[i][j]){
                        map[i][j] -= treeAge;
                        treeAge ++;
                        treeList[i][j].set(t, treeAge);
                    }else{
                        if(removeIdx == -1) removeIdx = t;
                        addNutrition += treeAge/2;
                    }
                }
                if(removeIdx != -1) {
                    for (int t = treeCount - 1; t >= removeIdx; t--) {
                        treeList[i][j].remove(t);
                    }
                    map[i][j] += addNutrition;
                }
            }
        }
    }
    //가을에는 나무가 번식한다. 나이가 5의 배수인 나무만 번식한다.
    //번식하는 나무 주변으로 8칸에 나이가 1인 나무들이 추가된다.
    static void fall(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                int treeCount = treeList[i][j].size();
                for(int t=0;t<treeCount;t++){
                    int treeAge = treeList[i][j].get(t);
                    if(treeAge%5 == 0){
                        for(int d=0;d<8;d++){
                            int nr = i+dr[d];
                            int nc = j+dc[d];
                            if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
                            treeList[nr][nc].add(0,1);
                        }
                    }
                }
            }
        }
    }
    //겨울에는 땅에 양분을 추가한다. 추가되는 양분은 A[r][c]이고 입력으로 주어진다.
    static void winter(){
        nutrient();
    }
    static void nutrient(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                map[i][j]+=A[i][j];
            }
        }
    }

}
