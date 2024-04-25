/**
 * My version of a Hash Table class
 *
 * @author Chad Sawyer
 * @version 1/22/2024
 */
public class MyHashTable<K, V>
{
    Node<K, V>[] hashTable;
    private int size;
    private int tableSize;
    private double loadFactor;
    
    /**
     * Constructor for objects of class MyHashTable
     */
    public MyHashTable()
    {
        tableSize = 10;
        hashTable = (Node<K, V>[]) new Node[tableSize];
        size = 0;
        loadFactor = 0.7;
    }
    
    /**
     * Adds an element to the hash table. If overloaded, expands the size.
     *
     * @param  key  the element to be added's key
     * @param value the element to be added's value
     */
    public void put(K key, V value)
    {
        Node<K, V> newNode = new Node(key, value);
        Node<K, V> search = searchBucket(newNode.getHash() % tableSize, key);
        if (hashTable[newNode.getHash() % tableSize] == null) {
            hashTable[newNode.getHash() % tableSize] = newNode;
            size++;
        }
        else if (search != null) {
            search.setValue(value);
        }
        else {
            addToBucket(newNode.getHash() % tableSize, newNode);
            size++;
        }
        if (size > loadFactor * tableSize) {
            expandHashTable();
        }
    }
    
    /**
     * Returns the value for a given key
     *
     * @param  key the element the value is connected to
     * @return the value of the key
     */
    public V get(K key)
    {
        Node<K, V> search = searchBucket(hashFunction(key), key);
        if (search != null) {
            return search.getValue();
        }
        return null;
    }
    
    /**
     * Removes a key-value pair from the hash table
     *
     * @param  key the element to be removed
     * @return the value of the key removed
     */
    public V remove(K key)
    {
        Node<K, V> remove = searchBucket(hashFunction(key), key);
        if (remove != null) {
            removeFromBucket(hashFunction(key), remove);
            size--;
            return remove.getValue();
        }
        return null;
    }
    
    /**
     * Returns if the hash table is empty or not
     *
     * @return true or false that the hash table is empty
     */
    public boolean isEmpty()
    {
        return size == 0;
    }
    
    /**
     * Returns the size of the hash table
     *
     * @return the number of pairs in the table
     */
    public int size()
    {
        return size;
    }
    
    public String toString()
    {
        String str = "[";
        for (int i = 0; i < hashTable.length; i++)
        {
            str += hashTable[i];
            if (i == hashTable.length - 1) {
                str += "]";
            }
            else {
                str += ", ";
            }
        }
        return str;
    }
    
    private int hashFunction(K key) {
        /*int hash = 1;
        *for (int i = 0; i < key.length(); i++) {
        *    hash *= key.charAt(i);
        *}
        */
        return Math.abs(key.hashCode()) % tableSize;
    }
    
    private Node<K, V> searchBucket(int hashValue, K key) {
        Node<K, V> currentNode = hashTable[hashValue];
        while (currentNode != null) {
            if (currentNode.getKey().equals(key)) {
                return currentNode;
            }
            currentNode = currentNode.getNext();
        }
        return null;
    }
    
    private void addToBucket(int hashValue, Node<K, V> newNode) {
        if (hashTable[hashValue] == null) {
            hashTable[hashValue] = newNode;
        }
        else {
            Node<K, V> lastNode = hashTable[hashValue];
            while (lastNode.getNext() != null) {
                lastNode = lastNode.getNext();
            }
            lastNode.setNext(newNode);
        }
    }
    
    private void removeFromBucket(int hashValue, Node<K, V> oldNode) {
        Node<K, V> currentNode = hashTable[hashValue];
        if (currentNode == oldNode) {
            hashTable[hashValue] = currentNode.getNext();
            return;
        }
        while (currentNode.getNext() != oldNode) {
            currentNode = currentNode.getNext();
        }
        currentNode.setNext(oldNode.getNext());
    }
    
    private void expandHashTable() {
        Node<K, V>[] newTable = (Node<K, V>[]) new Node[tableSize * 2];
        tableSize *= 2;
        for (int i = 0; i < tableSize / 2; i++) {
            Node<K, V> currentNode = hashTable[i];
            while (currentNode != null) {
                Node<K, V> next = currentNode.getNext();
                int j = hashFunction(currentNode.getKey());
                Node<K, V> temp = newTable[j];
                newTable[j] = currentNode;
                currentNode.setNext(temp);
                currentNode = next;
            }
        }
        hashTable = newTable;
    }
}

class Node<K, V> {
    private K key;
    private V value;
    private int hash;
    private Node<K, V> next;
    
    public Node(K key, V value) {
        this.key = key;
        this.value = value;
        next = null;
        hash = Math.abs(key.hashCode());
    }

    public K getKey() {
        return key;
    }
    
    public V getValue() {
        return value;
    }
    
    public void setValue(V value) {
        this.value = value;
    }
    
    public Node<K, V> getNext() {
        return next;
    }
    
    public void setNext(Node<K, V> next) {
        this.next = next;
    }
    
    public int getHash() {
        return hash;
    }
    
    public String toString() {
        String str = "";
        if (next != null) {
            str += next.toString() + "|";
        }
        str += key + "->" + value;
        return str;
    }
}