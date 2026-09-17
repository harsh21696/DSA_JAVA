class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] pre = new int[n];
        int left = 0;
        int sum = 0;
        int best = Integer.MAX_VALUE;

        for(int right = 0; right < n; right++){
            sum += arr[right];

            while(sum > target && left <= right){
                sum -= arr[left++];
            }

            if(sum == target){
                best = Math.min(best, right-left+1);
                sum -= arr[left++];
            }

            pre[right] = best;
        }

        left = 0; 
        sum = 0;

        int ans = Integer.MAX_VALUE;

        for(int right = 0; right < n; right++){
            sum += arr[right];

            while(sum > target && left <= right){
                sum -= arr[left++];
            }

            if(sum == target){
                int len = right-left+1;
                if(left > 0 && pre[left - 1] != Integer.MAX_VALUE){
                    ans = Math.min(ans, len + pre[left - 1]);
                }
                sum -= arr[left++];
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}