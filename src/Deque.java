/* Sun Zhicheng
 * coursera course PA2
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.In;

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
        nil = null;
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
        if(item == null) throw new NullPointerException("input null is fobbiden");        
        
        Node<Item> first = new Node<Item>();
        first.next = nil.next;
        first.item = item;
        first.prev = nil;
        nil.next = first;
        count++;
    }

    //add item to the end of the queue
    public void addLast(Item item) {
        if(item == null) throw new NullPointerException("input null is fobbiden");
        Node<Item> last = new Node<Item>();
        last.prev = nil.prev;
        last.item = item;
        last.next = nil;
        nil.prev = last;
        count++;
    }
       
    //remove item in the beginning of the queue
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");      
        Item item = nil.next.item;
        nil.next = nil.next.next;
        nil.next.prev = nil;
        return item;
    }
 
    //remove item in the end of the queue
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = nil.prev.item;
        nil.prev = nil.prev.prev;
        nil.prev.next = nil;

        return item;
    }
    
    //Iterator
    public Iterator<Item> iterator() {
        return new ListIterator<Item>(nil.next);
    }
    
    //new ListIterator class
    public class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;
        
        public ListIterator(Node<Item> first) {
            current = first;
        }
        
        public boolean hasNext() {return current != null;}
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
        // TODO Auto-generated method stub
        In in = new In(args[0]);      // input file
        Deque<Integer> q = new Deque<Integer>();

        while (!in.isEmpty()) {
            int i = in.readInt();
        }
        

    }

}
