import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int input, output, count, turn;
    static int[] belt;
    static boolean[] onBelt;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        input = 0;
        output = N-1;

        belt = new int[2*N];
        onBelt = new boolean[2*N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<belt.length;i++){
            belt[i] = Integer.parseInt(st.nextToken());
        }
        count = 0;
        turn = 0;

        while(count < K){
            turn++;
            //1. 벨트가 회전한다.  인풋이 한칸 뒤로 이동, 아웃풋도 한칸 뒤로 이동, 아웃풋의 칸에 로봇이 있다면 탈출시키기.
            if(input == 0) input = 2*N-1;
            else{
                input--;
            }
            if(output == 0) output = 2*N-1;
            else{
                output--;
            }
            robotOut();


            //2. 먼저 투입된 로봇 순서대로 한칸 전진할 수 있으면 전진한다.
                //1. 앞에 칸에 로봇이 없고 && 앞 칸으 내구도가 1이상이어야 한다.
            int index = output-1;
            for(int i=0;i<N-2;i++){
                int next = index + 1;
                if(next == 2*N) next = 0;
                if(index==-1) index = 2*N-1;
                if(onBelt[index] && !onBelt[next] && belt[next]>0){
                    onBelt[index] = false;
                    onBelt[next] = true;
                    belt[next] --;
                    if(belt[next] == 0) count++;
                }
                index--;
                if(index==-1) index = 2*N-1;
            }
            robotOut();

            //3. 올리는 위치의 내구도가 1이상이면 로봇을 새로 올린다.
            if(belt[input]>0){
                onBelt[input] = true;
                belt[input] --;
                if(belt[input]==0) count++;
            }

            //4. 내구도가 0인 칸이 K개 이상이라면 과정을 종료한다.
            if(count>=K){
                System.out.println(turn);
                break;
            }
        }
    }
    static void robotOut(){
        if(onBelt[output]){
            onBelt[output] = false;
        }
    }
}
