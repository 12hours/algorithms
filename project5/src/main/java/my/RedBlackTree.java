package my;

/**
 * Created by nm on 15.6.17.
 */
public class RedBlackTree<Key extends Comparable<Key>, Value> {
    private static boolean RED = true;
    private static boolean BLACK = false;

    private class Node {
        public Key key;
        public Node left;
        public Node right;
        public Value val;
        boolean color;

        public Node(Key key, Value val, boolean color) {
            this.key = key;
            this.val = val;
            this.color = color;
        }
    }

    private Node root;

    public RedBlackTree() {
        this.root = null;
    }

    public Value get(Key k) {
        Node node = root;
        while (node != null) {
            int cmp = k.compareTo(node.key);
            if      (cmp < 0) node = node.left;
            else if (cmp > 0) node = node.right;
            else return node.val;
        }
        return null;
    }

    private boolean isRed(Node node){
        if (node == null)   return false;
        return node.color == RED;
    }

    private Node rotateLeft(Node h){
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private Node rotateRight(Node h){
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private void flipColors(Node h){
        h.left.color = BLACK;
        h.right.color = BLACK;
        h.color = RED;
    }

    public Node put(Node h, Key key, Value val){
        if (h == null)  return new Node(key, val, RED);

        int cmp = key.compareTo(h.key);
        if      (cmp < 0) h.left = put(h.left, key, val);
        else if (cmp > 0) h.right = put(h.right, key, val);
        else              h.val = val;

        if (isRed(h.right) && !isRed(h.left))   h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))    flipColors(h);

        return h;
    }
}
