class Solution {
    private static final Map<Integer, int[]> DIGIT_FACTORS = new HashMap<>();
    static {
        DIGIT_FACTORS.put(0, new int[]{0, 0, 0, 0});
        DIGIT_FACTORS.put(1, new int[]{0, 0, 0, 0});
        DIGIT_FACTORS.put(2, new int[]{1, 0, 0, 0});
        DIGIT_FACTORS.put(3, new int[]{0, 1, 0, 0});
        DIGIT_FACTORS.put(4, new int[]{2, 0, 0, 0});
        DIGIT_FACTORS.put(5, new int[]{0, 0, 1, 0});
        DIGIT_FACTORS.put(6, new int[]{1, 1, 0, 0});
        DIGIT_FACTORS.put(7, new int[]{0, 0, 0, 1});
        DIGIT_FACTORS.put(8, new int[]{3, 0, 0, 0});
        DIGIT_FACTORS.put(9, new int[]{0, 2, 0, 0});
    }

    private int[] primeCountOfT(long t) {
        int[] c = new int[4];
        int[] primes = {2, 3, 5, 7};
        for (int i = 0; i < 4; i++) {
            while (t % primes[i] == 0) {
                t /= primes[i];
                c[i]++;
            }
        }
        return (t == 1) ? c : null;
    }

    private int[] primeCountOfNum(String num) {
        int[] c = new int[4];
        for (char ch : num.toCharArray()) {
            int[] f = DIGIT_FACTORS.get(ch - '0');
            for (int i = 0; i < 4; i++) c[i] += f[i];
        }
        return c;
    }

    private int[] sub(int[] a, int[] b) {
        int[] r = new int[4];
        for (int i = 0; i < 4; i++) r[i] = Math.max(0, a[i] - b[i]);
        return r;
    }

    private boolean covers(int[] a, int[] b) {
        for (int i = 0; i < 4; i++) if (a[i] < b[i]) return false;
        return true;
    }

    private int[] minDigitsFor(int[] req) {
        int two = req[0], three = req[1], five = req[2], seven = req[3];
        int c8 = two / 3, r2 = two % 3;
        int c9 = three / 2, c3 = three % 2;
        int c4 = r2 / 2, c2 = r2 % 2;
        int c6 = 0;
        if (c2 == 1 && c3 == 1) {
            c2 = 0; c3 = 0; c6 = 1;
        }
        if (c3 == 1 && c4 == 1) {
            c2 = 1; c6 = 1; c3 = 0; c4 = 0;
        }
        int[] digitCounts = new int[10];
        digitCounts[2] = c2;
        digitCounts[3] = c3;
        digitCounts[4] = c4;
        digitCounts[5] = five;
        digitCounts[6] = c6;
        digitCounts[7] = seven;
        digitCounts[8] = c8;
        digitCounts[9] = c9;
        return digitCounts;
    }

    private int sumCounts(int[] digitCounts) {
        int s = 0;
        for (int d = 2; d <= 9; d++) s += digitCounts[d];
        return s;
    }

    private String build(int[] digitCounts) {
        StringBuilder sb = new StringBuilder();
        for (int d = 2; d <= 9; d++)
            for (int k = 0; k < digitCounts[d]; k++) sb.append((char) ('0' + d));
        return sb.toString();
    }

    public String smallestNumber(String num, long t) {
        int[] req = primeCountOfT(t);
        if (req == null) return "-1";

        int[] base = minDigitsFor(req);
        int baseLen = sumCounts(base);
        if (baseLen > num.length()) {
            return build(base);
        }

        int[] prefixCount = primeCountOfNum(num);
        int firstZero = num.indexOf('0');
        if (firstZero == -1) {
            firstZero = num.length();
            if (covers(prefixCount, req)) return num;
        }

        int[] running = prefixCount.clone();
        for (int i = num.length() - 1; i >= 0; i--) {
            int d = num.charAt(i) - '0';
            running = sub(running, DIGIT_FACTORS.get(d));
            int spaceAfter = num.length() - 1 - i;
            if (i > firstZero) continue;

            for (int bigger = d + 1; bigger <= 9; bigger++) {
                int[] remainingReq = sub(sub(req, running), DIGIT_FACTORS.get(bigger));
                int[] need = minDigitsFor(remainingReq);
                int needLen = sumCounts(need);
                if (needLen <= spaceAfter) {
                    int fillOnes = spaceAfter - needLen;
                    return num.substring(0, i) + bigger + "1".repeat(fillOnes) + build(need);
                }
            }
        }

        int[] need = minDigitsFor(req);
        int ones = num.length() + 1 - sumCounts(need);
        return "1".repeat(ones) + build(need);
    }
}