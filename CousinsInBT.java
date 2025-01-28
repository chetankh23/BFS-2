
/**
 * Time Complexity: O(N)
 * Space Complexity: O(H)
 */

import java.util.LinkedList;
import java.util.Queue;

public class CousinsInBT {

    TreeNode xParent, yParent;
    int xLevel, yLevel;

    // BFS
    public boolean isCousinsWithBFS(TreeNode root, int x, int y) {
        if (root == null) {
            return false;
        }
        boolean xFound = false;
        boolean yFound = false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                if (current.left != null && current.right != null) {
                    if (current.left.val == x && current.right.val == y) {
                        return false;
                    }
                    if (current.left.val == y && current.right.val == x) {
                        return false;
                    }
                }
                if (current.val == x) {
                    xFound = true;
                }
                if (current.val == y) {
                    yFound = true;
                }
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
            if (xFound == true && yFound == true) {
                return true;
            }
            if (xFound == true || yFound == true) {
                return false;
            }
        }
        return false;
    }

    // DFS
    public boolean isCousinsWithDFS(TreeNode root, int x, int y) {
        if (root == null) {
            return false;
        }
        xParent = null;
        yParent = null;
        xLevel = -1;
        yLevel = -1;
        dfs(root, null, 0, x, y);
        return xParent != yParent && xLevel == yLevel;
    }

    private void dfs(TreeNode root, TreeNode parent, int level, int x, int y) {
        if (root == null || (xLevel != -1 && yLevel != -1)) {
            return;
        }
        if (root.val == x) {
            xParent = parent;
            xLevel = level;
        }
        if (root.val == y) {
            yParent = parent;
            yLevel = level;
        }
        dfs(root.left, root, level + 1, x, y);
        dfs(root.right, root, level + 1, x, y);
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
