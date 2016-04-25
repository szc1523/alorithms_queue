/* Sun Zhicheng
 * coursera course PA2
 * An implementation of Randomized Dequeue Queue
 * Using a resizing array
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int count; //current number of elements
    private Item[] array;
    
    @SuppressWarnings("unchecked")
    public RandomizedQueue() {
        count = 0;
        array = (Item[]) new Object[2]; 
    }
        
    public boolean isEmpty() {
        return count == 0;
    }
    
    public int size() {
        return count;
    }
    
    //resize array to twice as large as the previous one
    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        Item[] newarray = (Item[]) new Object[capacity];
        for(int i = 0; i < count; i++)
            newarray[i] = array[i];
        array = newarray;
    }
    
    //insert an item
    public void enqueue(Item item) {
        if(item == null) throw new NullPointerException("input null is fobbiden");     
        
        if(count == array.length) resize(2*array.length);         
        array[count++] = item; 
    }
    
    //remove an item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");     
        int r = StdRandom.uniform(count);
        Item last = array[count-1];
        
        Item temp = last;
        last = array[r];
        array[r] = temp;
        count--;
        return last;               
    }
    
    public Iterator<Item> iterator() {
        return new ListIterator<Item>();
    }
    
    // remove() is forbidden 
    public class ListIterator<Item> implements Iterator<Item> {
        private int current;
        private int[] ai = new int[count]; //array index
        
        public ListIterator() {
            for(int i = 0; i < count; i++) {
                ai[i] = i;
            }
            StdRandom.shuffle(ai);
            current = 0;
        }
        
        public boolean hasNext() {return current!=count;}
        public void remove() {throw new UnsupportedOperationException();}
        
        @SuppressWarnings("unchecked")
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return (Item) array[ai[current++]];            
        }
    }
    
    public static void main(String[] args) {
        RandomizedQueue<Integer> qi = new RandomizedQueue<Integer>();
        qi.enqueue(7);
        qi.enqueue(4);
        qi.enqueue(9);
        qi.enqueue(8);   
        qi.enqueue(12); 
        

        StdOut.println(qi.size());
        
        for (int i:qi) StdOut.println(i);     
        //while(!qi.isEmpty()) StdOut.println(qi.dequeue());            

    }

}
