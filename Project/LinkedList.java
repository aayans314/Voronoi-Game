/*
 * File name: LinkedList.java
 * Author: Aayan Shah
 * Last modified : 17th oct
 * 
 */



import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements Iterable<T>, Queue<T>, Stack<T> {
    
    private Node head;
    private Node tail;
    private int size;

    private class Node {
        private T item;
        private Node next;

        public Node(T item) {
            this.item = item;
            this.next = null;
        }

        public T getData() {
            return this.item;
        }

        public Node getNext() {
            return this.next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void addLast(T item) {
        Node newNode = new Node(item);
        if (size == 0) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    public T removeLast() {
        
        if (size == 1) {
            T data = head.getData();
            head = null;
            tail = null;
            size--;
            return data;
        }
        Node current = head;
        while (current.getNext() != tail) {
            current = current.getNext();
        }
        T data = tail.getData();
        tail = current;
        tail.setNext(null);
        size--;
        return data;
    }

    public T getLast() {
        
        return tail.getData();
    }

    public int size() {
        return this.size;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public String toString() {
        
        return "Size="+this.size();
    }

    public void add(T item) {
        Node newNode = new Node(item);
        if (size == 0) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setNext(head);
            head = newNode;
        }
        size++;
    }

    public boolean contains(Object o) {
        for (T item : this) {
            if (item.equals(o)) {
                return true;
            }
        }
        return false;
    }

    public T get(int index) {
        
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getData();
    }

    public T remove() {
        
        T data = head.getData();
        head = head.getNext();
        if (head == null) {
            tail = null; // If list is now empty
        }
        size--;
        return data;
    }

    public void add(int index, T item) {
        
        if (index == size) {
            addLast(item);
            return;
        }
        Node newNode = new Node(item);
        if (index == 0) {
            newNode.setNext(head);
            head = newNode;
            if (size == 0) {
                tail = newNode;
            }
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            newNode.setNext(current.getNext());
            current.setNext(newNode);
        }
        size++;
    }

    public T remove(int index) {
        
        if (index == 0) {
            return remove();
        }
        Node current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.getNext();
        }
        T data = current.getNext().getData();
        current.setNext(current.getNext().getNext());
        if (current.getNext() == null) {
            tail = current; // Update tail if we removed the last element
        }
        size--;
        return data;
    }

    public boolean equals(Object o) {
        if (!(o instanceof LinkedList)) {
            return false;
        }
        LinkedList<?> other = (LinkedList<?>) o;
        if (this.size != other.size) {
            return false;
        }
        Node current1 = this.head;
        @SuppressWarnings("unchecked")
        Node current2 = (LinkedList<T>.Node) other.head;
        while (current1 != null) {
            if (!current1.getData().equals(current2.getData())) {
                return false;
            }
            current1 = current1.getNext();
            current2 = current2.getNext();
        }
        return true;
    }

    private class LLIterator implements Iterator<T> {
        private Node current;

        public LLIterator(Node head) {
            this.current = head;
        }

        public boolean hasNext() {
            return current != null;
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T data = current.getData();
            current = current.getNext();
            return data;
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove not supported");
        }
    }

    public Iterator<T> iterator() {
        return new LLIterator(head);
    }

    // Queue interface methods
    
    public void offer(T item) {
        addLast(item);
    }

    @Override
    public T peek() {
        if(head==null) return null;
        return head.getData();
    }

    @Override
    public T poll() {
        return remove();
    }
    // Stack interface
    @Override
    public T pop(){
        return remove();
    }
    public void push(T item){
        add(item);
    }

}