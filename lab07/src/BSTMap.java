import java.util.*;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {


    private class BSTNode {
        private K key;
        private V value;
        private BSTNode left;
        private BSTNode right;

        public BSTNode(K key, V value){
            this.key = key;
            this.value = value;
        }
    }

    private BSTNode root;
    private int size = 0;
    private Set<K> keySet = new TreeSet<>();
    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    public BSTNode put(BSTNode node, K key, V value){
        if (node == null){
            size += 1;
            keySet.add(key);
            return new BSTNode(key, value);
        }
        int cmp = key.compareTo(node.key);
        if (cmp > 0){
            node.right = put(node.right, key, value);
        } else if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else {
            node.value = value;
        }
        return node;
    }

    @Override
    public V get(K key) {
        BSTNode res = get(root, key);
        if (res != null){
            return res.value;
        }
        return null;
    }

    public BSTNode get(BSTNode node, K key){
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp > 0) {
            return get(node.right, key);
        } else if (cmp < 0) {
            return get(node.left, key);
        } else{
            return node;
        }
    }

    @Override
    public boolean containsKey(K key) {
        return get(root, key) != null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        return keySet;
    }

    @Override
    public V remove(K key) {
        if (!containsKey(key)){
            return null;
        }
        V returnVal = get(key);
        remove(root, key);
        return returnVal;

    }

    private BSTNode remove(BSTNode node, K key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp > 0) {
            node.right = remove(node.right, key);
        } else if (cmp < 0) {
            node.left = remove(node.left, key);
        } else {
            if (node.left == null && node.right == null) {
                return null;
            } else if (node.right == null) {
                return node.left;
            } else if (node.left == null){
                return node.right;
            } else {
                BSTNode tmp = minKeyNode(node.right);
                node.key = tmp.key;
                node.value = tmp.value;
                node.right =  remove(node.right, tmp.key);
            }
        }
        return node;
    }

    private BSTNode minKeyNode(BSTNode node){
        if (node.left != null) {
            return minKeyNode(node.left);
        }
        return node;
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTMapIterator();
    }

    private class BSTMapIterator implements Iterator<K>{
        Stack<K> stack = new Stack<>();
        public BSTMapIterator(){
            inorderTraversal(root);
        }

        public boolean hasNext(){
            return !stack.isEmpty();
        }

        public K next(){
            return stack.pop();
        }

        public void inorderTraversal(BSTNode node) {
            if (node != null){
                inorderTraversal(node.left);
                stack.push(node.key);
                inorderTraversal(node.right);
            }
        }
    }

}
