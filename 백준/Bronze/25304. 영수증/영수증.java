import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long X = Long.parseLong(st.nextToken());
        int N = Integer.parseInt(br.readLine());

        long sum = 0;
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int price = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());
            sum+=price*count;
        }
        if(X == sum){
            System.out.println("Yes");
            return;
        }
        System.out.println("No");
    }
}
