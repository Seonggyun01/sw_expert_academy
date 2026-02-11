
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Solution {
	static int N = 4001;
	static int[][] map = new int[N][N];
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1;t<=T;t++) {
			int totalEnergy = 0;  //원자의 보유에너지 1~100 * 1000
			int count = Integer.parseInt(br.readLine());  //원자 개수
			
			ArrayDeque<Unit> dq = new ArrayDeque<>();  //살아있는 원자 리스트
			for(int i=0;i<count;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine()," ");
				int x = (Integer.parseInt(st.nextToken())+1000)<<1;
				int y = (Integer.parseInt(st.nextToken())+1000)<<1;
				int dir = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				
				map[y][x] = e;
				dq.addLast(new Unit(x,y,dir,e));
			}
			
			while(!dq.isEmpty()) {
				Unit cur = dq.pollFirst();
				
				// 1. 충돌 확인
				if(map[cur.y][cur.x] != cur.e){
					totalEnergy += map[cur.y][cur.x];
					map[cur.y][cur.x] = 0;
					continue;
				}
				
				// 2. 충돌되지 않았으면 다음 방향에 기록 후 덱에 넣기
				map[cur.y][cur.x] = 0;
				int nx = cur.x+dx[cur.dir];
				int ny = cur.y+dy[cur.dir];
				if(nx>=0 && nx<N && ny>=0 && ny<N) {
					cur.x = nx;
					cur.y = ny;
					map[cur.y][cur.x] += cur.e;
					dq.addLast(cur);
				}
			}
			System.out.println("#"+t+" "+totalEnergy);
		}
	}
	
	static class Unit{
		public Unit(int x, int y, int dir, int e) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.e = e;
		}

		int x,y,dir,e;
	}
	
}













