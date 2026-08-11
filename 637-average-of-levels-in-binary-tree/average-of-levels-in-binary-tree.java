/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        List<Long> sum = new ArrayList<>();
        List<Integer> count = new ArrayList<>();

        if(root == null){
            return res;
        }

        solve(root, 0, sum, count);

        for(int i = 0; i < sum.size(); i++){
            res.add((double) sum.get(i) / count.get(i));
        }

        return res;
    }

    private void solve(TreeNode root, int level, List<Long> sum, List<Integer> count){
        if(root == null){
            return;
        }

        if(sum.size() == level){
            sum.add(0L);
            count.add(0);
        }

        sum.set(level, sum.get(level) + root.val);
        count.set(level, count.get(level) + 1);

        solve(root.left, level + 1, sum, count);
        solve(root.right, level + 1, sum, count);
    }
}