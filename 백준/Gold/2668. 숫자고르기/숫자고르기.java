import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main{
    static int N;
    static int[][] grid;
    static boolean[] selected;
    static List<Integer> result;


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        grid = new int[2][N+1];
        for(int i=1;i<N+1;i++){
            grid[0][i] = i;
            grid[1][i] = Integer.parseInt(br.readLine());
        }

        //집합으로 구할까? -> 2^100 개임;;
        //비트 마스킹? 똑같이 2^100개 반복문임;
        //상단 숫자는 하단 숫자의 인덱스로 알 수 있음 -> 하단 만 고른다?
        //매번 현재 선택 가능한걸 찾아서 선택? -> 밀어내기 식이면 절대 못 찾음;
        //시작 숫자에서 시작해서 다시 처음 숫자로 돌아오면 해당 숫자 선택

        selected = new boolean[N+1];
        result = new ArrayList<>();
        for(int i=1;i<N+1;i++){
            if(selected[i]) continue;
            List<Integer> list = new ArrayList<>();
            list.add(i);
            boolean isCycle = check(grid[1][i], list);
            if(isCycle){
                int start = i;
                result.add(start);
                selected[start] = true;
                int next = grid[1][start];
                while(next != start){
                    result.add(next);
                    selected[next] = true;
                    next = grid[1][next];
                }
            }
        }

//        dfs(0,selected);

        System.out.println(result.size());
        result.sort(Integer::compare);
        for(int i=0;i<result.size();i++){
            System.out.println(result.get(i));
        }
    }
    static boolean check(int n, List<Integer> list){
        if(n == list.get(0)){
            return true;
        }
        if(list.contains(n)){
            return false;
        }

        list.add(n);
        boolean result = check(grid[1][n], list);
        return result;
    }
//    static void dfs(int cnt, boolean[] selected){
//        if(cnt == N){
//            List<Integer> set1 = new ArrayList<>();
//            List<Integer> set2 = new ArrayList<>();
//            for(int i=0;i<N;i++){
//                if(selected[i]){
//                    set1.add(i+1);
//                    set2.add(grid[1][i]);
//                }
//            }
//            set1.sort(Integer::compare);
//            set2.sort(Integer::compare);
//
//            if(set1.size()==0) return;
//            int temp = set1.get(set1.size()-1);
//            for(int i=set1.size()-2;i>=0;i--){
//                if(temp == set1.get(i)){
//                    set1.remove(i);
//                }else{
//                    temp = set1.get(i);
//                }
//            }
//            temp = set2.get(set2.size()-1);
//            for(int i=set2.size()-2;i>=0;i--){
//                if(temp == set2.get(i)){
//                    set2.remove(i);
//                }else{
//                    temp = set2.get(i);
//                }
//            }
//            if(set1.equals(set2)){
//                if(result.size() < set1.size()){
//                    result = new ArrayList<>(set1);
//                }
//            }
//
//            return;
//        }
//
//        selected[cnt] = true;
//        dfs(cnt+1, selected);
//        selected[cnt] = false;
//        dfs(cnt+1, selected);
//    }

}
