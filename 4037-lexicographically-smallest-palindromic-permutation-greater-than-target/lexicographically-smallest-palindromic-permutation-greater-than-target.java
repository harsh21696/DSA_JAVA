class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        int oddCount = 0;
        char midChar = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                oddCount++;
                midChar = (char) ('a' + i);
            }
        }
        
        if ((n % 2 == 0 && oddCount != 0) || (n % 2 != 0 && oddCount != 1)) {
            return "";
        }

        int[] halfCount = new int[26];
        for (int i = 0; i < 26; i++) {
            halfCount[i] = count[i] / 2;
        }

        char[] res = new char[n];
        int halfLen = n / 2;
        
        if (buildHalf(halfCount, target, 0, false, res, midChar, n, halfLen)) {
            return new String(res);
        }

        return "";
    }

    private boolean buildHalf(int[] count, String target, int i, boolean greater, char[] res, char midChar, int n, int halfLen) {
        if (i == halfLen) {
            if (n % 2 == 1) {
                res[halfLen] = midChar;
            }
            
            for (int j = 0; j < halfLen; j++) {
                res[n - 1 - j] = res[j];
            }

            String candidate = new String(res);
            return candidate.compareTo(target) > 0;
        }

        for (char ch = 'a'; ch <= 'z'; ch++) {
            if (count[ch - 'a'] == 0) continue;

            if (!greater && ch < target.charAt(i)) {
                continue;
            }

            res[i] = ch;
            count[ch - 'a']--;

            boolean newGreater = greater || (ch > target.charAt(i));

            if (buildHalf(count, target, i + 1, newGreater, res, midChar, n, halfLen)) {
                return true;
            }

            count[ch - 'a']++;
        }

        return false;
    }
}