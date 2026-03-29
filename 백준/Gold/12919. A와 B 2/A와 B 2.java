import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        String target = br.readLine();
        int result = 0;

        //문자열을 직접 비교해야 함!
        //문자열 뒤집기를 어떻게 할 것인가...
        //문자열을 입력에서 늘려가면 너무 많은 경우의 수 때문에 메모리 초과 발생 -> 그럼 target에서 줄여 나가면?

        Deque<String> dq = new ArrayDeque<>();
        dq.offerLast(target);
        while(!dq.isEmpty()){
            String curr = dq.pollFirst();
            if(curr.equals(input)){
                result = 1;
                break;
            }
            if(curr.length()<=input.length()) continue;
            if(curr.charAt(curr.length()-1) == 'A'){
                dq.offerLast(curr.substring(0,curr.length()-1));
            }
            if(curr.charAt(0) == 'B'){
                String next = curr.substring(1);
                dq.offerLast(reverse(next));
            }
        }

//        while(!dq.isEmpty()){
//            String curr = dq.pollFirst();
//            if(curr.equals(target)){
//                result = 1;
//                break;
//            }
//            if(curr.length()>=50 || curr.length()>=target.length()) continue;
//            dq.offerLast(curr+"A");
//            dq.offerLast(reverse(curr+"B"));
//        }
        System.out.println(result);
    }

    static String reverse(String str){
        String result = "";
        for(int i=str.length()-1;i>=0;i--){
            result += str.charAt(i);
        }
        return result;
    }
}
