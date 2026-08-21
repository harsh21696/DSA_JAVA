class Solution {
    long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    long lcm(long a, long b, long limit) {
        long g = gcd(a, b);
        if (a / g > limit / b) {
            return limit + 1;
        }
        return a / g * b;
    }

    long count(long x, int[] coins) {
        int n = coins.length;
        long ans = 0;
        for (int mask = 1; mask < (1 << n); mask++) {
            long multiple = 1;
            int bits = 0;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    bits++;
                    multiple = lcm(multiple, coins[i], x);
                    if (multiple > x) {
                        break;
                    }
                }
            }
            if (multiple > x) {
                continue;
            }
            long add = x / multiple;
            if (bits % 2 == 1) {
                ans += add;
            } else {
                ans -= add;
            }
        }
        return ans;
    }

    public long findKthSmallest(int[] coins, int k) {
        long left = 1;
        long right = (long) coins[0] * k;
        for (int coin : coins) {
            right = Math.min(right, (long) coin * k);
        }
        while (left < right) {
            long mid = left + (right - left) / 2;
            if (count(mid, coins) >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}