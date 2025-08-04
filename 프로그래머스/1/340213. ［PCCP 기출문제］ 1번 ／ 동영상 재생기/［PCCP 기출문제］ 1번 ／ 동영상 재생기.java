class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        /*
        10초 전 이동 : prev 명령 입력 할 경우 현재 위치에서 10초 전으로 이동, 현재 위치가 10초 미만일 경우 처음으로 이동
        10초 후 이동 : next 명령 입력 할 경우 현재 위치에서 10초 후로 이동, 남은 시간이 10초 미만일 경우 마지막으로 이동
        오프닝 건너뛰기 : 현재 재생 위치가 op_start와 op_end 사이인 경우 op_end로 이동
        
        String video_len : 동영상 길이 문자열 "00:00"
        String pos : 기능이 수행되기 직전의 재생위치 문자열 "00:00"
        String op_start : 오프닝 시작 위치 문자열 "00:00"
        String op_end : 오프닝 끝나는 위치 문자열 "00:00"
        String[] commands : 사용자의 입력을 나타내는 문자열 배열
        String result : 입력이 모두 끝난 후 동영상의 위치 문자열 "00:00"
        */
        String answer = pos;
        
        for(String s : commands){
            answer = skip(answer, op_start ,op_end);
            if(s.equals("next")){
                answer = next(answer, video_len);
            }
            if(s.equals("prev")){
                answer = prev(answer);
            }
            answer = skip(answer, op_start ,op_end);
        }
        
        
        return answer;
    }
    //next 기능
    public String next(String pos, String video_len){
        int min = Integer.parseInt(pos.substring(0,2));
        int sec = Integer.parseInt(pos.substring(3));
        int minMax = Integer.parseInt(video_len.substring(0,2));
        int secMax = Integer.parseInt(video_len.substring(3));
        sec += 10;
        if(sec>=60){
            sec-=60;
            min++;
        }
        if(min>=minMax&&sec>=secMax){
            min = minMax;
            sec = secMax;
        }
        String resultMin = String.valueOf(min);
        if(min<10){
            resultMin = ""+0+min;
        }
        String resultSec = String.valueOf(sec);
        if(sec<10){
            resultSec = ""+0+sec;
        }
        return resultMin+":"+resultSec;
        
    }
    //prev 기능
    public String prev(String pos){
        int min = Integer.parseInt(pos.substring(0,2));
        int sec = Integer.parseInt(pos.substring(3));
        sec -= 10;
        if(min>0 && sec<0){
            sec+=60;
            min--;
        }
        if(min <= 0&&sec<=0){
            min = 0;
            sec = 0;
        }
       String resultMin = String.valueOf(min);
        if(min<10){
            resultMin = ""+0+min;
        }
        String resultSec = String.valueOf(sec);
        if(sec<10){
            resultSec = ""+0+sec;
        }
        return resultMin+":"+resultSec;
    }
    
    //오프닝 건너뛰기 기능
    public String skip(String pos, String op_start , String op_end){
        int posSec = Integer.parseInt(pos.substring(0,2)+pos.substring(3));
        int opStartSec = Integer.parseInt(op_start.substring(0,2)+op_start.substring(3));
        int opEndSec = Integer.parseInt(op_end.substring(0,2)+op_end.substring(3));
        if(opStartSec<=posSec && posSec<=opEndSec){
            posSec = opEndSec;
        }
        int min = posSec/100;
        int sec = posSec%100;
        String resultMin = String.valueOf(min);
        if(min<10){
            resultMin = ""+0+min;
        }
        String resultSec = String.valueOf(sec);
        if(sec<10){
            resultSec = ""+0+sec;
        }
        return resultMin+":"+resultSec;
        
    }
}