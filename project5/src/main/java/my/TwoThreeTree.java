package my;

/**
 * Created by nm on 14.6.17.
 */
public class TwoThreeTree<Key extends Comparable<Key>, Value> {
    private class Node {
        Node left;
        Node middle;
        Node right;
        Key[] key;
        Value[] val;

        public Node() {
            key = (Key[]) new Object[3];
            val = (Value[]) new Object[3];
        }

    }

    private Node root;

    public void insert(Key k, Value v){
        root = insert(root, k, v);
    }

    private Node insert(Node node, Key k, Value v) {
        if (node == null){
            Node newNode = new Node();

            return newNode;
        }

        return node;
    }

    public Value search(Key k){

        return null;
    }

    public void delete(Key k){

    }
}
