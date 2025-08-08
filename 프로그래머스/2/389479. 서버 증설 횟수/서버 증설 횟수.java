class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        //m : 1서버 감당 인원
        //k : 서버 유지 시간
        //증설 된 서버가 유지 시간을 알고 있어야 함
        //각 서버의 증설 시간을 저장할 방법 -> 1차원 배열에 저장
        // 기능 나누기
        // 1. 현재 이용자에 따른 필요한 서버 수 구하기
        // 2. 현재 운영중인 서버 수와 그에 따른 추가 서버 수 구하기
        int[] servers = new int[24];  //각 시간 별 운영 중인 서버 수 저장
        
        for(int i=0; i<servers.length;i++){
            int needServers = players[i]/m;  //현재 필요한 서버 수
            if(needServers>servers[i]){
                int add = needServers - servers[i];
                answer+=add;
                for(int j=0;j<k;j++){
                    if(i+j>=24) break;
                    else{
                        servers[i+j]+=add;
                    }
                }
                    
            }
        }
        
        return answer;
    }
}