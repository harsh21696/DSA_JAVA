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
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();

        if(root == null){
            return res;
        }

        solve(root, 0, res);
        Collections.reverse(res);
        return res;  
    }

    private void solve(TreeNode root, int level, List<List<Integer>> res){ 
        if(root == null){
            return;
        }

        if(res.size() == level){
            res.add(new ArrayList<>());
        }

        res.get(level).add(root.val);
        solve(root.left, level + 1, res);
        solve(root.right, level + 1, res);
    }
}