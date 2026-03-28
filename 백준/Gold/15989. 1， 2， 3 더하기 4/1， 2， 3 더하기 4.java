import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int result;
    static int[] memory;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        memory = new int[10001];
        //모든 경우의 수를 만들고 배열 인덱스 접근으로 출력하자!
        //1만 사용해서 만드는 경우는 모든 숫자가 1가지 이다.
        Arrays.fill(memory,1);

        //1,2만 사용해서 만드는 경우
        for(int i=2;i<10001;i++){
            memory[i] += memory[i-2];
        }
        //1,2,3 모두 사용하는 경우
        for(int i=3;i<10001;i++){
            memory[i] += memory[i-3];
        }

        for(int t=0;t<T;t++){
            //정수 n 입력.
            //n을 1,2,3의 합으로 나타내는 방법 수
            //수의 순서는 무시. 1+2+3 == 3+2+1, 1+2 != 1+1+1 이므로 n을 만드는 수의 중복 조합
            //큰 수 부터 선택하면 마지막에 하나씩 숫자를 줄여가며 선택하자..?? --> 매번 완탐 시 시간 초과 발생 ㅠㅠ
            //dp로 쪼개서 생각하자..
            st = new StringTokenizer(br.readLine());
            int input = Integer.parseInt(st.nextToken());
            System.out.println(memory[input]);
        }
    }
}
