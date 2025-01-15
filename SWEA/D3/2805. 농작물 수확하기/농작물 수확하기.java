
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        for(int t=1;t<=T;t++){
            int N = scanner.nextInt();
            scanner.nextLine(); //버퍼 지우기
            int[][] arr = new int[N][N];
            for(int i =0;i<N;i++){
                String str = scanner.nextLine();
                for(int j=0;j<str.length();j++){
                    arr[i][j] = Integer.parseInt(str.substring(j,j+1));
                }
            }
            int middel = N/2;
            int result = 0;
            for(int i=0;i<N;i++){
                if(i<=middel) {
                    for (int j = middel - i; j < middel + i + 1; j++) {
                        result+=arr[i][j];
                        //System.out.println(i+", "+j+", "+result);
                    }
                }
                else{
                    for(int j = i-middel;j<N-(i-middel);j++){
                        result+=arr[i][j];
                        //System.out.println(i+", "+j+", "+result);
                    }
                }

            }
            System.out.println("#"+t+" " +result);
        }
    }
}
