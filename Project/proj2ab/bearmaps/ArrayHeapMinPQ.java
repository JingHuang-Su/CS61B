package bearmaps;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.NoSuchElementException;
import java.security.Key;
import java.util.HashMap;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {

    private int n; //number of item on ArrayHeapMinPQ
    private KeyNode[] pq; //store items at indices 1 to n
    private HashMap<T, Integer> hash;

    private class KeyNode implements Comparable<KeyNode> {
        private T item;
        private double priority;

        private KeyNode(T e, double p) {
            this.item = e;
            this.priority = p;
        }

        private T getItem() {
            return item;
        }

        private double getPriority() {
            return priority;
        }

        private void setPriority(double priority) {
            this.priority = priority;
        }

        @Override
        public int compareTo(KeyNode other) {
            if (other == null) {
                return -1;
            }
            return Double.compare( this.getPriority(), other.getPriority());
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (o == null || o.getClass() != this.getClass()) {
                return false;
            } else {
                return ((KeyNode) o).getItem().equals(getItem());
            }
        }

        @Override
        public int hashCode() {
            return item.hashCode();
        }
    }




    @SuppressWarnings("unchecked")
    public ArrayHeapMinPQ(int initCapacity) {
        pq = new ArrayHeapMinPQ.KeyNode[initCapacity];
        n = 0;
        pq[0] = null;
        hash = new HashMap<>();
    }

    public ArrayHeapMinPQ(){
       this(1);
    }

    @Override
    public boolean contains(T item){
        return hash.containsKey(item);
    }


    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        assert capacity > n;
        KeyNode[] temp =  new ArrayHeapMinPQ.KeyNode[capacity];
        for (int i = 1; i <= n; i++) {
            temp[i] = pq[i];
        }

        pq = temp;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void add(T item, double priority){
        if (n == pq.length - 1) {
            resize(pq.length * 2);
        }
        n += 1;
        pq[n] = new KeyNode(item, priority);
        swin(n);
        hash.put(item, n);
    }

    private void swin(int k){
        while (k > 1 && greater(k/2, k)){
            exch(k, k/2);
            k = k/2;
        }

    }

    private void exch(int i, int j){
        KeyNode node1 = pq[i];
        KeyNode node2 = pq[j];
        pq[i] = node2;
        pq[j] = node1;
        hash.put(node1.item, j);
        hash.put(node2.item, i);
    }

    private boolean greater(int i, int j) {
        return pq[i].compareTo(pq[j]) > 0;
    }

    public T getSmallest(){
        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue underflow");
        } else {
            return pq[1].item;
        }
    }

    public T removeSmallest(){
        if (isEmpty()){
            throw new NoSuchElementException("Priority queue underflow");
        }

        T min = pq[1].item;
        n -= 1;
        exch(1, n);
        sink(1);
        hash.remove(min);
        pq[n+1]= null;
        if (n > 0 && (n == (pq.length-1)/4)){
            resize(pq.length/2);
        }
        return min;
    }

    private void sink(int k) {
        while(2 * k <= n){
            int j = 2 * k;

            if (j < n && greater(j, j+1)){
                j += 1;
            }
            if (!greater(k, j)){
                break;
            }
            exch(k, j);
            k = j;
        }
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public void changePriority(T item, double priority){
        if (isEmpty()){
            throw new NoSuchElementException("Priority queue underflow");
        }

        int i = getIndex(item);
        KeyNode temp = pq[i];
        temp.priority = priority;
        pq[i] = temp;
        sink(i);
        swin(i);
    }

    private int getIndex(T item){
        return hash.get(item);
    }

}
