class Solution {
    public int maximumUniqueSubarray(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        int left = 0;
        int sum = 0;
        int maxScore = 0;

        for(int i = 0; i < n; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);

            sum += nums[i];

            while(map.get(nums[i]) > 1){
                map.put(nums[left], map.get(nums[left]) - 1);
                sum -= nums[left];

                if(map.get(nums[left]) == 0){
                    map.remove(nums[left]);
                }

                left++;
            }

            maxScore = Math.max(maxScore, sum);

        }

        return maxScore;
    }
}