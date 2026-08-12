class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int left = 0;
        int maxLength = 0;

        for(int right = 0; right < nums.length; right++){
            if(map.containsKey(nums[right])){
                map.put(nums[right], map.get(nums[right]) + 1);
            } 
            else{
                map.put(nums[right], 1);
            }

            while(map.get(nums[right]) > k){
                map.put(nums[left], map.get(nums[left]) - 1);
                left++;
            }

            int length = right - left + 1;
            maxLength = Math.max(maxLength, length);
        }

        return maxLength;
    }
}