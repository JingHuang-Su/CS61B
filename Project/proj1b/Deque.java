public interface Deque<Item> {

    public void addFirst(Item x);

    public void addLast(Item x);

    public Item removeFirst();

    public Item removeLast();

    public int size();

    public Item get(int Index);

    public void printDeque();
}
