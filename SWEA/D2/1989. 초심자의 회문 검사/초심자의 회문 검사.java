
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        scanner.nextLine();  //버퍼 지우기

        for(int t=1;t<=T;t++){
            String str = scanner.nextLine();
            StringBuffer temp = new StringBuffer(str);
            String reverse = temp.reverse().toString();
            //System.out.println(reverse);
            if(str.equals(reverse)){
                System.out.println("#"+t+" "+1);
            }
            else {
                System.out.println("#"+t+" "+0);
            }
        }
    }
}
