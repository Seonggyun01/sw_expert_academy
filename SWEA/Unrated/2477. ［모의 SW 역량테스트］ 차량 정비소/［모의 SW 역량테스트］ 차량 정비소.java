import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static class Customer{
		char state; //X: 도착 전, W: 도착함, A: 접수 창구 이용 중, R: 정비 창구 기다림, B: 정비 창구 이용 중, C: 모든 과정 통과
		int Anum; //이용한 접수 창구 번호
		int Bnum; //이용한 정비 창구 번호
		public Customer(char state, int anum, int bnum) {
			super();
			this.state = state;
			Anum = anum;
			Bnum = bnum;
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());  //접수 창구 개수
			int M = Integer.parseInt(st.nextToken());  //정비 창구 개수
			int K = Integer.parseInt(st.nextToken());  //방문 고객 수
			int A = Integer.parseInt(st.nextToken());  //target 접수 창구 번호 
			int B = Integer.parseInt(st.nextToken());  //target 정비 창구 번호

			int[] timeA = new int[N];
			int[] timeB = new int[M];
			int[] arrivedTime = new int[K];
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				timeA[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<M;i++) {
				timeB[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<K;i++) {
				arrivedTime[i] = Integer.parseInt(st.nextToken());
			}
			
			List<Customer> customers = new ArrayList<>();
			for(int i=0;i<K;i++) {
				customers.add(new Customer('X', -1, -1));
			}
			
			int result = 0;  //A와 B를 이용한 고개 번호의 합, 만약 없으면 -1 출력
			
			//===========================================================//
			//모든 고객이 통과 할 때 까지 반복
			int passCnt = 0;
			int time = 0;
			int[] useTimeA = new int[N];  //접수 창구를 이용중인 고객의 남은 시간
			int[] useTimeB = new int[M];  //정비 창구를 이용중인 고객의 남은 시간
			
			Queue<Customer> queue = new ArrayDeque<>();
			while(true) {
				if(passCnt == K) { 
					break;
				}
				for(int i=0;i<K;i++) {//시간에 따라서 도착한 고객의 상태를 변화 시킨다.
					if(arrivedTime[i] <= time && customers.get(i).state=='X') {
						customers.get(i).state = 'W';
					}
				}
				//=========접수 창구 먼저 비우기=======//
				for(int a=0;a<N;a++) {
					if(useTimeA[a]>0) --useTimeA[a];
					if(useTimeA[a]==0) {
						for(int i=0;i<K;i++) {
							if(customers.get(i).Anum == a && customers.get(i).state=='A') {
								customers.get(i).state='R';
								queue.offer(customers.get(i));
							}
						}
					}
				}
				//=========정비 창구 먼저 비우기======//
				for(int i=0;i<K;i++) {
					if(customers.get(i).state=='B') {
						int b = customers.get(i).Bnum;
						if(useTimeB[b]>0) --useTimeB[b];
						if(useTimeB[b]==0){
							customers.get(i).state='C';
							passCnt++;
						}
					}
				}
				//=========접수 창구 고객 번호 순으로 넣기========//
				for(int i=0;i<K;i++) {
					if(customers.get(i).state == 'W') {
						for(int a=0;a<N;a++) {
							if(useTimeA[a]== 0) {
								customers.get(i).state='A';
								customers.get(i).Anum = a;
								useTimeA[a]= timeA[a];
								break;
							}
						}
					}
				}
				//=======정비 창구 접수 창구 번호 순으로 넣기=======//
				
				for(int b=0;b<M;b++) {
					if(useTimeB[b] == 0) {
						if(queue.isEmpty()) break;
						Customer curr = queue.poll();
						curr.state = 'B';
						curr.Bnum = b;
						useTimeB[b] = timeB[b];
					}
				}
				time++;
			}
			for(int i=0;i<K;i++) {
				if(customers.get(i).Anum==(A-1) && customers.get(i).Bnum==(B-1)) {
					result+=(i+1);
				}
			}
			
			if(result == 0) {
				System.out.println("#"+t+" "+"-1");
			}else {
				System.out.println("#"+t+" "+result);
			}
			
		}
	}

}
