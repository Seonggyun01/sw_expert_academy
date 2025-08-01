import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        String answer = "";
        for(int i = 0; i<a.length();i++){
            if(a.charAt(i)>96){
                char c = (char)((byte)a.charAt(i) - 32);
                answer += c;
            }else{
                char c = (char)((byte)a.charAt(i) + 32);
                answer += c;
            }
        }
        System.out.println(answer);
    }
}