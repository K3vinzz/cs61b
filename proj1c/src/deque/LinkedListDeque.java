package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedListDeque<T> implements Deque<T> {
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

    private class LinkedListDequeIterator implements Iterator<T>{
        private int pos;
        public LinkedListDequeIterator(){
            pos = 0;
        }
        public boolean hasNext(){
            return pos < size;
        }

        public T next(){
            T returnItem = get(pos);
            pos += 1;
            return returnItem;
        }
    }

    public Iterator<T> iterator() { return new LinkedListDequeIterator(); }

    @Override
    public boolean equals(Object o){
        if (o == this) {return true; }
        if (o instanceof LinkedListDeque oas){
            if (oas.size != this.size){
                return false;
            }
            Node current1 = this.sentinel;
            Node current2 = oas.sentinel;
            for (int i = 0; i <= size; i += 1){
//                System.out.println("c1 :"+current1.item);
//                System.out.println("c2 :"+current2.item);
                if (current1.item != current2.item){
                    return false;
                }
                current1 = current1.next;
                current2 = current2.next;
            }
            return true;
        }
        return false;
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
        if (-1 < index && index < size){
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
