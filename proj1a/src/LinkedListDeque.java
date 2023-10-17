import java.util.List;
import java.util.ArrayList; // import the ArrayList class

public class LinkedListDeque<T> implements Deque<T>{
    public class Node{
        public T item;
        public Node next;
        public Node prev;
        public Node(T i, Node n, Node p){
            item = i;
            next = n;
            prev = p;
        }
    }
    private Node sentinel;
    private int size;
    public LinkedListDeque(){
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }
    public LinkedListDeque(T i){
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        sentinel.next = new Node(i, sentinel, sentinel);
        size += 1;
    }

    @Override
    public void addFirst(T x) {
        size += 1;
        Node first = new Node(x, sentinel.next, sentinel);
        sentinel.next = first;
        sentinel.next.prev = first;
    }

    @Override
    public void addLast(T x) {
        size += 1;
        Node last = new Node(x, sentinel, sentinel.prev);
        sentinel.prev.next = last;
        sentinel.prev = last;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node currentNode = sentinel.next;
        for (int i=0; i < size; i += 1){
            returnList.add(currentNode.item);
            currentNode = currentNode.next;
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
        if (sentinel.next.item != null){
            T firstItem = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            size -= 1;
            return firstItem;
        }
        return null;
    }

    @Override
    public T removeLast() {
        if (sentinel.prev.item != null){
            T prevItem = sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            size -= 1;
            return prevItem;
        }
        return null;
    }

    @Override
    public T get(int index) {
        Node current = sentinel;
        if (0 < index && index < size -1){
            for (int i = 0; i <= index; i += 1){
                current = current.next;
            }
            return current.item;
        }
        return null;
    }

    @Override
    public T getRecursive(int index) {
        if (0 < index && index < size -1){
            return getRecursive(sentinel.next, index);
        }
        return null;
    }

    private T getRecursive(Node node, int index){
        if (index == 0){
            return node.item;
        }
        return getRecursive(node.next, index - 1);
    }
}
