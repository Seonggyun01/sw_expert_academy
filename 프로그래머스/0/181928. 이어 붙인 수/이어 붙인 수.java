class Solution {
    public int solution(int[] num_list) {
        int answer = 0;
        int oddNum = 0;
        int evenNum = 0;
        int odd =0;
        int even =0;
        for(int i=num_list.length-1;i>=0;i--){
            int n = num_list[i];
            if(n%2==1){
                for(int j=0;j<odd;j++){
                    n*=10;
                }
                oddNum+=n;
                odd++;
            }else{
                for(int j=0;j<even;j++){
                    n*=10;
                }
                evenNum+=n;
                even++;
            }
        }
        return answer=oddNum+evenNum;
    }
}