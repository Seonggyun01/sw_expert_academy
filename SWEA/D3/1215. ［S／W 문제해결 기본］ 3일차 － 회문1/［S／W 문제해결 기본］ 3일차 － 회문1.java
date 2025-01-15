
import java.util.Scanner;

public class Solution {
    static String[] arr;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        for(int t=1;t<=10;t++){
            int n = scanner.nextInt();   //찾아야 하는 회문 길이
            scanner.nextLine();  //버퍼 지우기
            arr = new String[8];
            for(int i=0;i<8;i++){
                arr[i] = scanner.nextLine();
            }
            int result = findPalindrome(n);
            System.out.println("#"+t+" "+result);
        }
    }

    public static int findPalindrome(int n){
        int sum = 0;
        for(int r = 0;r<8;r++) {
            for (int i = 0; i < 8 - n + 1; i++) {
                for (int j = 0; j < n / 2; j++) {
                    if (arr[r].charAt(i+j) != arr[r].charAt(i + n - 1 - j)){
                        break;
                    }
                    if(j == n/2 - 1){
                        //System.out.println(r+","+i+","+j);
                        sum++;
                    }
                }
            }
        }
        for(int c = 0;c<8;c++){
            for(int i=0; i<8-n+1;i++){
                for(int j =0;j<n/2;j++){
                    if(arr[i+j].charAt(c)!=arr[i+n-1-j].charAt(c)){
                        break;
                    }
                    if(j==n/2-1){
                        //System.out.println(c+","+i+","+j);
                        sum++;
                    }
                }
            }
        }
        return sum;
    }
}
