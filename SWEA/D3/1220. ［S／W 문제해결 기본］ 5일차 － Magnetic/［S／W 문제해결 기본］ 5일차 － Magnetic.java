
import java.util.Scanner;

public class Solution {
    static int[][] arr;
    static int n;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        for(int t= 1;t<=10;t++){
            n = scanner.nextInt();
            scanner.nextLine();
            int result = 0;
            arr = new int[n][n];
            for(int i=0;i<n;i++){
                for(int j =0;j<n;j++){
                    arr[i][j] = scanner.nextInt();
                }

            }
            for(int c=0;c<n;c++){
                result+=count(c);
            }
            System.out.println("#"+t+" "+result);
        }
    }
    public static int count(int c){
        int sum =0;
        boolean flag = false;
        for(int i=0;i<n;i++){
            if(!flag && arr[i][c]==1){
                flag = true;
            }
            else if(flag && arr[i][c]==2){
                sum++;
                flag = false;
            }

        }
        return sum;
    }
}
