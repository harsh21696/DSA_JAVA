class Solution {
    public int reverseDegree(String s) {
        int total = 0;
        int a = s.length();
        
        for (int i = 0; i < a; i++) {
            char c = s.charAt(i);
            int rev = 26-(c-'a');
            int pos = i+1;
            total += rev * pos;
        }
        
        return total;
    }
}