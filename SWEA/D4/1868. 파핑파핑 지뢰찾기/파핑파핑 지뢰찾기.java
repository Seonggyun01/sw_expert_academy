import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
	static char[][] map;
	static boolean[][] visited;
	static int N;
	static int[] dx = {-1,0,1,1,1,0,-1,-1}; //시계방향 탐색
	static int[] dy = {-1,-1,-1,0,1,1,1,0}; 
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			visited = new boolean[N][N];
			for(int i=0;i<N;i++) {
				String str = br.readLine();
				for(int j=0;j<N;j++) {
					map[i][j] = str.charAt(j);
					if(map[i][j] == '*') visited[i][j] = true;
				}
			}
			Queue<int[]> queue = new ArrayDeque<>();
			int result = 0;
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(visited[i][j]) continue;
					int count = findBomb(new int[] {i,j});
					boolean flag = false;
					if(count == 0) {
						flag = true;
						visited[i][j] = true;
						queue.offer(new int[] {i,j});
						while(!queue.isEmpty()) {
							int[] cur = queue.poll();
							for(int d=0;d<8;d++) {
								int nr = cur[0]+dy[d];
								int nc = cur[1]+dx[d];
								if(isIn(nr,nc) && !visited[nr][nc]) {
									visited[nr][nc] = true;
									if(findBomb(new int[] {cur[0]+dy[d],cur[1]+dx[d]}) == 0) {
										queue.offer(new int[] {cur[0]+dy[d],cur[1]+dx[d]});
									}
								}
							}
						}
					}
					if(flag) result++;
					
				}
			}
			//8방에 지뢰가 없는 좌표 찾기 
			//-> 해당 좌표가 연결되어 있는지 확인하기
			//-> 구역의 개수 + 아직 방문하지 클릭하지 않은 지뢰 주변 '.' 개수 찾기
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(!visited[i][j]) result++;
				}
			}
			System.out.println("#"+t+" "+result);
		}
		
		
	}
	//주변 8칸 안의 지뢰 개수 찾는 기능
	private static int findBomb(int[] cur) {
		int count = 0;
		for(int d=0;d<8;d++) {
			if(isIn(cur[0]+dy[d],cur[1]+dx[d]) && map[cur[0]+dy[d]][cur[1]+dx[d]] == '*') count++;
		}
		return count;
	}
	
	//map의 범위 안에 들어있는지 체크하는 기능
	private static boolean isIn(int r, int c) {
		return (r>=0 && r<N && c>=0 && c<N);
	}

}
