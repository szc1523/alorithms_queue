/* Sun Zhicheng
 * coursera course PA2
 * An implementation of Randomized Dequeue Queue
 * Using a resizing array
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int count; //current number of elements
    private Item[] array;

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
    private void resize(int capacity) {
        Item[] newarray = (Item[]) new Object[capacity];
        for (int i = 0; i < count; i++)
            newarray[i] = array[i];
        array = newarray;
    }
    
    //insert an item
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException("input"
                + " null is fobbiden");     
        
        if (count == array.length) resize(2*array.length);         
        array[count++] = item; 
    }
    
    //remove an item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue"
                + " underflow");     
        int r = StdRandom.uniform(count);
        Item last = array[count-1];
        
        Item temp = last;
        last = array[r];
        array[r] = temp;
        
        //must do that for loistering. in case Item is a string or object
        array[count-1] = null; 
        count--;
        
        if (count > 0 && count < array.length/4) {
            resize(array.length/2);
        }
        
        return last;               
    }
    
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("Queue"
                + " underflow");   
        int r = StdRandom.uniform(count);
        return array[r];
    }
    
    public Iterator<Item> iterator() {
        return new ListIterator<Item>();
    }
    
    // remove() is forbidden 
    private class ListIterator<Item> implements Iterator<Item> {
        private int current;
        private int[] ai = new int[count]; //array index
        
        ListIterator() {
            for (int i = 0; i < count; i++) {
                ai[i] = i;
            }
            StdRandom.shuffle(ai); 
            current = 0;
        }
        
        public boolean hasNext() {
            return current != count;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
        
        // can also use the same method as in dequeue without a shuffle
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = (Item) array[ai[current++]];
            return item;            
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
