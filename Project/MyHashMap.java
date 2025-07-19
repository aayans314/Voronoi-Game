
/**
 * Filename: HashMap.java
 * Author: Aayan Shah
 * Last modified: 15th November 2024
 * Prupose of file: Implementing a HashMap
 */

import java.util.ArrayList;

public class MyHashMap<K, V> implements MapSet<K, V> {

    // Basic fields
    private int size;
    private HashNode<K, V>[] nodes;
    private double maxLoadFactor;
    public ArrayList<K> input = new ArrayList<>();

    /**
     * Inner class representing a node in the HashMap
     * 
     * @param <K> the type of keys maintained by this node
     * @param <V> the type of mapped values
     */
    @SuppressWarnings("hiding")
    public class HashNode<K, V> extends KeyValuePair<K, V> {

        private HashNode<K, V> next;

        /**
         * Constructor for HashNode
         * 
         * @param k the key of the node
         * @param v the value of the node
         */
        public HashNode(K k, V v) {
            super(k, v);
            this.next = null;
        }
    }

    /**
     * Default constructor that initializes the HashMap with a capacity of 16
     */
    public MyHashMap() {
        this(16);
    }

    /**
     * Constructor that initializes the HashMap with the given capacity
     * 
     * @param capacity the initial capacity of the HashMap
     */

    public MyHashMap(int capacity) {
        this(capacity, 0.75);
    }

    /**
     * Constructor that initializes the HashMap with the given capacity and load
     * factor
     * 
     * @param capacity   the initial capacity of the HashMap
     * @param loadFactor the maximum load factor before resizing
     */

    @SuppressWarnings("unchecked")
    public MyHashMap(int capacity, double loadFactor) {
        this.size = 0;
        this.nodes = new HashNode[capacity];
        this.maxLoadFactor = loadFactor;
    }

    /**
     * Returns the current capacity of the HashMap
     * 
     * @return the number of bins in the HashMap
     */
    public int capacity() {
        return nodes.length;
    }

    /**
     * Computes the hash value for the given key
     * 
     * @param key the key for which the hash value is to be calculated
     * @return the hash value for the key
     */
    private int hash(K key) {
        return Math.abs(key.hashCode() % this.capacity());
    }

    /**
     * Converts the HashMap to a String representation
     * 
     * @return the String representation of the HashMap
     */
    public String toString() {
        String output = "";
        for (int i = 0; i < this.capacity(); i++) {
            HashNode<K, V> node = this.nodes[i];
            output += "bin " + i + ": ";
            while (node != null) {
                output += node.toString() + " | ";
                node = node.next;
            }
            output += "\n";
        }
        return output;
    }

    /**
     * Inserts a key-value pair into the HashMap
     * 
     * @param key   the key to be added
     * @param value the value associated with the key
     * @return the previous value associated with the key, or null if the key was
     *         not present
     */
    @Override
    public V put(K key, V value) {
        /*
         * System.out.println("Im putting "+ key);
         * System.out.println(size());
         * System.out.println(nodes.length);
         */
        int position = hash(key);
        HashNode<K, V> current = nodes[position];

        if (current == null) {
            // No entry exists at this position, add new node
            nodes[position] = new HashNode<>(key, value);
        } else {
            // Traverse the linked list at this node to handle collisions
            while (current != null) {
                if (current.getKey().equals(key)) {
                    // Update the value if the key already exists
                    V oldValue = current.getValue();
                    current.setValue(value);
                    return oldValue;
                }
                if (current.next == null)
                    break;
                current = current.next;
            }
            // Add new node at the end of the chain
            current.next = new HashNode<>(key, value);
        }
        size++;
        input.add(key);
        // System.out.println(size);
        // System.out.println(this.capacity());
        if (size > this.capacity() * maxLoadFactor) {
            resize(this.capacity() * 2);
        }

        return null;
    }

    /**
     * Resizes the HashMap to the new capacity
     * @param newCapacity the new capacity of the HashMap
     */
    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        HashNode<K, V>[] oldArray = nodes;
        nodes = new HashNode[newCapacity];

        for (int i = 0; i < oldArray.length; i++) {
            for (HashNode<K, V> node = oldArray[i]; node != null; node = node.next) {
                int position = hash(node.getKey());
                if (nodes[position] == null) {
                    nodes[position] = new HashNode<>(node.getKey(), node.getValue());
                } else {
                    HashNode<K, V> newNode = new HashNode<>(node.getKey(), node.getValue());
                    newNode.next = nodes[position];
                    nodes[position] = newNode;
                }
            }
        }

    }

    /**
     * Checks if the given key is present in the HashMap
     * @param key the key to be checked
     * @return true if the key is present, false otherwise
     */
    @Override
    public boolean containsKey(K key) {
        HashNode<K, V> node = nodes[hash(key)];
        if (node == null)
            return false;
        if (node.getKey().equals(key))
            return true;
        while (node.next != null) {
            if (node.next.getKey().equals(key)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    /**
     * Retrieves the value associated with the given key
     * @param key the key whose associated value is to be returned
     * @return the value associated with the key, or null if the key is not found
     */
    @Override
    public V get(K key) {
        int position = hash(key);
        HashNode<K, V> cur = nodes[position];
        if (cur == null)
            return null;
        if (cur.next == null)
            return cur.getValue();
        while (cur.next != null || cur.getKey().equals(key)) {
            if (cur.getKey().equals(key))
                return cur.getValue();
            cur = cur.next;
        }
        return null;
    }

    /**
     * Removes the key-value pair associated with the given key
     * @param key the key whose mapping is to be removed
     * @return the value previously associated with the key, or null if the key was not present
     */
    @Override
    public V remove(K key) {
        int position = hash(key);
        HashNode<K, V> current = nodes[position];
        HashNode<K, V> previous = null;

        while (current != null) {
            if (current.getKey().equals(key)) {
                V oldValue = current.getValue();
                if (previous == null) {
                    // Removing the first node in the chain
                    nodes[position] = current.next;
                } else {
                    // Remove current by linking the previous node to the next node
                    previous.next = current.next;
                }
                size--;

                // Resize if size is below the threshold
                if (size < nodes.length * maxLoadFactor / 4) {
                    resize(this.capacity() / 2);
                }
                return oldValue;
            }
            previous = current;
            current = current.next;
        }

        // Key not found
        return null;
    }

    /**
     * Returns a list of all keys in the HashMap
     * @return an ArrayList containing all keys
     */
    @Override
    public ArrayList<K> keySet() {
        ArrayList<K> keySet = new ArrayList<>();
        for (HashNode<K, V> oneNode : nodes) {
            while (oneNode != null) {
                keySet.add(oneNode.getKey());
                oneNode = oneNode.next;
            }
        }
        return keySet;
    }

    public ArrayList<K> inputList(){
        return this.input;
    }

    /**
     * Returns a list of all values in the HashMap
     * @return an ArrayList containing all values
     */
    @Override
    public ArrayList<V> values() {
        ArrayList<V> valueSet = new ArrayList<>();
        for (HashNode<K, V> oneNode : nodes) {
            while (oneNode != null) {
                valueSet.add(oneNode.getValue());
                oneNode = oneNode.next;
            }

        }
        return valueSet;
    }

    /**
     * Returns a list of all key-value pairs in the HashMap
     * @return an ArrayList containing all key-value pairs
     */

    @Override
    public ArrayList<KeyValuePair<K, V>> entrySet() {
        ArrayList<KeyValuePair<K, V>> entrySet = new ArrayList<>();
        for (HashNode<K, V> oneNode : nodes) {

            while (oneNode != null) {
                entrySet.add(oneNode);
                oneNode = oneNode.next;
            }

        }
        return entrySet;
    }

    /**
     * Returns the number of key-value mappings in the HashMap
     * @return the size of the HashMap
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Clears all the key-value mappings in the HashMap
     */
    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        size = 0;
        this.nodes = new HashNode[nodes.length];
    }

    /**
     * Returns the maximum depth of any chain in the HashMap
     * @return the maximum depth of any chain
     */
    @Override
    public int maxDepth() {
        int count = 0;
        for (HashNode<K, V> node : nodes) {
            int countOfNode = 0;
            if (node != null) {
                countOfNode++;
                while (node.next != null) {
                    countOfNode++;
                    node = node.next;
                }
            }
            count = Math.max(count, countOfNode);
        }
        return count;
    }

    public static void main(String[] args) {
        MyHashMap<Integer, Integer> myMap = new MyHashMap<>();
        for (int i = 0; i < 10000; i++) {
            myMap.put(i, i);
        }
        System.out.println(myMap);
    }

}
