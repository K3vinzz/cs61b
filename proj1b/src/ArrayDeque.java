import java.util.ArrayList;
import java.util.List;

public class ArrayDeque<T> implements Deque<T> {
    /** invariants:
    addLast: The next Item we want to add, will go into position size.
    getLast: The item we want to return is in position size - 1.
    size: The number of items in the list should be size.
    The first item in ArrayList is in position nextFirst + 1
    get: The index we want is in position nextFirst + 1 + index
    The number of items from nextFirst to the end of the items[] is (items.length - 1) - nextFirst
    nextFirst is in position: capacity - distance - 1

    */
    private int size, nextFirst, nextLast;
    private T[] items;

    /** Helper function to return actual index. */
    private int targetIndexInItems(int index){
        if (index >= items.length){
            return index - items.length;
        } else if (index < 0) {
            return items.length - 1;
        }
        return index;
    }

    /** Helper function for checking if the ArrayDeque needs to resize or not. */
    private boolean isNeedResize(int s){
        if (s == items.length){
            return true;
        }
        return false;
    }

    /** Helper function for checking if ArrayDeque needs to resize down */
    private boolean isNeedResizeDown(int s){
        float r = (float) size / items.length;
        if (r < 0.25){
            return true;
        }
        return false;
    }

    private int resizeDownCapacity(int s, int currentCapacity){
        int resizedCapacity = currentCapacity;
        while (resizedCapacity > s){
            resizedCapacity = resizedCapacity / 2;
        }
        return resizedCapacity * 2;
    }

    /** Helper function for calculating the position of ArrayDeque[0] in new items */
    private int firstItemStartingPosition(int capacity, int distance){
//        System.out.println(capacity - distance);
        return capacity - distance;
    }

    private void resize(int capacity){
        T[] a = (T[]) new Object[capacity]; // capacity: a.length == 16;

        // nextFirst to end
        int distance = items.length - 1 - nextFirst;
        int firstPos = firstItemStartingPosition(capacity, distance);
        for (int i = 0; i < distance; i += 1){
            a[firstPos + i] = items[nextFirst + 1 + i];
        }
        nextFirst = capacity - distance - 1;

        // head to nextLast
        distance = nextLast;
        for (int i = 0; i < distance; i += 1){
            a[i] = items[i];
        }

        items = a;
//        for (int i = 0; i < capacity; i += 1){
//            System.out.print(items[i]+" ");
//        }
    }

    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }


    @Override
    public void addFirst(T x) {
        nextFirst = this.targetIndexInItems(nextFirst);
        if (isNeedResize(size)){
            resize(size * 2);
        }
        items[nextFirst] = x;
        size += 1;
        nextFirst -= 1;
    }

    @Override
    public void addLast(T x) {
        nextLast = this.targetIndexInItems(nextLast);
        if (isNeedResize(size)){
            resize(size * 2);
        }
        items[nextLast] = x;
        size += 1;
        nextLast += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        for (int i = 1; i <= size; i += 1){
            returnList.add(items[this.targetIndexInItems(nextFirst + i)]);
        }
        return returnList;

    }

    @Override
    public boolean isEmpty() {
        if (size == 0){
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        T returnValue = items[targetIndexInItems(nextFirst + 1)];
        items[targetIndexInItems(nextFirst + 1)] = null;
        nextFirst += 1;
        size -= 1;
        if (isNeedResizeDown(size)){
            resize(resizeDownCapacity(size, items.length));
        }

        return returnValue;
    }

    @Override
    public T removeLast() {
        T returnValue = items[targetIndexInItems(nextLast - 1)];
        items[targetIndexInItems(nextLast - 1)] = null;
        nextLast -= 1;
        size -= 1;
        if (isNeedResizeDown(size)){
            resize(resizeDownCapacity(size, items.length));
        }
        return returnValue;
    }

    @Override
    public T get(int index) {
        if (index > size - 1 || index < 0){
            return null;
        }
        return items[targetIndexInItems(nextFirst + 1 + index)];
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
}
