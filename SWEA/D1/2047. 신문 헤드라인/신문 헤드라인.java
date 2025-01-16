
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        StringBuilder answer = new StringBuilder();
        for(int i=0;i<str.length();i++){
            if(97<=str.charAt(i) && str.charAt(i)<123){
                answer.append((char)(str.charAt(i)-32));
            }
            else {
                answer.append(str.charAt(i));
            }
        }
        System.out.println(answer);
    }
}
