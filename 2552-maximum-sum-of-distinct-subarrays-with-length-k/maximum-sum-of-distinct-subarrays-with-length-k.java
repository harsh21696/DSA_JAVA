class Solution {
    public long maximumSubarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int low = 0;
        long sum = 0;
        long maxSum = 0;
        int len;

        for(int high = 0; high < nums.length; high++){
            map.put(nums[high], map.getOrDefault(nums[high], 0) + 1);
            sum += nums[high];
            len = high - low + 1;

            if(len > k){
                map.put(nums[low], map.get(nums[low]) - 1);
                sum -= nums[low];

                if(map.get(nums[low]) == 0){
                    map.remove(nums[low]);
                }

                low++;
            }
            len = high - low + 1;

            if(len == k && map.size() == k){
                maxSum = Math.max(maxSum, sum);
            }
        }

        return maxSum;
    }
}