import java.util.NoSuchElementException;

/**
 * This class implements the immutable tree interface.
 *
 * @author Matt Smith
 *
 */
public class Tree implements ImmutableSortedSet {

    final TreeNode root;

    /**
     * This constructs an empty tree.
     */
    public Tree() {
        root = null;
    }

    /**
     * This constructs a tree with a given root.
     */
    public Tree(TreeNode newRoot) {
        root = newRoot;
    }

    @Override
    public ImmutableSortedSet add(String key) {
        if (this.contains(key)) {
            return this;
        }

        return new Tree(add(this.root, key));
    }

    /**
     * This method adds a new node with a given key to the tree. If that key already
     * exists, the original tree is returned.
     */
    private static TreeNode add(TreeNode root, String key) {
        if (root == null) {
            return new TreeNode(key, null, null);
        }

        if (key != null && (root.data == null || 0 < key.compareTo(root.data))) {
            // Key is greater than data
            return root.changeRight(add(root.right, key));
        }

        if (key == null || 0 > key.compareTo(root.data)) {
            // Key is less than data
            return root.changeLeft(add(root.left, key));
        }

        return root;
    }

    @Override
    public ImmutableSortedSet remove(String key) {
        // TODO Auto-generated method stub
        return this;
    }

    @Override
    public String getAtIndex(int index) throws NoSuchElementException {
        // TODO Auto-generated method stub

        throw new NoSuchElementException();

        // return null;
    }

    @Override
    public int getIndex(String key) throws NoSuchElementException {
        // TODO Auto-generated method stub

        throw new NoSuchElementException();

        // return 0;
    }

    @Override
    public boolean contains(String key) {
        // TODO Auto-generated method stub

        return contains(key, this.root);
    }

    /**
     * This method checks if a given key is in the tree.
     */
    private static boolean contains(String key, TreeNode root) {
        if (root == null) {
            return false;
        }

        if (key == root.data) {
            return true;
        }

        return contains(key, root.left) || contains(key, root.right);
    }

    @Override
    public int size() {
        // Returns the number of keys in the tree
        return size(this.root);
    }

    /**
     * This method finds the size of the tree.
     */
    private static int size(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return size(root.left) + size(root.right) + 1;
    }

    @Override
    public String[] keys() {
        // TODO Auto-generated method stub
        String[] array = new String[] {};

        return keys(this.root, array);
    }

    /**
     * Adds keys to a string array in order from least to greatest.
     */
    private String[] keys(TreeNode root, String[] array) {
        if (root == null) {
            return array;
        }

        array = keys(root.left, array);
        String[] temp = new String[array.length + 1];
        for (int i = 0; i < array.length; i++) {
            temp[i] = array[i];
        }
        temp[temp.length - 1] = root.data;
        array = temp;

        if (array[array.length - 1] != root.data) {
            array = keys(root.right, array);
            temp = new String[array.length + 1];
            for (int i = 0; i < array.length; i++) {
                temp[i] = array[i];
            }
            temp[temp.length - 1] = root.data;
            array = temp;
        }

        return array;
    }

}
