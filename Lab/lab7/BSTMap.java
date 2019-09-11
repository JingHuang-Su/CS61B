import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    private Node root;             // root of BST

    private class Node {
        private K key;           // sorted by key
        private V val;         // associated data
        private Node left, right;  // left and right subtrees
        private int size;          // number of nodes in subtree

        public Node(K key, V val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    public void clear(){
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("argument ot contain() is null");
        }
        return get(key) != null ;
    }

    /** Returns the value corresponding to KEY or null if no such value exists. */
    public V get(K key) {
        return get(root, key);
    }

    private V get(Node x, K key) {
        if (key == null) {
            throw new IllegalArgumentException("calls get() with a null key");
        }
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);

        if (cmp > 0) {
            return get(x.right, key);
        } else if (cmp < 0) {
            return get(x.left, key);
        } else {
            return x.val;
        }
    }

    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node put(Node x, K key, V value){
        if (x == null){
            return new Node(key, value, 1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp > 0) {
            x.right = put(x.right, key, value);
        } else if(cmp < 0){
            x.left =  put(x.left, key, value);
        } else {
            x.val = value;
        }

        x.size = 1 + size(x.right) + size( x.left);
        return x;
    }


    @Override
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        } else {
            return x.size;
        }
    }
    
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    public void printInOrder(){
        printInOrder(root);
    }

    public void printInOrder(Node x){
        if(x.left == null && x.right == null){
            printNode(x);
        } else if (x.right == null){
            printInOrder(x.left);
            printNode(x);
        } else if (x.left == null){
            printNode(x);
            printInOrder(x.right);
        } else {
            printInOrder(x.left);
            printNode(x);
            printInOrder(x.right);
        }
    }
    private void printNode(Node x){
        System.out.println(x.key);
        System.out.println(" " + x.val);
    }

}

