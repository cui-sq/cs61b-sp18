public class ArrayDeque<T> implements Deque<T> {
    /* Invariants:
     size: the number of items.
     start: The first item is stored in position start.
     items.length should be at least 8.
     The xth item in the list is at
        position (start + x) % items.length in the array.
     addFirst: The next item we want to add will be at position
        start if size == 0;
        (items.length - 1) if start == 0 ;
        start - 1 if start != 0;
     addLast: The next item we want to add will be at position
        (start + size) % items.length

     */
    private T[] items;
    /** size: number of items. */
    private int size;
    /** The first item is stored in position start. */
    private int start;


    /** Creates an empty list. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        start = 0;
    }

    private double usageRatio() {
        return (double) size / items.length;
    }

    @Override
    public boolean isEmpty() {
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
    @Override
    public void addFirst(T x) {
        if (!isEmpty()) {
            if (size == items.length) {
                resize(items.length * 2);
            }
            start = getPosition(-1);
        }
        items[start] = x;
        size += 1;
    }

    @Override
    public void addLast(T x) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[getPosition(size)] = x;
        size += 1;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T x = get(0);
        items[getPosition(0)] = null;
        start = getPosition(1);
        size -= 1;
        if (size > 8 && (usageRatio() < 0.25)) {
            resize(items.length / 2);
        }
        return x;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T x = get(size - 1);
        items[getPosition(size - 1)] = null;
        size -= 1;
        if (size > 8 && (usageRatio() < 0.25)) {
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

    @Override
    public T get(int index) {
        if (isEmpty()) {
            return null;
        }
        return items[getPosition(index)];
    }

    @Override
    public int size() {
        return size;
    }

    /** Resizes the underlying array to the target capacity. */
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            a[i] = get(i);
        }
        start = 0;
        items = a;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
    }

}
