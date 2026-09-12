class Solution {
    int[][] arr;
    int n;
    Result[][] dp;

    static class Result{
        long score;
        int[] indices;

        Result(long score, int[] indices){
            this.score = score;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        n = intervals.size();
        arr = new int[n][4];

        for(int i = 0; i < n; i++){
            arr[i][0] = intervals.get(i).get(0);
            arr[i][1] = intervals.get(i).get(1);
            arr[i][2] = intervals.get(i).get(2);
            arr[i][3] = i;
        }

        Arrays.sort(arr, (a, b) -> {
            if(a[0] != b[0]){
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        dp = new Result[n + 1][5];

        return solve(0, 4).indices;
    }

    private Result solve(int i, int k){
        if(i == n || k == 0){
            return new Result(0, new int[0]);
        }

        if(dp[i][k] != null){
            return dp[i][k];
        }

        Result skip = solve(i + 1, k);

        int next = findNext(i);
        Result nextResult = solve(next, k - 1);

        int[] indices = addIndex(nextResult.indices, arr[i][3]);

        Result take = new Result(
            arr[i][2] + nextResult.score,
            indices
        );

        return dp[i][k] = better(take, skip);
    }

    private int[] addIndex(int[] indices, int index) {

        int[] result = new int[indices.length + 1];

        int pos = 0;

        while(pos < indices.length && indices[pos] < index){
            result[pos] = indices[pos];
            pos++;
        }

        result[pos] = index;

        while(pos < indices.length){
            result[pos + 1] = indices[pos];
            pos++;
        }

        return result;
    }

    private int findNext(int i){

        int target = arr[i][1];
        int low = i + 1;
        int high = n;

        while(low < high){
            int mid = low + (high - low)/2;

            if(arr[mid][0] > target){
                high = mid;
            } 
            else{
                low = mid + 1;
            }
        }

        return low;
    }

    private Result better(Result a, Result b){
        if(a.score != b.score){
            return a.score > b.score ? a : b;
        }

        return compare(a.indices, b.indices) < 0 ? a : b;
    }

    private int compare(int[] a, int[] b){
        int len = Math.min(a.length, b.length);

        for(int i = 0; i < len; i++){
            if(a[i] != b[i]){
                return Integer.compare(a[i], b[i]);
            }
        }

        return Integer.compare(a.length, b.length);
    }
}