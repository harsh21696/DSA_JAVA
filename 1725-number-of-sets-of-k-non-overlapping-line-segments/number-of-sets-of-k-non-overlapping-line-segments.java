class Solution {
    private static final long MOD = 1_000_000_007L;

    public int numberOfSets(int n, int k){
        int N = n + k - 1;
        int K = 2 * k;

        if(K > N){
            return 0;
        }

        long[] fact = new long[N + 1];
        fact[0] = 1;

        for(int i = 1; i <= N; i++){
            fact[i] = fact[i - 1] * i % MOD;
        }

        long denom = fact[K] * fact[N - K] % MOD;
        return (int)(fact[N] * pow(denom, MOD - 2) % MOD);
    }

    private long pow(long base, long exp){
        long res = 1;
        base %= MOD;

        while(exp > 0){
            if((exp & 1) == 1) res = res * base % MOD;
            base = base * base % MOD;
            exp >>= 1;
        }

        return res;
    }
}