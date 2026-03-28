import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        //그냥  bfs로 최단 거리 찾는게 빠를까?
        // 다음 위치와 시간을 함께 저장.
        //1. 현재 위치가 0보다 크다면 뒤로 가기도 진행.
        //2. 앞으로 가기
        //
        Deque<int[]> dq = new ArrayDeque<>();
        dq.offerLast(new int[] { N, 0});
        boolean[] visited = new boolean[100_001];
//        visited[N] = true;
        while(!dq.isEmpty()) {
            int[] temp = dq.pollFirst();
            int curr = temp[0];
            int time = temp[1];
            if(visited[curr]) continue;
            visited[curr] = true;
            if (curr == K) {
                System.out.println(time);
                return;
            }

            //3가지 경우의 수 탐색하기

            //1. 순간이동 o
            int next = curr * 2;
            if (next <= 100_000 && !visited[next]) {
                dq.offerFirst(new int[]{next, time});
            }
            //2. 순간이동 x, 앞으로 한칸
            next = curr + 1;
            if (next <= 100_000 && !visited[next]) {
                dq.offerLast(new int[]{next, time + 1});
            }
            //3. 순간이동 x, 뒤로 한칸
            next = curr - 1;
            if (next >= 0 && !visited[next]) {
                dq.offerLast(new int[]{next, time + 1});
            }

        }
    }
}
