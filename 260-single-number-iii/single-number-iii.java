class Solution {
    public int[] singleNumber(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int num : nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int[] ans = new int[2];
        int idx = 0;

        for(int num : map.keySet()){
            if(map.get(num) == 1){
                ans[idx] = num;
                idx++;
            }
        }

        return ans;
    }
}