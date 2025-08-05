class Solution {
    public int solution(int[] mats, String[][] park) {
        int answer = -1;
        
        //park에서 이동하면서 "-1"인 위치 찾기
        for(int y=0;y<park.length;y++){
            for(int x=0;x<park[0].length;x++){
                if(park[y][x].equals("-1")){
                    //mats의 값 n에 대하여 nxn 크기의 공간이 있는지 확인
                    for(int n : mats){
                    //만약 확인하는 공간이 park의 크기를 벗어나면 종료
                    //가장 큰 값을 찾아야 하기 때문에 answer에 넣을 때 값 비교하고 넣기
                        if(y+n-1<park.length && x+n-1<park[0].length){
                            boolean check = true;
                            for(int i=y;i<y+n;i++){
                                if(!check){
                                    break;
                                }
                                for(int j=x;j<x+n;j++){
                                    if(!park[i][j].equals("-1")){
                                        check = false;
                                        break;
                                    }else{
                                        if(i == y+n-1 && j== x+n-1){
                                            answer = (answer>n?answer:n);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return answer;
    }
}