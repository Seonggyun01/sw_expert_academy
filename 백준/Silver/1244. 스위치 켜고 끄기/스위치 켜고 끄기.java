import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main{
    static class Student{
        int sex, num;

        public Student(int sex, int num) {
            this.sex = sex;
            this.num = num;
        }
    }
    static int N, M;
    static int[] switches;

    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());  //스위치 개수
        switches = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            switches[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());  //학생 수
        List<Student> students = new ArrayList<>();
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            students.add(new Student(s,n));
        }

        for(int i=0;i<M;i++){
            if(students.get(i).sex == 1){
                change1(students.get(i).num);
            }else {
                change2(students.get(i).num);
            }
        }

        for(int i=0;i<N;i++){
            if(i != 0 && i % 20 == 0){
                System.out.println();
            }
            System.out.print(switches[i]+" ");
        }

    }
    //여학생은 자기 숫자 기준으로 좌우 대칭 범위를 찾고, 그 범위의 스위치를 모두 변경
    private static void change2(int num) {
        int left = num-1;
        int right = num-1;
        while(true){
            int nextL = left-1;
            int nextR = right+1;
            if(nextL < 0 ||  nextR > N-1){
                break;
            }
            if(switches[nextL] == switches[nextR]) {
                --left;
                ++right;
            }else{
                break;
            }
        }
        for(int i=left;i<=right;i++){
            switches[i] = switches[i] == 0 ? 1 : 0;
        }
    }

    //남학생은 자기가 받은 수의 배수를 변경
    private static void change1(int num) {
        for(int i=num-1;i<N;i+=num){
            switches[i] = switches[i] == 0 ? 1 : 0;
        }
    }
}
