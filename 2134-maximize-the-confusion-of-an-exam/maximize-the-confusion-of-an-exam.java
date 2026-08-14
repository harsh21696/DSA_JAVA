class Solution {
    public int maxConsecutiveAnswers(String answerKey, int k) {
        int res1 = solve(answerKey, k, 'F');
        int res2 = solve(answerKey, k, 'T');
        return Math.max(res1, res2);
    }

    private int solve(String s, int k, char ch){
        int left = 0;
        int count = 0;
        int maxLen = 0;

        for(int right = 0; right < s.length(); right++){
            if(s.charAt(right) == ch){
                count++;
            }

            while(count > k){
                if(s.charAt(left) == ch){
                    count--;
                }
                left++;
            }

            int len = right - left + 1;
            maxLen = Math.max(maxLen, len);
        }

        return maxLen;
    }
}