import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N, Ncount;
	static int[][] map;
	static List<int[]> startList;
	static int[][] stairs;
	static int mintime;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
		
			map = new int[N][N];
			stairs = new int[2][2];
			startList = new ArrayList<>();
			int index = 0;
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++){
					map[i][j]= Integer.parseInt(st.nextToken()); 
					if(map[i][j]==1) {
						startList.add(new int[] {i,j});
					}else if(map[i][j]>1) {
						stairs[index][0] = i;
						stairs[index++][1] = j;
					}
				}
			}
			Ncount = startList.size();
			mintime = Integer.MAX_VALUE;
			boolean[] selected = new boolean[Ncount];
			subset(0, selected);
			
			System.out.println("#"+t+" "+mintime);
					
			//조합
			//사람들을 2개의 집합(계단)으로 나눔
			//각 사람들별로 계단까지 거리 계산
			//거리가 가까운 사람순서대로 계단에 입장. 다 통과할 때 까지 반복
			//다음계단도 동일하게 진행. 
			//각 계단 이용 시간 중 최대 값이 현재 조합에서 최소 시간이다.

		}
	}
	static void subset(int cnt, boolean[] selected) {
		if(cnt == Ncount) {
			//selected에 조합 원소들 있음.
			List<Integer> stairs1= new ArrayList<>();
			List<Integer> stairs2= new ArrayList<>();
			for(int i=0;i<Ncount;i++) {
				if(selected[i]) {
					stairs1.add(i);
				}else {
					stairs2.add(i);
				}
			}
			
			for(int i=0;i<stairs1.size();i++) {
				int index = stairs1.get(i);
				int[] position = startList.get(index);
				int dist = Math.abs(position[0]-stairs[0][0]) + Math.abs(position[1]-stairs[0][1]);
				stairs1.set(i, dist);
			}
			for(int i=0;i<stairs2.size();i++) {
				int index = stairs2.get(i);
				int[] position = startList.get(index);
				int dist = Math.abs(position[0]-stairs[1][0]) + Math.abs(position[1]-stairs[1][1]);
				stairs2.set(i, dist);
			}
			Collections.sort(stairs1);
			Collections.sort(stairs2);
			
			int time1 = simulate(stairs1, map[stairs[0][0]][stairs[0][1]]);
			int time2 = simulate(stairs2, map[stairs[1][0]][stairs[1][1]]);
			int result = Math.max(time1, time2);
			if(result<mintime) {
				mintime = result;
			}
			return;
		}
		selected[cnt]=true;
		subset(cnt+1, selected);
		selected[cnt]=false; 
		subset(cnt+1, selected);
	}
	
	
	private static int simulate(List<Integer> arrivals, int stairLength) {
		if(arrivals.size()==0) return 0;
		
		Queue<Integer> readyQ = new ArrayDeque<>(arrivals);
		Queue<Integer> stairQ = new ArrayDeque<>();
		int time = 0;
		int exitedCount = 0; // 추가: 계단을 다 내려간 사람 수
	    int total = arrivals.size(); // 총 인원 수
	    
		while(exitedCount < total) {
			time++;
			
			//현재 큐에 있는 사람들 남은 시간 1씩 줄이기
			int size = stairQ.size();
			for(int i=0;i<size;i++) {
				int remain = stairQ.poll();
				remain--;
				if(remain>0) {
					stairQ.offer(remain);
				} else {
	                exitedCount++; 
	            }
			}
			
			//큐가 3보다 작고, readyQ가 비어있지 않고
			while(true) {
				if(readyQ.isEmpty()) {
					break;
				}
				
				if(stairQ.size() == 3) {
					break;
				}
				
				int arrivalTime = readyQ.peek();
				arrivalTime++; //1분 대기 필요
				
				if(arrivalTime>time) {
					break;
				}
				
				readyQ.poll();
				stairQ.offer(stairLength);
			}
			
		}
		return time;
	}
}
