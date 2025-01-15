
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int A = scanner.nextInt();
        int B = scanner.nextInt();
        if(A > B && A-B==1){
            System.out.println('A');
        } else if (A > B && A-B == 2) {
            System.out.println('B');
        } else if (B > A && B-A == 1) {
            System.out.println('B');
        } else if (B > A && B-A == 2) {
            System.out.println('A');
        }
    }
}
