import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static boolean[] visited = new boolean[26];
	static int result = 0;
	static String[] words; 
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		words = new String[N];
		
		for(int i=0;i<N;i++) {
			words[i] = br.readLine();
		}
		//a,n,t,i,c 는 무조건 필요함.
		if(K < 5) {
			System.out.println(0);
			return;
		}
		
		int minCount = Integer.MAX_VALUE;
		Set<Character> set = new HashSet<>();
		for(int w = 0;w<N;w++) {
			String word = words[w];
			set = new HashSet<>();
			for(int i=0;i<word.length();i++) {
				set.add(word.charAt(i));
			}
			StringBuilder newWord = new StringBuilder();
			for(char c : set) {
				newWord.append(c);
			}
			words[w] = newWord.toString();
			minCount = Math.min(minCount, words[w].length());
		}
		
		//가장 글자가 적게 필요한 단어보다 K가 작은 경우
		if(minCount>K) {
			System.out.println(0);
			return;
		}
		visited['a'-'a'] = true;
		visited['n'-'a'] = true;
		visited['t'-'a'] = true;
		visited['i'-'a'] = true;
		visited['c'-'a'] = true;
		
		dfs(0,0,K-5);
		System.out.println(result);
		
		
	}
	static void dfs(int start, int count, int end) {
		if(count == end) {
			int readableCount = 0;
			for(String word:words) {
				boolean canRead = true;
				for(int i=0;i<word.length();i++) {
					if(!visited[word.charAt(i)-'a']) {
						canRead = false;
						break;
					}
				}
				if(canRead) readableCount++;
			}
			result = Math.max(readableCount, result);
			return;
		}
		for(int i=start+1 ; i<26 ; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			dfs(i,count+1,end);
			visited[i] = false;
		}
	}

}
