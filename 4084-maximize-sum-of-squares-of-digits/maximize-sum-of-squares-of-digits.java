class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if(sum > 9*num || sum < 1){
            return "";
        }

        StringBuilder sb = new StringBuilder();
        int remain = sum % 9;
        int nines = sum / 9;

        for(int i = 0; i < nines; i++){
            sb.append('9');
        }

        if(remain != 0){
            sb.append((char)('0'+ remain));
        }

        while(sb.length() < num){
            sb.append('0');
        }

        return sb.toString();
    }
}