class Fancy {
    private static final int M = 1_000_000_007;
    private List<Long> seq;
    private long mult;
    private long add;

    public Fancy() {
        seq = new ArrayList<>();
        mult = 1;
        add = 0;
    }

    public void append(int val) {
        long x = ((val - add) % M + M) % M;
        x = (x * power(mult, M - 2)) % M;
        seq.add(x);
    }

    public void addAll(int inc) {
        add = (add + inc) % M;
    }

    public void multAll(int m) {
        mult = (mult * m) % M;
        add = (add * m) % M;
    }

    public int getIndex(int idx) {
        if(idx >= seq.size()) {
            return -1;
        }

        long val = (seq.get(idx) * mult + add) % M;
        return (int) val;
    }

    private long power(long base, long exp) {
        base %= M;
        long result = 1;

        while(exp > 0){
            if((exp & 1) == 1){
                result = (result * base) % M;
            }

            base = (base * base) % M;
            exp >>= 1;
        }

        return result;
    }
}