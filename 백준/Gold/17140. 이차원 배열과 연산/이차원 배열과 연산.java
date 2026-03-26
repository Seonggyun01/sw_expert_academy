import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int r, c, k;
    static int[][] A = new int[101][101];
    static int rowSize = 3, colSize = 3;

    static class Node implements Comparable<Node> {
        int num, count;

        public Node(int num, int count) {
            this.num = num;
            this.count = count;
        }

        @Override
        public int compareTo(Node o) {
            if (this.count == o.count) {
                return Integer.compare(this.num, o.num);
            }
            return Integer.compare(this.count, o.count);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 3; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solve());
    }

    static int solve() {
        for (int time = 0; time <= 100; time++) {
            // 목표 숫자가 k인지 확인 (인덱스 범위 체크 포함)
            if (A[r][c] == k) return time;

            if (rowSize >= colSize) {
                rowOperation();
            } else {
                colOperation();
            }
        }
        return -1;
    }

    // R 연산: 행 단위 정렬
    static void rowOperation() {
        int maxCol = 0;
        for (int i = 1; i <= rowSize; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = 1; j <= colSize; j++) {
                if (A[i][j] == 0) continue;
                map.put(A[i][j], map.getOrDefault(A[i][j], 0) + 1);
                A[i][j] = 0; // 정렬 전 해당 칸 비우기
            }

            List<Node> list = new ArrayList<>();
            for (int key : map.keySet()) {
                list.add(new Node(key, map.get(key)));
            }
            Collections.sort(list);

            int index = 1;
            for (Node node : list) {
                if (index > 100) break;
                A[i][index++] = node.num;
                A[i][index++] = node.count;
            }
            // 정렬 후 남은 칸들을 0으로 채우기 (이미 위에서 0으로 밀었으므로 인덱스만 갱신)
            maxCol = Math.max(maxCol, index - 1);
        }
        colSize = maxCol;
    }

    // C 연산: 열 단위 정렬
    static void colOperation() {
        int maxRow = 0;
        for (int j = 1; j <= colSize; j++) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 1; i <= rowSize; i++) {
                if (A[i][j] == 0) continue;
                map.put(A[i][j], map.getOrDefault(A[i][j], 0) + 1);
                A[i][j] = 0; // 정렬 전 해당 칸 비우기
            }

            List<Node> list = new ArrayList<>();
            for (int key : map.keySet()) {
                list.add(new Node(key, map.get(key)));
            }
            Collections.sort(list);

            int index = 1;
            for (Node node : list) {
                if (index > 100) break;
                A[index++][j] = node.num;
                A[index++][j] = node.count;
            }
            maxRow = Math.max(maxRow, index - 1);
        }
        rowSize = maxRow;
    }
}