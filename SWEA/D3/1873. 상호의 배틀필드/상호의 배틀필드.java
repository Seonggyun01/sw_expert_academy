
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int H, W;
	static char[][] map;
	static int x,y,d;
	static int[] dx = {1,0,-1,0}; //우하좌상(시계방향)
	static int[] dy = {0,1,0,-1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			map = new char[H][W];
			for(int i=0;i<H;i++) {
				String str = br.readLine();
				for(int j=0;j<W;j++) {
					map[i][j] = str.charAt(j);
					if(map[i][j] == '^') {
						d = 3;
						x = j;
						y = i;
					}else if(map[i][j] == '<') {
						d = 2;
						x = j;
						y = i;
					}else if(map[i][j] == 'v') {
						d = 1;
						x = j;
						y = i;
					}else if(map[i][j] == '>') {
						d = 0;
						x = j;
						y = i;
					}
				}
			}
			
			int N = Integer.parseInt(br.readLine());
			String inputStr = br.readLine();
			for(int i=0;i<inputStr.length();i++) {
				char c = inputStr.charAt(i);
				if(c=='U' || c=='D' || c=='L' || c=='R') move(c);
				else if(c=='S')shoot(c);
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ");
			for(int i=0;i<H;i++) {
				for(int j=0;j<W;j++) {
					if(i==y && j==x) {
						if(d==0) {
							sb.append(">");
						}else if(d==1) {
							sb.append("v");
						}else if(d==2) {
							sb.append("<");
						}else if(d==3) {
							sb.append("^");
						}
					}else {
						sb.append(map[i][j]);
					}
				}
				sb.append("\n");
			}
			System.out.print(sb);
			
		}
	}

	private static void shoot(char c) {
		if(d==1 || d==3) {
			int sx = x;
			int sy = y + dy[d];
			while(sy>=0 && sy<H) {
				if(map[sy][sx] == '*') {
					map[sy][sx] = '.';
					break;
				}else if(map[sy][sx] == '#') {
					break;
				}
				sy+=dy[d];
			}
		}else {
			int sx = x + dx[d];
			int sy = y;
			while(sx>=0 && sx<W) {
				if(map[sy][sx] == '*') {
					map[sy][sx] = '.';
					break;
				}else if(map[sy][sx] == '#') {
					break;
				}
				sx +=dx[d];
			}
		}
		
	}

	private static boolean isIn(int nx, int ny) {
		return (nx>=0 && nx<W && ny>=0 && ny<H);
	}

	private static void move(char c) {
		switch(c) {
		case 'U':
			d = 3;
			break;
		case 'D':
			d = 1;
			break;
		case 'L':
			d = 2;
			break;
		case 'R':
			d = 0;
			break;
		}
		int nx = x+dx[d];
		int ny = y+dy[d];
		if(isIn(nx, ny) && map[ny][nx]=='.') {
			map[y][x] = '.';
			x = nx;
			y = ny;
		}
	}
}
