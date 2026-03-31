import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static int len, K;
	static int result1, result2;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			result1 = 0;
			result2 = 0;
			String input = br.readLine();
			K = Integer.parseInt(br.readLine());
			//알파벳이 나타나는 위치를 저장하고 그 위치를 가져와서 검사하자.
			
			List<Integer>[] pos = new ArrayList[26];
			for(int i=0;i<26;i++) pos[i] = new ArrayList<>(); 
			
			for(int i=0;i<input.length();i++) {
				pos[input.charAt(i)-'a'].add(i);
			}
			
			int minLength = Integer.MAX_VALUE;
			int maxLength = 0;
			
			for(int i=0;i<26;i++) {
				if(pos[i].size() < K) continue;
				
				for(int j=0;j<=pos[i].size() - K;j++) {
					int length = pos[i].get(j+K-1) - pos[i].get(j) + 1;
					minLength = Math.min(minLength, length);
					maxLength = Math.max(maxLength, length);
				}
			}
			
			if(minLength == Integer.MAX_VALUE) {
				System.out.println(-1);
			}else {
				System.out.println(minLength+" "+maxLength);
			}
			
			
			//============================================================//
			//아래 방식은 모든 윈도우를 검사하므로 시간 초과 발생..
			
			//윈도우 사이즈를 줄이면서? -> 모든 알파벳이 K 개 이하일 때 문자열의 길이.
			//만약 윈도우 사이즈가 K보다 작으면  -1로 리턴
			
			// 특정 문자를 정확히 K개 가지고, 가장 짧은 연속 문자열 길이 구하기
				//1. 윈도우 사이즈를 K부터 input.length() 까지 늘리면서, 윈도우를 이동시키며 특정 문자가 K를 가지는지 검사.
				//만약 K개를 넘기면 중간에 false 반환. 
				//윈도우 전체 탐색 후 max가 K랑 같으면 true 반환, 아니며  false 반환
			
			
			// 특정 문자를 정확히 K개 가지고, 문자열의 첫 번째와 마지막 글자가 해당 문자로 같은 가장 긴 문자열의 길이
				//0. 초기 문자와 마지막 문자가 같은지 검사, 다르면 continue
				//1. 윈도우 사이즈를 큰 것부터 시작하여 K개로 줄이면서, 윈도우를 이동시키며 특정 문자가 K개를 가지는지 검사.
				//만약 K개를 넘기면 중간에 false 반환.
		}
	}
}
