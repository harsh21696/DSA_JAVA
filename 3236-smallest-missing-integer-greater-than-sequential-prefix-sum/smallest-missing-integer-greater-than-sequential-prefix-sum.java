class Solution {
    public int missingInteger(int[] nums) {
        int n = nums.length;
        int sum = nums[0];

        for(int i = 0; i < n - 1; i++){
            if(nums[i + 1] == nums[i] + 1){
                sum += nums[i + 1];
            }
            else {
                break;
            }
        }

        Set<Integer> present = new HashSet<>();
        for(int num : nums){
            present.add(num);
        }

        while(present.contains(sum)){
            sum++;
        }

        return sum;
    }
}