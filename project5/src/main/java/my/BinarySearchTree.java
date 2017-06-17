package my;

/**
 * Created by nm on 12.6.17.
 */
public class BinarySearchTree<Key extends Comparable<Key>, Value> {
    private class Node {
        public Key key;
        public Value value;
        public Node left;
        public Node right;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }
    //===================================//

    private Node root;

    public BinarySearchTree() {
        this.root = null;
    }

    public void put(Key k, Value v){
        root = put(root, k, v);
    }

    private Node put(Node x, Key k, Value v) {
        if (x == null)
            return new Node(k, v);

        int cmp = k.compareTo(x.key);
        if      (cmp < 0)   x.left = put(x.left, k, v);
        else if (cmp > 0)   x.right = put(x.right, k, v);
        else                x.value = v;

        return x;
    }

    public Value get(Key k){
        Node x = root;
        while (x != null){
            int cmp = k.compareTo(root.key);
            if      (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else           return x.value;
        }
        return null;
    }

    public void delete(Key k){

    }

    public Key floor(Key k){
        Node current = root;
        Key greatestLesser = null;
        while (current != null){
            int cmp = k.compareTo(current.key);
            if      (cmp < 0)    current = current.left;
            else if (cmp > 0)    {
                greatestLesser = current.key;
                current = current.right;
            } else {
                return current.key;
            }
        }
        return null;

    }
}
