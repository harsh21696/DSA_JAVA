class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        String best = "";

        for(int len = k; len <= n; len++){
            String currentBest = "";

            for(int start = 0; start <= n - len; start++){
                String temp = s.substring(start, start + len);

                int ones = 0;
                for(int i = 0; i < temp.length(); i++){
                    if(temp.charAt(i) == '1'){
                        ones++;
                    }
                }

                if(ones == k){
                    if (currentBest.isEmpty() || temp.compareTo(currentBest) < 0) {
                        currentBest = temp;
                    }
                }
            }

            if(!currentBest.isEmpty()){
                return currentBest;
            }
        }

        return "";
    }
}