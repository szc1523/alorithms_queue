/* Sun Zhicheng
 * coursera course PA2
 * A test unit
 */

import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Subset {

    public static void main(String[] args) {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        int k = StdIn.readInt();
        while (!StdIn.isEmpty()) {
            q.enqueue(StdIn.readString());   
        }
        Iterator<String> i = q.iterator();
        int count = 0;
        while(i.hasNext() && count < k) {
            StdOut.println(i.next());
            count++;
        }
    }

}
