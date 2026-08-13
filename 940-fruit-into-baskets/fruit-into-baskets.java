class Solution {
    public int totalFruit(int[] fruits) {
        int n = fruits.length;
        HashMap<Integer, Integer> map  = new HashMap<>();
        int left = 0;
        int maxCount = 0;
        int len = 0;

        for(int right = 0; right < n; right++){
           if(map.containsKey(fruits[right])){
            map.put(fruits[right], map.get(fruits[right]) + 1);
           }
           else{
            map.put(fruits[right], 1);
           }

           while(map.size() > 2){
            map.put(fruits[left], map.get(fruits[left]) - 1);

            if(map.get(fruits[left]) == 0){
                map.remove(fruits[left]);
            }

            left++;
           }

           len = right - left + 1;
           maxCount = Math.max(maxCount, len);
        }

        return maxCount;
    }
}