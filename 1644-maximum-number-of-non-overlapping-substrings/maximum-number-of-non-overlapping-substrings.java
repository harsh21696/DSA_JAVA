class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, Integer.MAX_VALUE);
        Arrays.fill(last, -1);
        
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            first[idx] = Math.min(first[idx], i);
            last[idx] = Math.max(last[idx], i);
        }
        
        Set<String> seen = new HashSet<>();
        List<int[]> intervals = new ArrayList<>();
        
        for (int i = 0; i < 26; i++) {
            if (first[i] == Integer.MAX_VALUE) continue;
            
            int l = first[i];
            int r = last[i];
            
            boolean changed = true;
            while (changed) {
                changed = false;
                for (int k = l; k <= r; k++) {
                    int c = s.charAt(k) - 'a';
                    if (first[c] < l || last[c] > r) {
                        l = Math.min(l, first[c]);
                        r = Math.max(r, last[c]);
                        changed = true;
                    }
                }
            }
            
            String key = l + ":" + r;
            if (!seen.contains(key)) {
                seen.add(key);
                intervals.add(new int[]{l, r});
            }
        }
        
        intervals.sort((a, b) -> {
            if (a[1] != b[1]) return a[1] - b[1];
            return a[0] - b[0];
        });
        
        List<String> result = new ArrayList<>();
        int lastEnd = -1;
        
        for (int[] interval : intervals) {
            if (interval[0] > lastEnd) {
                result.add(s.substring(interval[0], interval[1] + 1));
                lastEnd = interval[1];
            }
        }
        
        return result;
    }
}