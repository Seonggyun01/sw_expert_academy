
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        scanner.nextLine(); //버퍼 지우기
        for(int t =1;t<=T;t++){
            String str = scanner.nextLine();
            System.out.println("#"+t+" "+count(str));
        }
    }

    public static int count(String str){
        boolean flag = false;   //초기가 값 0
        //System.out.println(flag);
        int sum = 0;
        for(int i =0;i<str.length();i++){
            if(!flag && str.charAt(i)=='1'){
                flag = true;
                sum ++;
                //System.out.println(flag);
            } else if (flag && str.charAt(i)=='0') {
                flag = false;
                sum ++;
                //System.out.println(flag);
            }
        }
        return sum;
    }
}
