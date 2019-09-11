import java.util.List;

public class LinkedListDeque<Item>{
    public class SomeNode {
        private Item item;
        private SomeNode next;
        private SomeNode prev;

        public SomeNode(SomeNode p, Item i, SomeNode n) {
            prev = p;
            item = i;
            next = n;
        }
    }
    private int size;
    private SomeNode sentinel;

    public boolean isEmpty(){
        return size == 0;
    }

    public LinkedListDeque(){
        sentinel = new SomeNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(Item x){
        /** Adds an item of type T to the front of the deque */
        SomeNode helper = new SomeNode(sentinel, x , sentinel.next);
        helper.next.prev = helper;
        helper.prev.next = helper;
        size += 1;
    }

    public void addLast(Item x){
        /**Adds an item of type T to the back of the deque.*/
        SomeNode helper = new SomeNode(sentinel.prev, x, sentinel);
        helper.next.prev = helper;
        helper.prev.next = helper;
        size += 1;
    }

    public Item removeFirst(){
        /** Removes and returns the item at the front of the deque.
         * If no such item exists, returns null.*/
        Item temp = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return temp;
    }

    public Item removeLast(){
        /**Removes and returns the item at the back of the deque.
         * If no such item exists, returns null.*/
        Item temp = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return temp;
    }

    public int size(){
        /**Returns the number of items in the deque.*/
        return size;
    }

    public Item get(int Index){
        /**Gets the item at the given index, where 0 is the front,
         * 1 is the next item, and so forth. If no such item exists, returns null.
         * Must not alter the deque!*/
        if (isEmpty() || (size - 1) < Index) {
            return null;
        }

        SomeNode temp = sentinel;
        while (Index >= 0) {
            Index -= 1;
            temp = temp.next;
        }

        return temp.item;
    }

    public void printDeque(){
        /** Prints the items in the deque from first to last,
         *  separated by a space.*/
        while (sentinel.next.item != null) {
            System.out.print(sentinel.next.item + " ");
            sentinel = sentinel.next;
        }
    }

    public static void main(String[] args) {
        LinkedListDeque x = new LinkedListDeque();
        x.addFirst(5);
        x.addFirst(6);
        x.addFirst(7);
        x.addLast(8);
        x.size();
        x.removeFirst();
        x.removeLast();
        x.printDeque();
    }
}
