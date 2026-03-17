import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        BigInteger result = BigInteger.valueOf(1);
        for(int i=1;i<=N;i++){
            result = result.multiply(BigInteger.valueOf(i));
        }
        System.out.println(result);
    }
}
