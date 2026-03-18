import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] A = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            A[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        long count = N; //기본적으로 총 감독관이 N명 필요함.
        for(int i=0;i<N;i++){
            A[i] -= B;
            if(A[i]<=0) continue;
            count+=A[i]/C;
            A[i]%=C;
            if(A[i]>0) count++;
        }
        System.out.println(count);
    }
}
