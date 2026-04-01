import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] grid = new int[C];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<C;i++){
            grid[i] = Integer.parseInt(st.nextToken());
        }

        int result = 0;

        int[] leftTop = new int[C];
        int[] rightTop = new int[C];

        leftTop[0] = grid[0];
        for(int i=1;i<C;i++){
            leftTop[i] = Math.max(leftTop[i-1], grid[i]);
        }
        rightTop[C-1] = grid[C-1];
        for(int i=C-2;i>=0;i--){
            rightTop[i] = Math.max(rightTop[i+1], grid[i]);
        }
        for(int i=0;i<C;i++){
            int height = Math.min(leftTop[i], rightTop[i]);
            if(height>grid[i]) result+=height-grid[i];
        }

        System.out.println(result);
    }
}
