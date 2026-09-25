class Solution {
    int i = 0;
    String s;

    public List<String> braceExpansionII(String expression) {
        this.s = expression;
        this.i = 0;
        TreeSet<String> res = parseUnion();
        return new ArrayList<>(res);
    }

    private TreeSet<String> parseUnion() {
        TreeSet<String> set = parseConcat();
        while (i < s.length() && s.charAt(i) == ',') {
            i++;
            set.addAll(parseConcat());
        }
        return set;
    }

    private TreeSet<String> parseConcat() {
        TreeSet<String> res = new TreeSet<>();
        res.add("");
        while (i < s.length() && s.charAt(i) != '}' && s.charAt(i) != ',') {
            TreeSet<String> factor = parseFactor();
            TreeSet<String> next = new TreeSet<>();
            for (String a : res) {
                for (String b : factor) {
                    next.add(a + b);
                }
            }
            res = next;
        }
        return res;
    }

    private TreeSet<String> parseFactor() {
        if (s.charAt(i) == '{') {
            i++;
            TreeSet<String> inner = parseUnion();
            i++;
            return inner;
        } else {
            i++;
            TreeSet<String> single = new TreeSet<>();
            single.add(String.valueOf(s.charAt(i - 1)));
            return single;
        }
    }
}