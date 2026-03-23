import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        List<int[]> list = new ArrayList<>();
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            list.add(new int[] {time, price});
        }
        int maxPrice = 0;
        for(int i=0;i<Math.pow(2,N);i++){
            boolean[] days = new boolean[N];
            int totalPrice = 0;
            for(int j=0;j<N;j++){
                if((i & (1<<j))!= 0){
                    int time = list.get(j)[0];
                    int price = list.get(j)[1];
                    boolean alreadyReserved = false;
                    for(int t=j;t<j+time;t++){ //이미 잡혀있는 상담이 있는지 체크
                        if(t>=N || days[t]) {
                            alreadyReserved = true;
                            break;
                        }
                    }
                    if(!alreadyReserved){
                        for(int t=j;t<j+time;t++){ //예약 가능하므로 days에 기록하고, price에 누적하기
                            days[t] = true;
                        }
                        totalPrice += price;
                    }
                    else{
                        break;
                    }
                }
            }
            maxPrice = Math.max(maxPrice, totalPrice);
        }
        System.out.println(maxPrice);
    }
}
