package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T>{
    public Comparator<T> nc;
    public MaxArrayDeque(){
        super();
        nc = null;
    }

    public MaxArrayDeque(Comparator<T> c){
        super();
        this.nc = c;
    }

    public T max(){
        if (isEmpty()) { return null; }
        return max(nc);
    }

    public T max(Comparator<T> c){
        if(isEmpty()){ return null; }
        T maxItem = get(0);
        for (T item : this){
            if (c.compare(item, maxItem) > 0){
                maxItem = item;
            }
        }
        return maxItem;
    }

}
