package hashmap;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/**
 *  A hash table-backed Map implementation.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author Kevin Cheng
 */
public class MyHashMap<K, V> implements Map61B<K, V> {
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
    static final double DEFAULT_LOAD_FACTOR = 0.75f;
    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private int size;
    private double loadFactor;
    private int capacity;
    private Collection<Node>[] buckets;

    /** Constructors */
    public MyHashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        if (initialCapacity < 0)
            throw  new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
        if (loadFactor <= 0 || Double.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
        this.loadFactor = loadFactor;
        this.capacity = initialCapacity;
        this.buckets = new Collection[initialCapacity];
        for(int i= 0; i < initialCapacity; i++) {
            buckets[i] = createBucket();
        }
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<Node>();
    }

    private int hash(K key) {
        return Math.floorMod(key.hashCode(), this.capacity);
    }

    private void resize(int newCapacity) {
        this.capacity = newCapacity;
        Collection<Node>[] newBuckets = new Collection[newCapacity];
        for (int i = 0; i < newCapacity; i++) {
            newBuckets[i] = createBucket();
        }
        for (Collection<Node> bucket : this.buckets) {
            for (Node node : bucket) {
                newBuckets[hash(node.key)].add(new Node(node.key, node.value));
            }
        }
        this.buckets = newBuckets;
    }

    private double calLoadFactor() {
        return (double)this.size() / this.capacity;
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

    @Override
    public void put(K key, V value) {
        int i = hash(key);
        for (Node node : this.buckets[i]) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }
        this.buckets[hash(key)].add(new Node(key, value));
        this.size += 1;
        if (this.calLoadFactor() > this.loadFactor)
            resize(this.capacity * 2);
    }

    @Override
    public V get(K key) {
        int i = hash(key);
        for (Node node : buckets[i]) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        int i = hash(key);
        for (Node node : buckets[i]) {
            if (node.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        this.size = 0;
        for(int i= 0; i < this.capacity; i++) {
            buckets[i] = createBucket();
        }
    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }


}
