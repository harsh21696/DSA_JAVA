class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int[] sufMin = new int[n];
        sufMin[n-1] = nums[n-1];
        
        for(int i = n-2; i >= 0; i--){
            sufMin[i] = Math.min(nums[i], sufMin[i+1]);
        }
        
        int prefMax = nums[0];
        for(int i = 0; i < n; i++){
            prefMax = Math.max(prefMax, nums[i]);
            if((long) prefMax - sufMin[i] <= k){
                return i;
            }
        }
        
        return -1;
    }
}