class Solution {
    int[] parent;

    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        parent = new int[n];

        for(int i = 0; i < n; i++){
            parent[i] = i;
        }

        Integer[][] indexedNums = new Integer[n][2];

        for(int i = 0; i < n; i++){
            indexedNums[i][0] = nums[i];
            indexedNums[i][1] = i;
        }

        Arrays.sort(indexedNums, (a, b) -> Integer.compare(a[0], b[0]));

        for(int i = 1; i < n; i++){
            if(indexedNums[i][0] - indexedNums[i - 1][0] <= limit){
                union(indexedNums[i][1], indexedNums[i - 1][1]);
            }
        }

        Map<Integer, List<Integer>> groups = new HashMap<>();

        for(int i = 0; i < n; i++){
            int root = find(i);
            groups.computeIfAbsent(root, k -> new ArrayList<>()).add(i);
        }

        int[] result = new int[n];

        for(List<Integer> indices : groups.values()){
            List<Integer> values = new ArrayList<>();

            for(int idx : indices){
                values.add(nums[idx]);
            }

            Collections.sort(values);

            for(int i = 0; i < indices.size(); i++){
                result[indices.get(i)] = values.get(i);
            }
        }

        return result;
    }

    private int find(int x){
        if(parent[x] != x){
            parent[x] = find(parent[x]);
        }

        return parent[x];
    }

    private void union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);

        if(rootX != rootY){
            parent[rootX] = rootY;
        }
    }
}