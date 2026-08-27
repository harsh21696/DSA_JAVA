class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int[] count = new int[26];
        for(char c : s.toCharArray()){
            count[c-'a']++;
        }
        
        char[] res = new char[target.length()];
        if(solve(count, target, 0, false, res)){
            return new String(res);
        }
        return "";
    }

    private boolean solve(int[] count, String target, int i, boolean greater, char[] res) {
        if(i == target.length()){
            return greater;
        }

        for(char ch = 'a'; ch <= 'z'; ch++){
            if(count[ch - 'a'] == 0){
                continue;
            }

            if(!greater && ch < target.charAt(i)){
                continue;
            }

            if(greater){
                res[i] = ch;
                count[ch-'a']--;
                if(solve(count, target, i + 1, true, res)){
                    return true;
                }

                count[ch-'a']++;
                continue;
            }

            if(ch == target.charAt(i) || ch > target.charAt(i)){
                boolean newGreater = greater || (ch > target.charAt(i));
                res[i] = ch;
                count[ch-'a']--;
                
                if(solve(count, target, i + 1, newGreater, res)){
                    return true;
                }
                
                count[ch-'a']++;
            }
        }

        return false;
    }
}