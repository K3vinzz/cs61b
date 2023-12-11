package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayDeque<T> implements Deque<T> {
    int nextFirst;
    int nextLast;
    int size;
    T[] items;
    public ArrayDeque(){
        nextFirst = 0;
        nextLast = 1;
        size = 0;
        items = (T[])new Object[8];
    }

    private class ArrayDequeIterator implements Iterator<T>{
        private int pos;
        public ArrayDequeIterator(){
            pos = 0;
        }
        public boolean hasNext(){
            return pos < size;
        }

        public T next(){
            T returnItem = items[pos];
            pos += 1;
            return returnItem;
        }
    }
    public Iterator<T> iterator() {return new ArrayDequeIterator(); }

    @Override
    public void addFirst(T x) {
        if (isNeedResizeUp(size)){
            resize();
            System.out.println("resize up to : "+items.length);

        }
        items[nextFirst] = x;
        nextFirst -= 1;
        size += 1;
        if (nextFirst < 0){
            nextFirst = items.length - 1;
        }
    }

    @Override
    public void addLast(T x) {
        if (isNeedResizeUp(size)){
            resize();
            System.out.println("resize up to : "+items.length);
        }
        items[nextLast] = x;
        nextLast += 1;
        size += 1;
        if (nextLast == items.length){
            nextLast = 0;
        }
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        for (int i = 0; i < size; i += 1){
            returnList.add(items[getIndexInItems(i)]);
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        T returnItem = items[getIndexInItems(0)];
        items[getIndexInItems(0)] = null;
        nextFirst += 1;
        if (nextFirst == items.length){
            nextFirst = 0;
        }
        size -= 1;
        if ((float)size / items.length < 0.25){
            resizeDown();
            System.out.println("resize down to : "+items.length);

        }
        return returnItem;
    }

    @Override
    public T removeLast() {
        T returnItem = items[getIndexInItems(size - 1)];
        items[getIndexInItems(size - 1)] = null;
        nextLast -= 1;
        if (nextLast < 0){
            nextLast = items.length - 1;
        }
        size -= 1;
        if ((float)size / items.length < 0.25){
            resizeDown();
            System.out.println("resize down to :"+items.length);
        }
        return returnItem;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size ){
            return null;
        }
        return items[getIndexInItems(index)];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    public boolean isNeedResizeUp(int s){
        if (s == items.length){
            return true;
        }
        return false;
    }

    /** Resize the arrayDeque, put the array into the new one from index 0
     * which means the new nextFirst point to the end i.e. newArray[newArray.length - 1]
     * and the new nextLast point to newArray[size]
     * e.g. [1, 2, 3, 4 ,5, 6, 7, 8]
     * addLast(9)
     * [1, 2, 3, 4, 5, 6, 7, 8, nextLast, _, _, _, _, _, _, nextFirst]
     * [1, 2, 3, 4, 5, 6, 7, 8, 9, nextLast, _, _, _, _, _, nextFirst] */

    public void resize(){
        T[] newItems = (T[]) new Object[items.length * 2];
        for (int i = 0; i < size; i += 1){
            newItems[i] = items[getIndexInItems(i)];
        }
        nextFirst = newItems.length - 1;
        nextLast = size;
        items = newItems;
    }

    public void resizeDown(){
        T[] newItems = (T[]) new Object[items.length / 2];
        for (int i = 0; i < size; i += 1){
            newItems[i] = items[getIndexInItems(i)];
        }
        nextFirst = newItems.length - 1;
        nextLast = size;
        items = newItems;
    }

    public int getIndexInItems(int i){
        int index = nextFirst + 1 + i;
        if (index > (items.length - 1)){
            index = index % (items.length);
        }
        return index;
    }
}
