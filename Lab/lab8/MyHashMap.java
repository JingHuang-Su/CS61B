import java.net.http.HttpClient;
import java.util.*;

public class MyHashMap<K, V> implements Map61B<K, V> {

    private static final int fixed_capacity = 16;
    private static final double fixed_loadFactor = 0.75;
    private int m; //num of key-value pair
    private double hashsize;
    private int n; //hash table size
    private HTNode<K, V>[] nodetable;
    private HTNode list;

    private class HTNode<K, V>{
        private Node first;
        private int n;
        private class Node {
            /**
             * Stores KEY as the key in this key-value pair, VAL as the value, and
             * NEXT as the next node in the linked list.
             */
            private K key;
            private V value;
            private Node next;


            private Node(K k, V v, Node n) {
                this.value = v;
                this.key = k;
                this.next = n;
            }
        }

        private HTNode() {

        }

        private int size() {
            return n;
        }

        private boolean isEmpty() {
            return size() == 0;
        }

        private boolean contains(K key) {
            return get(key) != null;
        }

        public V get(K key) {
            for (Node x = first; x != null; x = x.next) {
                if (key.equals(x.key)) {
                    return x.value;
                }
            }
            return null;
        }

        private void put(K key, V val) {

            for (Node x = first; x != null; x = x.next) {
                if (key.equals(x.key)) {
                    x.value = val;
                    return;
                }
            }
            first = new Node(key, val, first);
            n += 1;
        }

        private Node delete(Node x, K key) {
            if (x == null) {
                return null;
            }
            if (key.equals(x.key)) {
                n -= 1;
                return x.next;
            }
            x.next = delete(x.next, key);
            return x;
        }

        public Iterable<K> keys(){
            Queue<K> queue = new LinkedList<>();
            for (Node x = first; x != null; x = x.next){
                queue.offer(x.key);
            }
            return queue;
        }



    }



    public MyHashMap(){
        this(fixed_capacity);
    }


    public MyHashMap(int initialSize) {
        this.m = initialSize;
        nodetable = (HTNode<K, V>[])new HTNode[m];

        for (int i = 0; i < m; i+=1)
            nodetable[i] = new HTNode<K, V>() ;
    }

    //@SuppressWarnings("unchecked")
    public MyHashMap(int initialSize, double loadFactor){
        this.m = initialSize;
        this.hashsize = loadFactor;

        nodetable = (HTNode<K, V>[])new HTNode[m];
        for (int i = 0; i < m; i+=1)
            nodetable[i] = new HTNode<K, V>() ;
    }

    private void resize(int chains) {
        MyHashMap<K, V> temp = new MyHashMap<>(chains);
        for (int i = 0; i < m; i+=1) {
            for (K key : nodetable[i].keys()) {
                temp.put(key, nodetable[i].get(key));
            }
        }
        this.m  = temp.m;
        this.n  = temp.n;
        this.nodetable = temp.nodetable;
    }

    public int hash(K key){
        return (key.hashCode() & 0x7fffffff) % m;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        return get(key) != null;
    }

    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to get() is null");
        }
        int i = hash(key);
        return nodetable[i].get(key);
    }

    @Override
    public void clear() {
        nodetable = (HTNode<K, V>[])new HTNode[fixed_capacity];
        for (int i = 0; i < fixed_capacity - 1; i+=1)
            nodetable[i] = new HTNode<K, V>() ;
        m = 0;
        n = 0;
    }

    public void put(K key, V val) {
        if (key == null) {
            throw new IllegalArgumentException("first argument to put() is null");
        }


        // double table size if average length of list >= 10
        if (n >= 10*m) {
            resize(2*m);
        }

        int i = hash(key);
        if (!nodetable[i].contains(key)) {
            n++;
        }
        nodetable[i].put(key, val);
    }
    /*
    public void delete(K key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to delete() is null");
        }

        int i = hash(key);
        if (nodetable[i].contains(key)){
            n--;
        }
        nodetable[i].delete(key);

    }
    */
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


}
