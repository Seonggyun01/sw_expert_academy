
import java.util.Scanner;

public class Solution {
    static int[] arr;
    static int N;
    static int K;
    static int sum;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        for(int t =1; t<=T;t++){
            N = scanner.nextInt();
            K = scanner.nextInt();
            arr = new int[N];
            for(int i=0;i<N;i++){
                arr[i] = scanner.nextInt();
            }
            dfs(0,0);
            System.out.println("#"+t+" "+sum);
            sum = 0;

        }
    }
    public static void dfs(int index, int currentSum){
        if(currentSum > K){
            //System.out.println(currentSum);
            return;
        }
        if(currentSum == K){
            //System.out.println(currentSum);
            sum++;
            return;
        }
        for(int i=index;i<N;i++){
            //System.out.println(currentSum);
            dfs(i+1,currentSum+arr[i]);
        }
    }
}
