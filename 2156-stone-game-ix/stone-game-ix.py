class Solution:
    def stoneGameIX(self, stones):
        cnt0 = cnt1 = cnt2 = 0

        for val in stones:
            rem = val % 3

            if rem == 0:
                cnt0 += 1
            elif rem == 1:
                cnt1 += 1
            else:
                cnt2 += 1

        if cnt0 % 2 == 0:
            return cnt1 > 0 and cnt2 > 0

        return abs(cnt1 - cnt2) > 2