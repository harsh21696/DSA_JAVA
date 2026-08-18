class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        int maxNum = -1;

        for(int num : nums){
            maxNum = Math.max(maxNum, num);
        }
        
        int[] freq = new int[maxNum + 1];
        for(int num : nums){
            freq[num]++;
        }
   
        if(k == n){
            return maxNum;
        }
        
        if(k == 1){
            int maxMiss = -1;

            for(int i = 0; i <= maxNum; i++){
                if(freq[i] == 1){
                    maxMiss= Math.max(maxMiss, i);
                }
            }

            return maxMiss;
        }
        
        int maxMiss = -1;

        if(freq[nums[0]] == 1){
            maxMiss = Math.max(maxMiss, nums[0]);
        }

        if(freq[nums[n - 1]] == 1){
            maxMiss = Math.max(maxMiss, nums[n - 1]);
        }
        
        return maxMiss;
    }
}