import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine();
		StringBuilder sb = new StringBuilder();
		while(!"end".equals(input)) {
			boolean isValid = true;
			boolean dot = false;
			int Xcount = 0;
			int Ocount = 0;
			char[][] map = new char[3][3];
			int index = 0;
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					map[i][j] = input.charAt(index++);  
					if(map[i][j]== '.') {
						dot = true;
					}else if(map[i][j]== 'X'){
						Xcount++;
					}else if(map[i][j]== 'O'){
						Ocount++;
					}
				}
			}
			
			//1. O가 X보다 2개 더 많은 경우 invalid
			if(Xcount<Ocount || Xcount > Ocount+1) {
				isValid = false;
			}
			//누가 승리했는지 확인하기
			int[] win = countLine(map);
			if(isValid && win[0]>0 && win[1]>0) {
				isValid = false;
			}
			//X가 이겼을 때는 Owin이 0이어야 한다.
			if(win[0]>0) {
				if(Xcount != Ocount+1) {
					isValid = false;
				}
			}
			if(win[1]>0) {
				if(Xcount != Ocount) {
					isValid = false;
				}
			}
			if(win[0] == 0 && win[1]==0) {
				if(dot) {
					isValid = false;
				}
			}
			
			if(isValid) {
				sb.append("valid").append("\n");
			}else if(!isValid) {
				sb.append("invalid").append("\n");
			}
			
			input = br.readLine();
		}
		System.out.println(sb);
		
		
	}
	static int[] countLine(char[][] map) {
		int Ocount = 0;
		int Xcount = 0;
		//가로 체크
		for(int i=0;i<3;i++) {
			char curr = map[i][0];
			for(int j=0;j<3;j++) {
				if(curr != map[i][j]) break;
				if(j==2 && curr=='O') {
					Ocount++;
				}else if(j==2 && curr=='X') {
					Xcount++;
				}
			}
		}
		//세로 체크
		for(int i=0;i<3;i++) {
			char curr = map[0][i];
			for(int j=0;j<3;j++) {
				if(curr != map[j][i]) break;
				if(j==2 && curr=='O') {
					Ocount++;
				}else if(j==2 && curr=='X') {
					Xcount++;
				}
			}
		}
		//대각선 체크
		if(map[0][0] == map[1][1] && map[1][1] == map[2][2]) {
			if(map[0][0]=='O') {
				Ocount++;
			}else if(map[0][0]=='X') {
				Xcount++;
			}
		}
		if(map[0][2] == map[1][1] && map[1][1] == map[2][0]) {
			if(map[0][2]=='O') {
				Ocount++;
			}else if(map[0][2]=='X') {
				Xcount++;
			}
		}
		
		return new int[] {Xcount, Ocount};
		
	}

}
