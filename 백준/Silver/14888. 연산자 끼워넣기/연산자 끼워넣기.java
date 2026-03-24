import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

public class Main{
    static int N;
    static int[] nums, opers;
    static int minResult = Integer.MAX_VALUE;
    static int maxResult = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }
        opers = new int[4];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<4;i++){
            opers[i] = Integer.parseInt(st.nextToken());
        }
        //중복순열로 연산자 순서 구하기(N-1개의 중복 순열)
        combination(0,nums[0]);


        //최종 출력하기
        System.out.println(maxResult);
        System.out.println(minResult);
    }
    static void combination(int count, int result){
        if(count == N-1){
            minResult = Math.min(minResult, result);
            maxResult = Math.max(maxResult,result);
            //결과를 min, max와 비교하고 업데이트하기
            return;
        }
        for(int i=0;i<4;i++){
            if(opers[i] > 0){
                opers[i]--;
                if(i==0){
                    combination(count+1,result+nums[count+1]);
                }else if(i==1){
                    combination(count+1,result-nums[count+1]);
                }else if(i==2){
                    combination(count+1,result*nums[count+1]);
                }else if(i==3){
                    combination(count+1,result/nums[count+1]);
                }
                opers[i]++;
            }
        }
    }
}
