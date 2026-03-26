import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main{
    static int r,c,k;
    static int[][] A;

    static class Node implements Comparable<Node>{
        int num, count;
        public Node(int num, int count) {
            this.num = num;
            this.count = count;
        }

        @Override
        public int compareTo(Node o) {
            int result;
            if(this.count == o.count){
                result = Integer.compare(this.num, o.num);
            }else{
                result = Integer.compare(this.count, o.count);
            }
            return result;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken())-1;
        c = Integer.parseInt(st.nextToken())-1;
        k = Integer.parseInt(st.nextToken());
        int currR = 3;
        int currC = 3;

        A = new int[100][100];
        for(int i=0;i<3;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<3;j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int turn = 0;
        while(true){
            //기저 조건
            if(turn > 100){
                turn = -1;
                break;
            }
            else if(r<currR && c<currC && A[r][c] == k){
                break;
            }

            //currR >= currC인 경우
            if(currR >= currC){

                int maxLength = 0;
                for(int r=0;r<currR;r++){
                    ArrayList<Node> list = new ArrayList<>();
                    for(int c=0;c<currC;c++){
                        if(A[r][c] == 0)continue;
                        boolean existed = false;
                        for(Node node : list){
                            if(node.num == A[r][c]){
                                node.count++;
                                existed = true;
                                break;
                            }
                        }
                        if(!existed){
                            list.add(new Node(A[r][c], 1));
                        }
                    }
                    list.sort(null);

                    int index = 0;
                    for(Node node : list){
                        A[r][index++] = node.num;
                        A[r][index++] = node.count;
                        if(index == 100){
                            break;
                        }
                    }
                    for(int c=index; c<100;c++){
                        A[r][c] = 0;
                    }

                    maxLength = Math.max(maxLength, index);
                }
                currC = maxLength;
            }
            else if(currR < currC){
                int maxLength = 0;
                for(int c=0;c<currC;c++){
                    ArrayList<Node> list = new ArrayList<>();
                    for(int r=0;r<currR;r++){
                        if(A[r][c] == 0) continue;
                        boolean existed = false;
                        for(Node node : list){
                            if(node.num == A[r][c]){
                                node.count++;
                                existed = true;
                                break;
                            }
                        }
                        if(!existed){
                            list.add(new Node(A[r][c],1));
                        }
                    }
                    list.sort(null);
                    int index = 0;
                    for(Node node : list){
                        A[index++][c] = node.num;
                        A[index++][c] = node.count;
                        if(index == 100){
                            break;
                        }
                    }
                    for(int r=index;r<100;r++){
                        A[r][c] = 0;
                    }

                    maxLength = Math.max(maxLength, index);
                }
                currR = maxLength;
            }
            turn++;

        }
        System.out.println(turn);
    }

}
