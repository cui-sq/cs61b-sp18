public class LinkedListDeque<T> {
    private class TNode{
        public T item;
        public TNode prev;
        public TNode next;

        public TNode(T i, TNode p, TNode n){
            item = i;
            prev = p;
            next = n;
        }
    }
    /** The first node is at sentinel.next
     * The last node is at sentinel.prev.
     */
    private TNode sentinel;
    private int size;
    public LinkedListDeque(){
        sentinel = new TNode( (T) "null", null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }


    /**Adds an item of type T to the front of the deque. */
    public void addFirst(T item){
        size += 1;
        sentinel.next.prev= new TNode(item, sentinel, sentinel.next);
        //sentinel.next.prev = temp;
        sentinel.next = sentinel.next.prev;
    }

    public void addLast(T item){
        size += 1;
        sentinel.prev.next = new TNode(item, sentinel.prev, sentinel);
        sentinel.prev = sentinel.prev.next;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    /** Prints the items in the deque from first to last,
     * separated by a space. */
    public void printDeque(){
        TNode p = sentinel.next;
        for(int count = 0; count < size; count += 1){
            System.out.print(p.item + " ");
            p = p.next;
        }
    }

    /** Removes and returns the item at the front of the deque
     * If no such item exists, returns null.
     */
    public T removeFirst(){
        if(isEmpty()) {
            return null;
        }
        size -= 1;
        T res = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        return res;
    }

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */
    public T removeLast(){
        if(isEmpty()) {
            return null;
        }
        size -= 1;
        T res = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        return res;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     */
    public T get(int index){
        if((index < 0) || (index > size - 1)) {
            return null;
        }
        TNode p = sentinel.next;
        int count = 0;
        while(count < index){
            p = p.next;
            count += 1;
        }
        return p.item;
    }

    /** Recursion get. */
    private T getRecursiveHelper(int index, TNode p){
        if(index == 0) {
            return p.item;
        }
        return getRecursiveHelper(index - 1, p.next);
    }
    public T getRecursive(int index){
        if((index < 0) || (index > size - 1)) {
            return null;
        }
        return getRecursiveHelper(index, sentinel.next);
    }

}
