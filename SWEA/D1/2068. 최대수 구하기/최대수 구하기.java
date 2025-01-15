
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        for(int t=1;t<=T;t++){
            int max=0;
            for(int i=0;i<10;i++){
                int n = scanner.nextInt();
                if(max<n){
                    max = n;
                }
            }
            System.out.println("#"+t+" "+max);
        }
    }
}
