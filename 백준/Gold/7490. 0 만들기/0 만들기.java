import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int T, N;
    static StringBuilder sb;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        for(int t=1;t<=T;t++){
            N = Integer.parseInt(br.readLine());
            //숫자는 1~N 까지 N가지 오른차순 숫자임.
            int[] select = new int[N-1]; //0이면 이어 붙이기, 1이면 더하기, 2면 빼기
            dfs(0,select);
            sb.append("\n");
        }
        System.out.println(sb);
    }
    static void dfs(int cnt, int[] select){
        if(cnt == N-1){
            //만들어진 수식을 어떻게 계산할까?
            //String으로 만들고 연산자 단위로 끊어서 연산?
            String expression = "1";
            for(int i=0;i<N-1;i++){
                if(select[i] == 1){
                    expression += "+";
                }else if(select[i] == 2){
                    expression += "-";
                }
                expression += i+2;
            }
            int sum = 0;
            int start = 0;
            for(int i=0;i<expression.length();i++){
                if(expression.charAt(i)-'0'<0 || expression.charAt(i)-'0'>9){ //숫자가 아닌경우, 수식의 끝이면 연산자로 생각하고 연산한다.
                    int num = Integer.parseInt(expression.substring(start,i));
                    sum+=num;
                    start = i;
                }
            }
            sum += Integer.parseInt(expression.substring(start));

            if(sum == 0){
                String result = "1";
                for(int i=0;i<N-1;i++){
                    if(select[i] == 0) {
                        result += " ";
                    }else if(select[i] == 1){
                        result += "+";
                    }else if(select[i] == 2){
                        result += "-";
                    }
                    result += i+2;
                }
                sb.append(result+"\n");
            }
            return;
        }
        select[cnt] = 0;
        dfs(cnt+1, select);
        select[cnt] = 1;
        dfs(cnt+1, select);
        select[cnt] = 2;
        dfs(cnt+1, select);
    }
}
