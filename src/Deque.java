/* Sun Zhicheng
 * coursera course PA2
 * An implementation of Deque
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {
    //nil is the sentinel, described in CLRS
    private Node<Item> nil; 
    private int count;
    
    //private linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> prev;
        private Node<Item> next;
    }
    
    //constructor
    public Deque() {
        count = 0;
        nil = new Node<Item>(); //must write like that, i forgot new...
        nil.item = null;
        nil.prev = nil;
        nil.next = nil;        
    }
    
    //return true if empty
    public boolean isEmpty(){
        return count == 0;
    }

    //return size of deque
    public int size() {
        return count;
    }
    
    //add item to the beginning of the queue
    public void addFirst(Item item) {
        //if(item == null) throw new NullPointerException("input null is fobbiden");        
        
        Node<Item> first = new Node<Item>();
        first.next = nil.next;
        first.item = item;
        first.prev = nil;
        nil.next.prev = first;
        nil.next = first;
        count++;
    }

    //add item to the end of the queue
    public void addLast(Item item) {
        //if(item == null) throw new NullPointerException("input null is fobbiden");
        
        Node<Item> last = new Node<Item>();
        last.prev = nil.prev;
        last.item = item;
        last.next = nil;
        nil.prev.next = last;
        nil.prev = last;
        count++;
    }
       
    //remove item in the beginning of the queue
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");      
        Item item = nil.next.item;
        nil.next = nil.next.next;
        nil.next.prev = nil;
        count--;
        return item;
    }
 
    //remove item in the end of the queue
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = nil.prev.item;
        nil.prev = nil.prev.prev;
        nil.prev.next = nil;
        count--;
        return item;
    }
    
    //Iterator
    public Iterator<Item> iterator() {
        return new ListIterator<Item>(nil.next);
    }
    
    //new ListIterator class, remove() is forbidden
    public class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;
        
        public ListIterator(Node<Item> first) {
            current = first;
        }
        
        public boolean hasNext() {return current != nil;}
        public void remove() {throw new UnsupportedOperationException();}
        
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }        
    }
    
    //unit testing
    public static void main(String[] args) {

        Deque<Integer> q = new Deque<Integer>();
        q.addFirst(2);
        q.addLast(8);
        q.addFirst(1);
        q.addLast(9);
        q.addLast(10);
        for (int i : q) {
            StdOut.println(i);
        }        
        
        while(true) {
            StdOut.println(q.removeLast());
        }

    }

}
