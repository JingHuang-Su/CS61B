public class ArrayDeque<Item> implements Deque<Item>{
    private int size;
    private Item[] items;
    private int nextFirst;
    private int nextLast;
    private static final int FIXED_LENGTH = 8;
    private static final int REACTOR = 2;
    private static final double MIN_USAGE_RATIO = 0.25;

    private boolean alreadyboom() {
        return size == items.length;
    }
    private boolean IsEmpty() {
        return size == 0;
    }

    public ArrayDeque() {
        items = (Item[]) new Object[FIXED_LENGTH];
        nextFirst = (items.length - size) / 2;
        nextLast = nextFirst + 1;
        size = 0;
    }

    private int minusOne(int x) {
        if (x == 0) {
            return items.length - 1;
        }
        return x - 1;
    }

    private void NeedResize() {
        if ((double) size / items.length < MIN_USAGE_RATIO) {
            resizeDown(items.length / 2);
        }
    }

    private void resizeDown(int capacity) {
        Item[] a = (Item[]) new Object[capacity];
        if (capacity < FIXED_LENGTH) {
            return;
        }
        if (nextFirst + 1 + size > items.length) {
            int left = (nextFirst + 1 + size) - items.length;
            int tempLength = items.length - nextFirst + 1;
            System.arraycopy(items, nextFirst + 1, a, (a.length / 2) - 1, tempLength);
            System.arraycopy(items, nextLast - 1, a, ((a.length / 2) - 1) + tempLength, left);
        } else {
            System.arraycopy(items, nextFirst + 1, a, (a.length / 2) - 1, size);
        }
        items = a;
        nextFirst = minusOne((a.length / 2) - 1);
        nextLast = plusOne(nextFirst + size);

    }

    private void resize(int capacity) {
        Item[] a = (Item[]) new Object[capacity];
        int tempLength = items.length - nextFirst + 1;
        System.arraycopy(items, nextFirst + 1, a, (a.length / 2), tempLength);
        if (tempLength != items.length) {
            System.arraycopy(items, 0, a, ((a.length / 2) - 1) + tempLength, nextFirst + 1);
        }
        items = a;
        nextFirst = minusOne((a.length / 2) - 1);
        nextLast = plusOne(nextFirst + size);
    }

    private int plusOne(int x) {
        if (x == items.length - 1) {
            return 0;
        }
        return x + 1;
    }
    @Override
    public void addFirst(Item x) {
        if (alreadyboom()) {
            resize(size * REACTOR);
        }
        items[nextFirst] = x;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }
    @Override
    public void addLast(Item x) {
        if (alreadyboom()) {
            resize(size * REACTOR);
        }
        items[nextLast] = x;
        nextLast = plusOne(nextLast);
        size += 1;
    }
    @Override
    public Item removeFirst() {
        if (IsEmpty()) {
            return null;
        }
        Item temp = items[nextFirst + 1];
        items[nextFirst + 1] = null;
        nextFirst = plusOne(nextFirst);
        size -= 1;
        NeedResize();
        return temp;
    }
    @Override
    public Item removeLast() {
        if (IsEmpty()) {
            return null;
        }
        Item temp = items[nextLast - 1];
        items[nextLast - 1] = null;
        nextLast = minusOne(nextLast);
        size -= 1;
        NeedResize();
        return temp;
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public Item get(int i) {
        if (i >= size) {
            return null;
        } else if (nextFirst + 1 + i < items.length) {
            return items[nextFirst + 1 + i];
        } else {
            return items[nextFirst + 1 + i - items.length];
        }
    }
    @Override
    public void printDeque() {
        for (int i = 0; i < items.length; i += 1) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }
}
