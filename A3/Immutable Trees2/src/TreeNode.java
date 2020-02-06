/**
 * This is the node class for the immutable tree.
 * All fields in this class are final to
 *
 * @author Matt Smith
 *
 */
public class TreeNode {

    // These may need to be private
    final int size;
    final String data;
    final TreeNode right;
    final TreeNode left;

    /**
     * This is the constructor for tree node. Because all
     * fields are final, the fields must be given during creation.
     *
     * Calculates the size.
     */
    public TreeNode(String newData, TreeNode newRight, TreeNode newLeft) {
        data = newData;
        right = newRight;
        left = newLeft;
        size = calculateSize(this);
    }

    /**
     * This method calculates the size of the node given.
     */
    private int calculateSize(TreeNode root) {
        // Size = Size of left tree plus size of right tree plus 1
        if (root == null) {
            return 0;
        }

        int left = calculateSize(root.left);
        int right = calculateSize(root.right);

        return right + left + 1;
    }

    /**
     * This method creates a new right child instead of setting.
     */
    public TreeNode changeRight(TreeNode newRight) {
        return new TreeNode(this.data, newRight, this.left);
    }

    /**
     * This method creates a new left child instead of setting.
     */
    public TreeNode changeLeft(TreeNode newLeft) {
        return new TreeNode(this.data, this.right, newLeft);
    }

}
