import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Solution{
	static String numbers = "0123456789";
	static String alphabet = "ABCDEF";

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			String inputN = br.readLine();
			
			Deque<Character> deque = new ArrayDeque<>();
			for(int i=0;i<N;i++) {
				deque.offer(inputN.charAt(i));
			}
			
			int numLength = N/4;
			
			List<Integer> convertedNums = new ArrayList<>();
			//총 3번 회전
			for(int i=0;i<numLength;i++) {
				char temp = deque.pollLast();
				deque.offerFirst(temp);
				List<String> parseNums = new ArrayList<>();

				//각 회전에서 4개의 숫자를 추출
				for(int j=0;j<4;j++) {
					String parseNum = "";
					for(int index = 0; index<numLength;index++) {
						char c = deque.pollFirst();
						parseNum+=c;
						deque.offerLast(c);
								
					}
					parseNums.add(parseNum);
				}
				
				//4개의 16진수를 int형으로 변환하여 list에 넣기
				for(int j=0;j<4;j++) {
					String str = parseNums.get(j);
					int n = 0;
					for(int k=0;k<numLength;k++) {
						int tempN = 0;
						if(numbers.contains(Character.toString(str.charAt(k)))) tempN =str.charAt(k)-'0';
						else if(alphabet.contains(Character.toString(str.charAt(k)))) tempN = str.charAt(k)-'A'+10;
						n+=(tempN)*Math.pow(16, (numLength-k-1));
					}
					convertedNums.add(n);
				}
			}
			//중복 제거 후 K번째 숫자 출력
			convertedNums.sort(Comparator.reverseOrder());
			for(int i=convertedNums.size()-1;i>0;i--) {
				if(convertedNums.get(i).equals(convertedNums.get(i-1))) convertedNums.remove(i);
			}
			System.out.println("#"+t+" "+convertedNums.get(K-1));
		}
 
	}

}
