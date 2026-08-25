class Solution {
    public int missingMultiple(int[] nums, int k) {
        int temp = k;

        while(true){
            int i;
            for(i = 0; i < nums.length; i++){
                if(nums[i] == temp){
                    break;
                }
            }

            if(i == nums.length){
                return temp;
            }

            temp += k;
        }
    }
}