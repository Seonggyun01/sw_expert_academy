
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testcase;
        for(int t=1;t<=10;t++){
            testcase = scanner.nextInt();
            scanner.nextLine();
            int[][] arr = new int[100][100];
            int max = 0;
            for(int i=0;i<100;i++){
                for(int j=0;j<100;j++){
                    arr[i][j] = scanner.nextInt();
                }
            }
            int sum1 = 0;
            int sum2 = 0;
            for(int i=0;i<100;i++){
                int sumRow = 0;
                int sumCol = 0;
                for(int j=0;j<100;j++){
                    sumRow += arr[i][j];
                    sumCol += arr[j][i];
                }
                sum1 += arr[i][i];
                sum2 += arr[99-i][i];
                max = Math.max(Math.max(sumRow,sumCol),max);
            }
            max = Math.max(Math.max(sum1,sum2),max);
            System.out.println("#"+testcase+" "+max);
        }
    }
}
