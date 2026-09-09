class Solution {
    public long countCommas(long n) {
        long total = 0;

        for(int i = 1; ; i++){
            long low = pow10(3*i);

            if(low > n){
                break;
            }

            total += n - low + 1;
        }

        return total;
    }

    private long pow10(int e) {
        long res = 1;

        for(int i = 0; i < e; i++){
            res *= 10;
        }

        return res;
    }
}