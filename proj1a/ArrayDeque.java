import java.util.Arrays;

public class ArrayDeque<Item> {
    /* Invariants:
     size: the number of items.
     start: The first item is stored in position start.
     The xth item in the list is at
        position (start + x) % items.length in the array.
     addFirst: The next item we want to add will be at position
        start if size == 0;
        (items.length - 1) if start == 0 ;
        start - 1 if start != 0;
     addLast: The next item we want to add will be at position
        (start + size) % items.length

     */
    private Item[] items;
    /** size: number of items. */
    private int size;
    /** The first item is stored in position start. */
    private int start;


    /** Creates an empty list. */
    public ArrayDeque() {
        items = (Item[]) new Object[8];
        size = 0;
        start = 0;
    }

    private double usageRatio(){
        return (double) size / items.length;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    /** Help calculate the position of addFirst method for non-empty array list.
     * Returns the new start*/
    /*private int addFirstIndex(){
        if(start == 0) {
            return (items.length - 1);
        }
        return start - 1;
    }*/
    public void addFirst(Item x) {
        if (!isEmpty()) {
            if (size == items.length) {
                resize(items.length * 2);
            }
            start = getPosition(-1);
        }
        items[start] = x;
        size += 1;
    }

    public void addLast(Item x) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[getPosition(size)] = x;
        size += 1;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Item x = get(0);
        items[start] = null;
        start = getPosition(1);
        size -= 1;
        if (usageRatio() < 0.25) {
            resize(items.length / 2);
        }
        return x;
    }

    public Item removeLast() {
        if (isEmpty()) {
            return null;
        }
        Item x = get(size - 1);
        items[size - 1] = null;
        size -= 1;
        if (usageRatio() < 0.25) {
            resize(items.length / 2);
        }
        return x;
    }

    /** Returns the position in the array corresponding to
     * the index in the list.
     * @return position
     */
    private int getPosition(int index) {
        return Math.floorMod((start + index), items.length);
    }

    public Item get(int index) {
        return items[getPosition(index)];
    }

    public int size() {
        return size;
    }

    /** Resizes the underlying array to the target capacity. */
    private void resize(int capacity) {
        Item[] a = (Item[]) new Object[capacity];
        for(int i = 0; i < size; i++){
            a[i] = get(i);
        }
        start = 0;
        items = a;
    }

}
