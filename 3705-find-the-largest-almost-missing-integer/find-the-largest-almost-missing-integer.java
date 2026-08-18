class Solution {
    public int largestInteger(int[] nums, int k) {
        // This maps a number to the total number of size-k subarrays it appears in
        Map<Integer, Integer> subarrayCount = new HashMap<>();
        
        // This map maintains the frequency of numbers in the current sliding window
        Map<Integer, Integer> windowMap = new HashMap<>();
        
        // 1. Initialize the first window of size k
        for (int i = 0; i < k; i++) {
            windowMap.put(nums[i], windowMap.getOrDefault(nums[i], 0) + 1);
        }
        
        // Record all unique numbers in this first window
        for (int num : windowMap.keySet()) {
            subarrayCount.put(num, subarrayCount.getOrDefault(num, 0) + 1);
        }
        
        // 2. Push the window to the right by one element at a time
        for (int i = k; i < nums.length; i++) {
            // Add the new element entering the window from the right
            windowMap.put(nums[i], windowMap.getOrDefault(nums[i], 0) + 1);
            
            // Remove the old element leaving the window from the left
            int leftElement = nums[i - k];
            windowMap.put(leftElement, windowMap.get(leftElement) - 1);
            
            // If the frequency drops to 0, completely remove it from the window map
            if (windowMap.get(leftElement) == 0) {
                windowMap.remove(leftElement);
            }
            
            // Record all unique numbers present in this new current window
            for (int num : windowMap.keySet()) {
                subarrayCount.put(num, subarrayCount.getOrDefault(num, 0) + 1);
            }
        }
        
        // 3. Find the maximum number that appears in exactly one subarray
        int maxAlmostMissing = -1;
        for (Map.Entry<Integer, Integer> entry : subarrayCount.entrySet()) {
            if (entry.getValue() == 1) {
                maxAlmostMissing = Math.max(maxAlmostMissing, entry.getKey());
            }
        }
        
        return maxAlmostMissing;
    }
}