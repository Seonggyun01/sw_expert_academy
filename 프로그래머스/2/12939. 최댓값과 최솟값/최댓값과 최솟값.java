import java.util.*;

class Solution {
    public String solution(String s) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        StringTokenizer st = new StringTokenizer(s);
        int size = st.countTokens();
        for(int i=0;i<size;i++){
            int num = Integer.parseInt(st.nextToken());
            max = Math.max(num, max);
            min = Math.min(num, min);
        }
        
        String answer = min + " " + max;
        return answer;
    }
}